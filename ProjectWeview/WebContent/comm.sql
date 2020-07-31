drop table comm;
create table comm(
	no number(5),
	board_no number(5) references board(no) on delete cascade,
	content varchar2(255),
	member_email varchar2(30) references member(email) on delete cascade,
	report number(5),
	regidate date,
	love number(5),
	hate number(5),
	image varchar2(255),
	primary key(no)
);

select * from comm;

insert into comm values(1, 1, '2가 더 좋음요 강추', 'user2@naver.com',0,sysdate,3,0,'spider.jpg');