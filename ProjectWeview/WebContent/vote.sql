drop table vote;
create table vote(
	no number(5),
	board_no number(5) references board(no) on delete cascade,
	member_email varchar2(30) references member(email) on delete cascade,
	like1 number(2),
	like2 number(2),
	like3 number(2),
	primary key(no)
);

select * from vote;