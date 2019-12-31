create database test;
use test;

create table student(
	id int primary key auto_increment,
	sNumber int not null,
	name varchar(20) not null,
	age int,
	sex enum('男','女'),
	class int,
	grade varchar(20),
	profession varchar(20),
	academy varchar(20),
	tName varchar(20),
	phone varchar(20),
	email varchar(30),
	birthday date default '2019-12-12',
	location varchar(30)
);