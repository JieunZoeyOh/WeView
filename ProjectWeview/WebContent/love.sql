drop table love;
create table love(
	no number(5),
	member_email varchar2(30) references member(email) on delete cascade,
	board_no number(5) references board(no) on delete cascade,
	primary key(no)
);

select * from love;