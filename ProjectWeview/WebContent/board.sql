drop table board;
create table board(
	no number(5),
	title varchar2(100),
	content varchar2(3000),
	category_no number(5) references category(no) on delete cascade,
	member_email varchar2(30) references member(email) on delete cascade,
	report number(5),
	regidate date,
	image1 varchar2(255) default null,
	image2 varchar2(255) default null,
	image3 varchar2(255) default null,
	productName1 varchar2(30) default null,
	productName2 varchar2(30) default null,
	productName3 varchar2(30) default null,
	like1 number(5),
	like2 number(5),
	like3 number(5),
	views number(5),
	primary key(no)
);


select * from board;
insert into board values (1, '�� �߿� ���� �� ����?', '�� ��� ������ ������ ��õ ��..',2, 'user2@naver.com', 0, sysdate, null, null, null, '��ǰ1','��ǰ2','��ǰ3',42,32,14,423);
delete from board where no=1;

alter table board modify (content varchar2(3000));
alter table board modify (productName1 varchar2(500));
alter table board modify (productName2 varchar2(500));
alter table board modify (productName3 varchar2(500));
alter table board modify (title varchar2(500));


insert into BOARD
values(1,'ũ����vs��û','��Ƽ�� �����ǵ� ��� ����?',1,'member1@naver.com',0,sysdate,null,null,null,'�귣���/1931 OBJECT JEANS','TOFFEE/T9-WDDP',null,0,0,0,56);
insert into BOARD
values(2,'�ֵ� ������ ��������?','�������� ����?',1,'member2@naver.com',0,sysdate,null,null,null,' ADD/AB6215C','ADD/ASU',null,0,0,0,45);
insert into BOARD
values(3,'�� ��°� �������?','���� �� ��������? LMC�� ���� �������̿���',1,'member3@naver.com',0,sysdate,null,null,null,' LMC/20SS_ANMO_BK','THISISNEVERTHAT/TN20S0137','THISISNEVERTHAT/MT20ST00166',0,0,0,7);
insert into BOARD
values(4,'ģ�� ������ ���� �������?','����ּ���!',1,'member4@naver.com',0,sysdate,null,null,null,'��Ű ��ĸ ','���Ž�91 ��ũ ����� ĸ','�����ΰ�LA������Ŭ����',0,0,0,23);
insert into BOARD
values(5,'��� ��︱���?','�Ұ��� �� �������� ��°ǵ� ���� ��︱���? ��������� ���ڽ��ϴ�.',1,'member5@naver.com',0,sysdate,null,null,null,' PARTIMENTO/Hair Line Stripe',' COVERNAT/C1901SS01BM',null,0,0,0,1);

insert into BOARD
values(6,'ƾƮ ��&��õ','�����س����� �� �� �ֱ� �ѵ� ������ ��ٰ� �ȹٸ��� �ʹ� �Ʊ���� �񱳿� ��õ ��Ź�帳�ϴ�',2,'member1@naver.com',0,sysdate,null,null,null,'�̴Ͻ����� ���� �ɹ� ƾƮ','�̴Ͻ����� ���� �ĸ��� ƾƮ','��ŲǪ�� �丶�� �� ���� ƾƮ',0,0,0,0);
insert into BOARD
values(7,'���� ����ƾƮvs ���� ����ƾƮ HD','1. ���ӷ��� ��� �� ������?2. ������ ��� �� ������?3. ����ƾƮ �߿��� �������濡 �� ��︮�� ���� ����ƾƮ HD�߿��� �������濡 �߾�︮�� ���� ��õ�� �ּ���~',2,'member2@naver.com',0,sysdate,null,null,null,'�ڿܼ�ũ��1','�ڿܼ�ũ��2','�ڿܼ�ũ��3',0,0,0,0);
insert into BOARD
values(8,'�ڿܼ� ������ ��õ ���ּ���','�Ǽ��Դϴ�.',2,'member3@naver.com',0,sysdate,null,null,null,'���ٵ��Ͽ콺/���۾���Ƽ������ ',' ��ŲǪ��/�丶�伱ũ��',null,0,0,0,0);
insert into BOARD
values(9,'���ũ�� ��� �������?','���ũ�� ��õ�޾Ƽ� ����� �� �ø��ϴ�.���帧�ְ� �⸧���ִ� �Ǻο���',2,'member4@naver.com',0,sysdate,null,null,null,'��ϸ�/��Ϸ� ac','�̻� /�ʺ����� ','null',0,0,0,0);
insert into BOARD
values(10,'���ũ��!','��� �������?',2,'member5@naver.com',0,sysdate,null,null,null,'XTM ��Ƽ ���(�����Ǻο�)','������ ���� ��� ũ��','null',0,0,0,0);

insert into BOARD
values(11,'�� ������ ����Ͻź� ��Ű���?','��� ������ ��ε˴ϴ�.',3,'member1@naver.com',0,sysdate,'ch1.PNG','ch2.PNG',null,'�䵵�� ������ �ֵ��� ','������ ��Ƽ����Ʈ ���� ����',null,0,0,0,0);
insert into BOARD
values(12,'��ֱⰡ �� ���� ����ּ���Ф�','�ʹ� ��ε˴ϴ٤̤�',3,'member2@naver.com',0,sysdate,null,null,null,'������Ʈ �� �����ǰ� PPSU ����','���� PPSU ����',null,0,0,0,0);
insert into BOARD
values(13,'�����ҵ��� ��õ ��Ź�帳�ϴ�.','ù��° ��ǰ�� �����ϰ� �ְ� ������ ��ǰ�� ���� ���ϰ� �־��̤�',3,'member3@naver.com',0,sysdate,null,null,null,'������ �÷��� �����ҵ���','NEW �����÷��� �����ҵ���','�����޵��ɾ� ����Ʈ�� ���� �ҵ���',0,0,0,0);
insert into BOARD
values(14,'�ʹ� ��ε˴ϴ� ��ī �� ������ �� ����ּ���','ã�Ɣf�µ� �� ����ǰ�� ���� ����!',3,'member4@naver.com',0,sysdate,null,null,null,'���� ����3 �𷰽� ������','������ ����Ʈ V2������',null,0,0,0,0);
insert into BOARD
values(15,'����ü�� ����ּ���!','���� ù��°�ε� �Ƴ��� �ι�°���� �������� ����ּ���',3,'member5@naver.com',0,sysdate,null,null,null,'������ ���ܽ�','������ Ŭ��',null,0,0,0,0);

insert into BOARD
values(16,'�ְ��� �ͼ����?','ã�ƺ��ϱ� �̰� ���ٴµ� ����� ���ź� ��Ű���?',4,'member1@naver.com',0,sysdate,'mix1.PNG','mix2.PNG',null,'�ʸ������� �ʰ�� �ͼ��� HR3752/00','�ʸ��� HR3752 �����ͼ���',null,0,0,0,0);
insert into BOARD
values(17,'���� �� ����ּ���!','�̹��� ���� ���� ����� �մϴ� ��õ ��Ź�����.',4,'member2@naver.com',0,sysdate,null,null,null,'����Ʈ ��3�� ���ٳ���','���߷� �����','���ν� ���ٳ���',0,0,0,0);
insert into BOARD
values(18,'���������̱� ��õ�̿�~','����ּ���~',4,'member3@naver.com',0,sysdate,null,null,null,'�ʸ��� ���������̾� hd9220/22','������� ���������̾� ��뷮 5.5l',null,0,0,0,0);
insert into BOARD
values(19,'�δ��� ������ ���ǿ���?','�뵵�� �����Դϴ�.',4,'member4@naver.com',0,sysdate,null,null,null,'�����̾� ��� �δ��� ���ⷹ����','���ⷹ���� �δ��� �������ĵ�',null,0,0,0,0);
insert into BOARD
values(20,'����� ���������̱�?','�̹��� ���븦 �����ϴµ� ���������̱� �� ���� �Դϴ�. ����ǰ �� ����ּ���',4,'member5@naver.com',0,sysdate,null,null,null,'���۸���3L ��뷮 ���������̱� ����������','������ ���������̾�',null,0,0,0,0);

insert into BOARD 
values(21,'�濡 ���� ��Ƽ�� ��������~','������ְ� �ͼ� �׷��µ� �� ����ּ��� �������̰� ���ؿ��',5,'member1@naver.com',0,sysdate,'pa1.PNG','pa2.PNG',null,'��ö ��Ƽ�� ��Ƽ��','���� ����ε� ��Ƽ��',null,0,0,0,0);
insert into BOARD
values(22,'ħ�뿷 ���ٵ� �̰� ���?','������ ������ ������ ���ƿ� ���߿� ����ּ���!',5,'member2@naver.com',0,sysdate,'jang.PNG','jang2.PNG',null,'���Ϻ� VARDI �彺�ĵ�','���Ϻ� VARDI �彺�ĵ�',null,0,0,0,0);
insert into BOARD
values(23,'�Žǿ� ����vsī��Ʈ','�������?',5,'member3@naver.com',0,sysdate,'lat.PNG','pa.PNG',null,'���� ��ź��Ÿ�� ����','���̺�/�丣�þ� ����ũ ī��Ʈ',null,0,0,0,0);
insert into BOARD
values(24,'�Žǿ� ���ð� ��õ ��Ź�����!','��︱���?',5,'member4@naver.com',0,sysdate,'nw1.PNG','nw2.PNG',null,'������ǳ ���ð�','������ ���� ��Ʋ ���ð�',null,0,0,0,0);
insert into BOARD
values(25,'�濡 �ɾ�� ����','��� ���ܱ��?',5,'member5@naver.com',0,sysdate,'jsp.PNG','nd.PNG',null,' 1984�� ���� ������','���� �� ���� �������� ������',null,0,0,0,0);

insert into BOARD
values(26,'������ ���� ���� �� �����ּ����','������ ������11�̳� �������� se�� ����ּ���!���� �޴��� �ѹ� ��� 2������ ����� ������ ���ϰ� ������ ���� ����',6,'member1@naver.com',0,sysdate,'ip11m.PNG','se2.PNG',null,'������11','������se2',null,0,0,0,0);
insert into BOARD
values(27,'������ �����ε� ������se2 �����?','������s20���� ���� ������ ���� se�ϱ��? ������ �ѹ� ����������� ��������...',6,'member2@naver.com',0,sysdate,'sg20.PNG','se1.PNG',null,'������20','������se2',null,0,0,0,0);
insert into BOARD
values(28,'������11 �� ����ּ����','���� ������ֿ� �ɷǾ��̤� ����̳� �����̳� Ȥ�ó� ���� ������� �����ǳ׿�.. �� �ƴϸ� �������� ���',6,'member3@naver.com',0,sysdate,'ip11p.PNG','ip11w.PNG','ip11b.PNG','������11����','������11ȭ��Ʈ','������11��',0,0,0,0);
insert into BOARD
values(29,'���ӱ� �� ����ּ��� ����ġ vs �ý�4','����ġ�� �������̶� �ϸ� ��մٴµ� ���� ȥ�� ���ŵ�� ȥ�� ���⿡�� �� �׷���',6,'member4@naver.com',0,sysdate,'sw.PNG','ps4.PNG',null,'���ٵ�����ġ','�Ҵ� ps4',null,0,0,0,0);
insert into BOARD
values(30,'�׷� ��ġ �� ��õ���ּ���','14��ġ ��� �� ����ұ��?',6,'member5@naver.com',0,sysdate,'gr14.PNG','gr17.PNG',null,'�׷� 14��ġ','�׷�17��ġ',null,0,0,0,0);

insert into BOARD
values(31,'���ѷ� �ƹ��ų� �絵 �ǳ���?','���� ���� ���� ã�Ҵµ� ���������?',7,'member1@naver.com',0,sysdate,'gre.PNG','bkp.PNG','eva.PNG','�׸��� 2.0 ���ѷ�','���� �� �ϵ� ���ѷ�','EVA �ʶ��׽� ���ѷ�',0,0,0,0);
insert into BOARD
values(32,'�䰡�� ��õ�� �ּ���','��� ȿ���� �������? �ΰ� ���� ������',7,'member2@naver.com',0,sysdate,'ri.PNG','tra.PNG',null,'��Ÿ �ʶ��׽���','TRATAC �䰡��',null,0,0,0,0);
insert into BOARD
values(33,'�︰�� �����ּ���! �ܹ��������� ���� �������?','�׳� �Ѱ� ����? �ƴϸ� �귣��? ',7,'member3@naver.com',0,sysdate,'dy.PNG','ac.PNG',null,'���̳��� ���� ���̳�','��Ƽ���� �۷�Ÿ��',null,0,0,0,0);
insert into BOARD
values(34,'���׸ӽ� ���� ������?','�ｺ�� ���ð��� ������  Ȥ�ó� ����ǰ�� ���� ��õ�Ͻô°� ������ ��ۿ� ���� �޾��ּ���~ ',7,'member4@naver.com',0,sysdate,'xml.PNG','ew503.PNG',null,'XQIAO ���̽� ���׸ӽ�','��ŷ�ӽ� EW-503',null,0,0,0,0);


