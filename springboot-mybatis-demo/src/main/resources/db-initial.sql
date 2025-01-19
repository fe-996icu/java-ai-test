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
	dept_id int signed primary key auto_increment comment '部门id',
	dept_name varchar(20) not null comment '部门名称'
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
	name varchar(20) not null comment '员工姓名',
	gender tinyint(1) signed default null comment '性别',
    phone char(11) null comment '手机号',
    birthday date not null comment '出生日期',
	dept_id int signed default null comment '所属部门id',
	job_id int signed default null comment '所属岗位id',
    join_date date null comment '员工入职时间',
    update_date datetime not null comment '数据最后修改时间',
    status tinyint signed not null default 0 comment '员工状态：0-未启用、1-正常、2-离职'
) comment '员工信息表';

insert into employee values (1, '孙悟空', 0, '13811111111', '1998-10-01', 3, 1, '1998-10-01', now(), 1);
insert into employee values (2, '猪八戒', 1, '13822222222', '2000-01-01', 3, 1, '2000-01-01', now(), 1);
insert into employee values (3, '沙和尚', 1, '13833333333', '2002-12-11', 2, 2, '2002-12-11', now(), 1);
insert into employee values (4, '唐僧', 1, '13844444444', '1998-01-01', 2, 2, '1998-01-01', now(), 1);
insert into employee values (5, '观世音菩萨', 0, '13855555555', '1890-01-01', 2, 3, '1890-01-01', now(), 2);
insert into employee values (6, '菩提老祖', 0, '13866666666', '1898-10-01', 1, 3, '1898-10-01', now(), 2);
insert into employee values (7, '玉皇大帝', 1, '13877777777', '1958-10-01', 1, 4, '1958-10-01', now(), 2);
insert into employee values (8, '王母娘娘', 0, '13888888888', '1958-10-10', 2, 5, '1958-10-10', now(), 2);
insert into employee values (9, '太上老君', 1, '13899999999', '1988-01-01', 3, 5, '1988-01-01', now(), 2);
insert into employee values (10, '如来佛祖', 0, '13800000000', '1598-01-01', 3, 6, '1598-01-01', now(), 2);
insert into employee values (11, '黑熊精', 1, null, '2000-10-11', 2, 6, null, now(), 0);
insert into employee values (12, '黄袍怪', 1, null, '1985-10-21', 1, 6, null, now(), 0);

-- 员工工资表
drop table if EXISTS salary;
create table salary (
	id int signed primary key auto_increment comment '工资表id',
	emp_id int signed not null comment '员工id',
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