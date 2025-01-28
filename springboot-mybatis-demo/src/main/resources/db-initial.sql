-- 显示所有数据库名称
show databases;

-- 删除数据库
drop database if exists db01;
-- 创建数据库
create database if not exists db01 default charset utf8mb4;

use db01;


-- 部门表
drop table IF EXISTS department;
create table if not exists department (
	id int signed primary key auto_increment comment '部门id',
	name varchar(20) not null comment '部门名称'
) comment '部门信息表';

insert into department values ('1', '市场部');
insert into department values ('2', '测试部');
insert into department values ('3', '开发部');


-- 工作职位表
drop table IF EXISTS job;
create table if not exists job (
	id int signed primary key auto_increment comment '工作职位id',
	name varchar(20) not null comment '职位名称'
) comment '工作职位信息表';

insert into job values ('1', '市场销售');
insert into job values ('2', '测试工程师');
insert into job values ('3', '开发工程师');
insert into job values ('4', '测试经理');
insert into job values ('5', '开发经理');
insert into job values ('6', '市场经理');


-- 员工表
drop table if EXISTS employee;
create table if not exists employee (
	id int primary key auto_increment comment '员工id',
    username varchar(20) not null unique comment '账号',
    password varchar(20) not null default '123456' comment '密码',
	name varchar(20) not null comment '员工姓名',
	gender tinyint(1) signed default null comment '性别',
    phone char(11) null comment '手机号',
    birthday date not null comment '出生日期',
	department_id int signed default null comment '所属部门id',
	job_id int signed default null comment '所属岗位id',
    join_date date null comment '员工入职时间',
    update_date datetime not null comment '数据最后修改时间',
    status tinyint signed not null default 0 comment '员工状态：0-未启用、1-正常、2-离职'
) comment '员工信息表';

insert into employee values (1, 'sunwukong', '123456', '孙悟空', 0, '13811111111', '1998-10-01', 3, 1, '1998-10-01', now(), 1);
insert into employee values (2, 'zhubajie', '123456', '猪八戒', 1, '13822222222', '2000-01-01', 3, 1, '2000-01-01', now(), 1);
insert into employee values (3, 'shaheshang', '123456', '沙和尚', 1, '13833333333', '2002-12-11', 2, 2, '2002-12-11', now(), 1);
insert into employee values (4, 'tangseng', '123456', '唐僧', 1, '13844444444', '1998-01-01', 2, 2, '1998-01-01', now(), 1);
insert into employee values (5, 'guanshiyin', '123456', '观世音菩萨', 0, '13855555555', '1890-01-01', 2, 3, '1890-01-01', now(), 2);
insert into employee values (6, 'puti', '123456', '菩提老祖', 0, '13866666666', '1898-10-01', 1, 3, '1898-10-01', now(), 2);
insert into employee values (7, 'yudi', '123456', '玉皇大帝', 1, '13877777777', '1958-10-01', 1, 4, '1958-10-01', now(), 2);
insert into employee values (8, 'wangmu', '123456', '王母娘娘', 0, '13888888888', '1958-10-10', 2, 5, '1958-10-10', now(), 2);
insert into employee values (9, 'laojun', '123456', '太上老君', 1, '13899999999', '1988-01-01', 3, 5, '1988-01-01', now(), 2);
insert into employee values (10, 'rulai', '123456', '如来佛祖', 0, '13800000000', '1598-01-01', 3, 6, '1598-01-01', now(), 2);
insert into employee values (11, 'heixiongjing', '123456', '黑熊精', 1, null, '2000-10-11', 2, 6, null, now(), 0);
insert into employee values (12, 'huangpaoguai', '123456', '黄袍怪', 1, null, '1985-10-21', 1, 6, null, now(), 0);


-- 员工工资表
drop table if EXISTS salary;
create table salary (
	id int signed primary key auto_increment comment '工资表id',
	employee_id int signed not null comment '员工id',
	salary int signed not null comment '工资数额'
) comment '工资信息表';

insert into salary values ('1', '1', '2100');
insert into salary values ('2', '2', '2000');
insert into salary values ('3', '3', '5000');
insert into salary values ('4', '4', '1999');
insert into salary values ('5', '5', '1900');
insert into salary values ('6', '6', '3000');
insert into salary values ('7', '7', '5500');
insert into salary values ('8', '8', '2000');
insert into salary values ('9', '9', '1500');
insert into salary values ('10', '10', '4000');
insert into salary values ('11', '11', '2600');
insert into salary values ('12', '12', '5300');


-- 员工工作经历表
drop table if EXISTS emp_expr;
create table emp_expr (
	id int signed primary key auto_increment comment '员工经历表id',
	employee_id int signed not null comment '员工id',
	begin_date date not null comment '开始时间',
	end_date date not null comment '结束时间',
	company varchar(50) not null comment '所在单位',
    job varchar(50) not null comment '职位/岗位'
) comment '员工经历表';

insert into emp_expr values (1, 1, '1900-01-01', '2000-12-31', '花果山', '猴王');
insert into emp_expr values (2, 1, '2000-01-01', '2003-12-31', '天宫', '弼马温');
insert into emp_expr values (3, 1, '2005-01-01', '2020-12-31', '东土大唐', '大师兄');


-- 班级表
drop table if EXISTS clazz;
create table clazz (
	id int signed primary key auto_increment comment '班级id',
    name varchar(50) not null comment '班级名称',
    room varchar(10) not null comment '班级教室',
	employee_id int signed null comment '班主任-员工id-关联员工表',
	begin_date date not null comment '开课时间',
	end_date date not null comment '结课时间',
    status tinyint signed null default 0 comment '班级状态：0-未开班、1-在读、2-结业',
	last_update_time datetime null default now() comment '最后操作时间',
    create_time datetime null default now() comment '创建时间'
) comment '班级表';

insert into clazz values(1, '第1届培训班', '101', 1, '2000-01-01', '2000-12-31', 2, default, default);
insert into clazz values(2, '第2届培训班', '308', 10, '2001-01-01', '2001-12-31', 2, default, default);
insert into clazz values(3, '第3届培训班', '1008', 5, '2002-01-01', '2002-12-31', 1, default, default);


-- 学历表
drop table IF EXISTS edu;
create table if not exists edu (
	id int signed primary key auto_increment comment '学历id',
	name varchar(20) not null comment '学历类型',
	last_update_time datetime null default now() comment '最后操作时间',
    create_time datetime null default now() comment '创建时间'
) comment '学历表';

insert into edu values (1, '小学', default, default);
insert into edu values (2, '初中', default, default);
insert into edu values (3, '中专', default, default);
insert into edu values (4, '高中', default, default);
insert into edu values (5, '大专', default, default);
insert into edu values (6, '本科', default, default);
insert into edu values (7, '硕士', default, default);
insert into edu values (8, '博士', default, default);

-- 学生表
drop table if EXISTS student;
create table student (
	id int signed primary key auto_increment comment '学生id',
    username varchar(20) not null unique comment '账号',
    password varchar(20) not null default '123456' comment '密码',
    name varchar(50) not null comment '学生姓名',
    no varchar(10) not null comment '学生编号',
	clazz_id int signed null comment '所属班级-关联班级表',
	gender tinyint not null comment '性别：0-女、1-男',
	phone varchar(11) null comment '手机号',
    edu_id tinyint signed null comment '学历信息-关联学历表',
	last_update_time datetime null default now() comment '最后操作时间',
    create_time datetime null default now() comment '创建时间'
) comment '班级表';

insert into student values(1, 'zhangsan', '123456', '张三', 'A0001', 1, 1, '13111111111', 8, default, default);
insert into student values(2, 'lisi', '123456', '李四', 'A0002', 2, 0, '13111111112', 1, default, default);
insert into student values(3, 'wangwu', '123456', '王五', 'A0003', 3, 0, '13111111113', 5, default, default);
