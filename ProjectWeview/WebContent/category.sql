drop table category;

CREATE TABLE category(
	no number(5) not null,
	name varchar2 (50) not null,
	PRIMARY KEY(no)
);

insert into CATEGORY values(2,'�м��Ƿ�/��ȭ');

select * from category;


alter table category modify (name varchar2(100));

select * from category;

insert into category
values(1,'��ü');
insert into category
values(2,'�м��Ƿ�&��ȭ');

insert into category
values(3,'��Ƽ');

insert into category
values(4,'��� & ���Ƶ�');

insert into category
values(5,'�ֹ� & ��Ȱ��ǰ');

insert into category
values(6,'Ȩ���׸���');

insert into category
values(7,'����/������');

insert into category
values(8,'������ & �ｺ & �ǰ�');

insert into category
values(9,'�ڵ��� & ��ǰ');

insert into category
values(10,'�ݷ�������ǰ');