package com.jianjunhuang.ssm.base.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * MyBatis 布尔类型强制转换器<br>
 * 用于将数据库中的Integer转换为Boolean
 * @author ousheobin
 */
public class MybatisBooleanHandler implements TypeHandler<Boolean> {

	@Override
	public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
		int result = rs.getInt(columnName);
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		int result = rs.getInt(columnIndex);
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Boolean getResult(CallableStatement rs, int columnIndex) throws SQLException {
		int result = rs.getInt(columnIndex);
		if(result==0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void setParameter(PreparedStatement ps, int index, Boolean parameter, JdbcType jdbcType) throws SQLException {
		if(parameter) {
			ps.setInt(index, 1);
		} else {
			ps.setInt(index, 0);
		}
	}
	
	

}
