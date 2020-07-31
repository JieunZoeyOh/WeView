drop table notice;
create table notice (
	no number(5) primary key,
	title varchar2(100) not null,
	content varchar2(3000) not null,
	regidate date not null,
	image1 varchar2(255),
	image2 varchar2(255),
	image3 varchar2(255)
);
