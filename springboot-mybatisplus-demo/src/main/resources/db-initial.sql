show databases ;

drop database if exists db02;

create schema if not exists db02;

use db02;

drop table if exists user;
create table if not exists user (
    id int signed primary key auto_increment comment '用户id',
    username varchar(20) unique not null comment '登录账号',
    password varchar(20) not null default '123456' comment '密码',
    name varchar(10) not null comment '姓名',
    balance double signed not null default 0 comment '存款',
    birthday date null comment '生日',
    gender tinyint signed null comment '性别：1-男，0-女',
    status tinyint signed not null default 0 comment '状态：0-禁用，1-启用',
    create_time datetime not null default now() comment '创建时间',
    last_update_time datetime not null default now() comment '最后修改时间',
    delete_flag tinyint not null default 0 comment '逻辑删除：1-删除、0-未删除'
) comment '用户表';

insert into user values (1, 'zhangsan', default, '张三', 5000, '1999-10-10', 1, 1, default, default, default);
insert into user values (2, 'lisi', default, '李四', 3500, '1989-10-10', 0, 1, default, default, default);
insert into user values (3, 'wangwu', default, '王五', 8000, '1979-10-10', 1, 1, default, default, default);

select * from user;

select *
from user
where delete_flag = 0
  and status = 1
  and balance > 4000
  and username like '%wu%';

update user
set balance = 2000
where username = 'lisi';

update user
set balance = balance - 200,
    gender = 0
where id in (1,2);

select *
from user
where username like '%sun%'
  and status = 1
  and balance > 100
  and balance < 10000000;



drop table if exists address;
create table if not exists address (
    id int signed primary key auto_increment comment '地址id',
    user_id int signed not null comment '所属用户id',
    province varchar(20) not null comment '省',
    city varchar(20) not null comment '市',
    county varchar(20) not null comment '县、区',
    street varchar(100) not null comment '详细地址',
    mobile varchar(11) not null comment '手机号',
    contact varchar(10) not null comment '联系人姓名',
    is_default tinyint signed default 0 comment '是否默认：1-默认、0-否',
    notes varchar(100) null comment '备注',
    create_time datetime not null default now() comment '创建时间',
    last_update_time datetime not null default now() comment '最后修改时间',
    delete_flag tinyint not null default 0 comment '逻辑删除：1-删除、0-未删除'
) comment '用户表';

insert into address
values
    (1, 1, '北京市', '北京市', '海淀区', '光华路1号院', '13111111111', '张三', 1, default, default, default, default),
    (2, 2, '上海市', '上海市', '闵行区', '普陀山1号', '13111111111', '张三', 0, default,default, default, default),
    (3, 1, '河南省', '郑州市', '郑东新区', '大玉米街道1号', '13122222222', '李四', 1, default,default, default, default);
