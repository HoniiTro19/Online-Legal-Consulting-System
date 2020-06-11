/*
 * MySQL创建数据库表单
 * userTable 记录用户的个人信息 com.huidong.legalsys.domain.User
 * statureTable 记录法条信息 com.huidong.legalsys.domain.Stature
 * loginTable 记录用户的登录 com.huidong.legalsys.domain.Login
 * consultTable 记录用户的咨询 com.huidong.legalsys.domain.Consult
 * convrTable 记录用户的会话 com.huidong.legalsys.domain.Convr
 * 运行init.sql时，所有数据库信息都会被初始化
 */

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
SET GLOBAL LOCAL_INFILE = 1;

DROP DATABASE IF EXISTS legalsys;
CREATE DATABASE legalsys;
use legalsys;

DROP TABLE IF EXISTS userTable;
CREATE TABLE userTable  (
  phone varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  name varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  password varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  idno varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  licenseurl varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  firmname varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (phone) USING BTREE,
  UNIQUE(phone, idno, licenseurl)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS statureTable;
CREATE TABLE statureTable  (
  lawid int(11) NOT NULL,
  part int(11) NOT NULL,
  chapter int(11) NOT NULL,
  article int(11) NOT NULL,
  title varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  content varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  clickrate int(11) NOT NULL,
  PRIMARY KEY (lawid) USING BTREE,
  UNIQUE (lawid)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS loginTable;
CREATE TABLE loginTable  (
  phone varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  attempt int(11) NOT NULL,
  status int(11) NOT NULL,
  freezetime varchar(255) NULL DEFAULT NULL,
  PRIMARY KEY (phone) USING BTREE,
  FOREIGN KEY (phone) REFERENCES userTable (phone)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS consultTable;
CREATE TABLE consultTable  (
  id int(11) NOT NULL AUTO_INCREMENT,
  phone varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  title varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  query varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  type int(11) NOT NULL,
  result varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  time varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (id) USING BTREE,
  FOREIGN KEY (phone) REFERENCES userTable (phone)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS convrTable;
CREATE TABLE convrTable  (
  id int(11) NOT NULL AUTO_INCREMENT,
  phone varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  lawyerphone varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  convr varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  lawyerconvr varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  time varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  lawyertime varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (id) USING BTREE,
  FOREIGN KEY (phone) REFERENCES userTable (phone),
  FOREIGN KEY (lawyerphone) REFERENCES userTable (phone)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS sessionTable;
CREATE TABLE sessionTable  (
  id int(11) NOT NULL AUTO_INCREMENT,
  phone varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  title varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  content varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  status int(11) NOT NULL,
  PRIMARY KEY (id) USING BTREE,
  FOREIGN KEY (phone) REFERENCES userTable (phone)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


/*
 * MySQL数据库初始化设置
 * 为userTable插入管理员信息
 * 为statureTable导入《中华人民共和国刑法》信息
 */
INSERT INTO userTable
(phone, name, password, idno)
VALUES
('11111111111', 'admin', '111111', '111111111111111111');
LOAD XML LOCAL INFILE 'C:/Users/83470/Desktop/My Repository/Online-Legal-Consulting-System/legalsys/src/main/resources/stature/penalLaw.xml' INTO TABLE statureTable;

SET FOREIGN_KEY_CHECKS = 1;