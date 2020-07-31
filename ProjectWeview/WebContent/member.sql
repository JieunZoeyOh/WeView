drop table member;

CREATE TABLE member(
	email	varchar2(50),
	pwd	    varchar2(30)	not null,
	jumin	varchar2(14)	not null,
	phone	varchar2(13)	not null,
	gender	varchar2(5)	    not null,
	name	varchar2(20)	not null,
	block	number(2)	DEFAULT 0 not null,
	image	varchar2(255),
	PRIMARY KEY(email)
);
delete from member;
insert into member values('admin','1234','0','0','남','관리자',0,null);
insert into member values('user1@naver.com','1111','980913-1234567','010-1234-5678','남','사용자1',0,null);
insert into member values('user2@naver.com','2222','990909-1819231','010-2345-1456','남','사용자2',0,null);
insert into member values('user3@naver.com','3333','050113-4234567','010-5234-4326','여','사용자3',0,null);
select * from member;

insert into member
values('admin','1234','910908-1234567','010-2342-2342','1','관리자',0,null);

insert into member
values('member1@naver.com','1234','910926-1234567','010-2342-2342','2','배수지',0,null);

insert into member
values('member2@naver.com','1234','920228-1234567','010-2132-2156','2','전지현',0,null);

insert into member
values('member3@naver.com','1234','880908-1234567','010-7688-2345','1','원빈',0,null);

insert into member
values('member4@naver.com','1234','950608-1234567','010-7866-9999','1','이민호',0,null);

insert into member
values('member5@naver.com','1234','871017-1234567','010-2133-5656','1','박보검',0,null);

