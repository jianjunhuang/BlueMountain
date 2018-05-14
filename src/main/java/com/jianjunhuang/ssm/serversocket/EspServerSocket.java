package com.jianjunhuang.ssm.serversocket;

import com.google.gson.Gson;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.service.MachineService;
import com.jianjunhuang.ssm.utils.CoffeeOrderUtils;
import com.jianjunhuang.ssm.websocket.CoffeeWebSocketHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * just user for machine
 */
@Component
public class EspServerSocket extends Thread {

    private ServerSocket serverSocket;

    @Resource
    private MachineService machineService;

    @Resource
    private CoffeeOrderUtils coffeeOrderUtils;


    private static Map<String, ProcessSocketData> socketMap = new HashMap<>();

    public EspServerSocket() {
        if (serverSocket == null) {
            try {
                this.serverSocket = new ServerSocket(8266);
                start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {

        while (!this.isInterrupted()) { // 线程未中断执行循环
            try {
                // 开启服务器，线程阻塞，等待8266的连接
                Socket socket = serverSocket.accept();
                ProcessSocketData psd = new ProcessSocketData(socket);
                new Thread(psd).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO
    public void closeServerSocket() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //将socket连接一静态集合变量的形式暴露出去
    public static Map<String, ProcessSocketData> getSocketMap() {
        return socketMap;
    }

    public class ProcessSocketData extends Thread {
        private Gson gson = null;
        private Socket socket;
        private InputStream in = null;
        private DataOutputStream out = null;

        private String id = null;
        private boolean play = false;
        private int preStatus = -1;

        // 构造方法，传入连接进来的socket
        public ProcessSocketData(Socket socket) {
            this.socket = socket;
            gson = new Gson();
            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            play = true;
        }

        public void run() {
            byte[] msg = new byte[1024];
            try {
                // 死循环，无线读取8266发送过来的数据
                while (play) {
                    in.read(msg);//读取流数据
                    String str = new String(msg).trim();
                    if ("".equals(str)) {
                        continue;
                    }
                    try {
                        System.out.println("Esp发过来的数据>>>>>>>：" + str);
                        Machine machine = gson.fromJson(str, Machine.class);
                        System.out.println("Esp发过来的数据_______：" + machine);
                        EspServerSocket.socketMap.put(machine.getMachineId(), this);
                        //update machine status
                        machineService.updateMachine(machine);
                        switch (machine.getStatus()) {
                            case Machine.STATUS_KEEP_WARMING: {
                                if (preStatus == Machine.STATUS_MAKING_COFFEE) {
                                    coffeeOrderUtils.notifyUserToGetCoffee(machine.getMachineId());
                                }
                                break;
                            }
                        }
                        preStatus = machine.getStatus();
                        CoffeeWebSocketHandler.notifyUsersToUpdateMachine(machine.getMachineId());
                        String outMsg = "{\"action\":-1}";
                        out.write(outMsg.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                        socketMap.remove(id);
                        //TODO update user
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //TODO
        //这是服务器发送数据到8266的函数
        public void send(String msg) {
            try {
                out.write(msg.getBytes());
            } catch (IOException e) {
                try {
                    // 移除集合里面的Socket
                    EspServerSocket.socketMap.remove(id);
                    out.close();
                    play = false;
                    in.close();
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("该客户端已退出！");
            }
        }
    }

    public static void notifyMachineToMakeCoffee(String machineId) {
        System.out.println(">>>>>>>>>>>>>>>" + machineId);
        ProcessSocketData process = getSocketMap().get(machineId);
        if (null != process) {
            process.send("{\"action\":2}");
            System.out.println("send >>>>>>>>>>>>>>>" + machineId);
        }
    }

    public static void notifyMachineToSetInsulationTemperature(String machineId, int temperature) {
        ProcessSocketData process = getSocketMap().get(machineId);
        if (null != process) {
            process.send("{\"action\":3,\"temperature\":" + temperature + "}");
        }
    }
}
