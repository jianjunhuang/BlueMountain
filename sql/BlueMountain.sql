CREATE DATABASE IF NOT EXISTS BlueMountain;

USE BlueMountain;

DROP TABLE IF EXISTS t_machine;
CREATE TABLE t_machine (
  f_machine_id  VARCHAR(32),
  f_status      INT,
  f_temperature FLOAT,
  f_level       INT,
  f_connected   BOOLEAN,
  PRIMARY KEY (f_machine_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  f_user_id              VARCHAR(32),
  f_user_name            VARCHAR(120) NOT NULL,
  f_user_status          INT,
  f_user_cup_size        INT,
  f_user_fav_temperature INT,
  f_machine_id           VARCHAR(32),
  PRIMARY KEY (f_user_id, f_machine_id),
  CONSTRAINT t_user_fk FOREIGN KEY (f_machine_id) REFERENCES t_machine (f_machine_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS t_community;
CREATE TABLE t_community (
  f_community_id VARCHAR(32),
  f_user_id      VARCHAR(32),
  f_machine_id   VARCHAR(32),
  f_title        VARCHAR(256) NOT NULL,
  f_content      VARCHAR(2048),
  f_date         DATETIME,
  f_is_del       INT,
  PRIMARY KEY (f_community_id, f_user_id, f_machine_id),
  CONSTRAINT t_community_user_fk FOREIGN KEY (f_user_id) REFERENCES t_user (f_user_id),
  CONSTRAINT t_community_machine_fk FOREIGN KEY (f_machine_id) REFERENCES t_machine (f_machine_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

DROP TABLE IF EXISTS t_community_disposition;
CREATE TABLE t_community_disposition (
  f_community_id VARCHAR(32),
  f_user_id      VARCHAR(32),
  f_is_agree     INT,
  PRIMARY KEY (f_community_id, f_user_id),
  CONSTRAINT t_community_id_fk FOREIGN KEY (f_community_id) REFERENCES t_community (f_community_id),
  CONSTRAINT t_user_id_fk FOREIGN KEY (f_user_id) REFERENCES t_community (f_user_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

