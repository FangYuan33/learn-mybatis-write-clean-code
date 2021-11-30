## 1. 介绍
掘金小册《玩转 MyBatis：深度解析与定制》学习笔记

## 2. 待执行SQL
CREATE TABLE user (
id varchar(32) NOT NULL,
version int(11) NOT NULL DEFAULT '0' COMMENT '乐观锁标志位',
name varchar(32) NOT NULL,
age int(3) DEFAULT NULL,
birthday datetime DEFAULT NULL,
department_id varchar(32) NOT NULL,
sorder int(11) NOT NULL DEFAULT '1' COMMENT '自定义排序值',
deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除字段',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO user(id, version, name, age, birthday, department_id, sorder, deleted) VALUES ('09ec5fcea620c168936deee53a9cdcfb', 0, '阿熊', 18, '2003-08-08 10:00:00', '18ec781fbefd727923b0d35740b177ab', 1, 0);
INSERT INTO user(id, version, name, age, birthday, department_id, sorder, deleted) VALUES ('5d0eebc4f370f3bd959a4f7bc2456d89', 0, '老狗', 30, '1991-02-20 15:27:20', 'ee0e342201004c1721e69a99ac0dc0df', 1, 0);

CREATE TABLE department (
id varchar(32) NOT NULL,
name varchar(32) NOT NULL,
tel varchar(18) DEFAULT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tbl_department(id, name, tel) VALUES ('00000000000000000000000000000000', '全部部门', '-');
INSERT INTO tbl_department(id, name, tel) VALUES ('18ec781fbefd727923b0d35740b177ab', '开发部', '123');
INSERT INTO tbl_department(id, name, tel) VALUES ('53e3803ebbf4f97968e0253e5ad4cc83', '测试产品部', '789');
INSERT INTO tbl_department(id, name, tel) VALUES ('ee0e342201004c1721e69a99ac0dc0df', '运维部', '456');





