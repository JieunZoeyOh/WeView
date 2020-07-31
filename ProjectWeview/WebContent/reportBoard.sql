drop table reportBoard;
create table reportBoard(
	no number(5) constraints reportBoard_no_pk primary key,
	type number(1) not null,
	board_no number(5),
	member_email varchar2(30) not null,
	reportdate date not null,
	reason_report varchar2(100) not null,
	report_count number(5),
	comm_no number(5),
	constraint reportBoard_board_no_fk foreign key(board_no) references board(no) on delete cascade,
	constraint reportBoard_member_email_fk foreign key(member_email) references member(email) on delete cascade 
);

select * from reportBoard;

select count(*) 
from (select * 
from (select rownum rnum, c.* 
from (select count(*) 
from reportBoard 
where type = 0 
group by board_no) c 
				) );

				select * 
				from (select rownum rnum, c.* 
						from (select * 
							  from (select board_no, report_count, title, reportBoard.no, reportBoard.member_email, reportdate, reason_report  
				     				from reportBoard , board 
									where reportBoard.board_no = board.no 
									and type=0
				     		  ) 
							  order by board_no desc , report_count asc
				     	  	 ) c 
						) a , 
						( 
						select board_no, max(report_count) 
						from REPORTBOARD 
						group by board_no order by board_no desc
						) m 
				where a.board_no >= 26 and a.board_no < =34 and a.board_no = m.board_no;			
				
				
				
select board_no 
					   from (select rownum rnum, b.* 
						    from (select board_no 
						  		  from reportBoard 
					 		   where type = 0 
					  		  group by board_no 
					   			  order by board_no desc 
					     		 ) b 
					   	  ) 
					   where rnum = 3