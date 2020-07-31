drop table category;

CREATE TABLE category(
	no number(5) not null,
	name varchar2 (50) not null,
	PRIMARY KEY(no)
);

insert into CATEGORY values(2,'패션의류/잡화');

select * from category;


alter table category modify (name varchar2(100));

select * from category;

insert into category
values(1,'전체');
insert into category
values(2,'패션의류&잡화');

insert into category
values(3,'뷰티');

insert into category
values(4,'출산 & 유아동');

insert into category
values(5,'주방 & 생활용품');

insert into category
values(6,'홈인테리어');

insert into category
values(7,'가전/디지털');

insert into category
values(8,'스포츠 & 헬스 & 건강');

insert into category
values(9,'자동차 & 용품');

insert into category
values(10,'반려동물용품');