-- 使用Mysql数据库

-- 数据库初始化脚本

-- 创建数据库
DROP DATABASE IF EXISTS db_nighty;
CREATE DATABASE db_nighty;
-- 使用数据库
use db_nighty;

-- 创建表
# DROP TABLE t_user;
CREATE TABLE t_user(
  id VARCHAR(36) NOT NULL COMMENT '用户ID',
  nickname NVARCHAR(50) NOT NULL COMMENT '登录后显示的昵称，可以为中文',
  username VARCHAR(50) NOT NULL UNIQUE COMMENT '登录时的用户名，不能包含中文',
  password VARCHAR(120) NOT NULL COMMENT '登录密码，存的是MD5加密后的密码',
  salt VARCHAR(120) NOT NULL COMMENT '密码盐',
  email VARCHAR(30) NOT NULL UNIQUE COMMENT '邮箱，可以用来登录或重置密码',
  phone VARCHAR(20) NOT NULL COMMENT '手机号码',
  register_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  last_login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次登录的时间',
  PRIMARY KEY (id)
) DEFAULT CHARSET = utf8 COMMENT '用户表';

DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role(
  id VARCHAR(36) NOT NULL COMMENT '角色ID',
  name VARCHAR(50) NOT NULL COMMENT '角色名',
  description TEXT COMMENT '角色描述',
  type VARCHAR(20) COMMENT '角色类型，"admin"表示管理员，"user"表示用户，可扩展',
  PRIMARY KEY (id)
) DEFAULT CHARSET = utf8 COMMENT '角色表';

DROP TABLE IF EXISTS t_authority;
CREATE TABLE t_authority(
  id VARCHAR(36) NOT NULL COMMENT '权限ID',
  name VARCHAR(50) NOT NULL COMMENT '权限名',
  description TEXT COMMENT '权限描述',
  PRIMARY KEY (id)
) DEFAULT CHARSET = utf8 COMMENT '权限表';

DROP TABLE IF EXISTS t_role_authority;
CREATE TABLE t_role_authority(
  id VARCHAR(36) NOT NULL COMMENT 'ID',
  role_id VARCHAR(36) NOT NULL COMMENT '角色ID',
  authority_id VARCHAR(36) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (id)
) DEFAULT CHARSET = utf8 COMMENT '角色权限关系表';

DROP TABLE IF EXISTS t_role_user;
CREATE TABLE t_role_user(
  id VARCHAR(36) NOT NULL COMMENT 'ID',
  role_id VARCHAR(36) NOT NULL COMMENT '角色ID',
  user_id VARCHAR(36) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (id)
) DEFAULT CHARSET = utf8 COMMENT '角色用户关系表';


-- 初始化数据

-- 初始化权限表
INSERT INTO t_authority (id,name,description) VALUES ('20170326164700','readSystemInfo','系统读权限');
INSERT INTO t_authority (id,name,description) VALUES ('20170326164701','writeSystemInfo','系统写权限');

-- 初始化角色表
INSERT INTO t_role (id,name,description,type) VALUES ('20170326164710','admin','管理员','admin');
INSERT INTO t_role (id,name,description,type) VALUES ('20170326164711','guest','用户','user');

-- 初始化用户表
INSERT INTO t_user (id,nickname,username,password,salt,email,phone)
VALUES ('1','aa','aa','24C1FD38DB4B717D4E4B4A838A812102','salt001','nightynight_cc@163.com','15522331234');
INSERT INTO t_user (id,nickname,username,password,salt,email,phone)
VALUES ('6b4ff786-401c-40f4-a439-87c9decabd89','cc','cc','5715B972EFCFDA1C5C564840AF5C522F','3n1F5vgAom','1024234001@qq.com','12312345678');

-- 初始化角色权限关系表
INSERT INTO t_role_authority (id, role_id, authority_id) VALUES ('20170418230900','20170326164710','20170326164700');
INSERT INTO t_role_authority (id, role_id, authority_id) VALUES ('20170418230901','20170326164710','20170326164701');

-- 初始化角色用户关系表
INSERT INTO t_role_user (id, role_id, user_id) VALUES ('20170418231000','20170326164710','1');
INSERT INTO t_role_user (id, role_id, user_id) VALUES ('20170418231001','20170326164711','6b4ff786-401c-40f4-a439-87c9decabd89');