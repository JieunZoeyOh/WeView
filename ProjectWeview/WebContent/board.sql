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
insert into board values (1, '둘 중에 뭐가 더 나음?', '돈 없어서 가성비 좋은거 추천 좀..',2, 'user2@naver.com', 0, sysdate, null, null, null, '상품1','상품2','상품3',42,32,14,423);
delete from board where no=1;

alter table board modify (content varchar2(3000));
alter table board modify (productName1 varchar2(500));
alter table board modify (productName2 varchar2(500));
alter table board modify (productName3 varchar2(500));
alter table board modify (title varchar2(500));


insert into BOARD
values(1,'크림진vs연청','흰티에 입을건데 어떤걸 살까요?',1,'member1@naver.com',0,sysdate,null,null,null,'브랜디드/1931 OBJECT JEANS','TOFFEE/T9-WDDP',null,0,0,0,56);
insert into BOARD
values(2,'애드 슬랙스 사고싶은데?','무슨색을 살까요?',1,'member2@naver.com',0,sysdate,null,null,null,' ADD/AB6215C','ADD/ASU',null,0,0,0,45);
insert into BOARD
values(3,'뭘 사는게 좋을까요?','뭐가 더 괜찮나요? LMC는 지금 세일중이에요',1,'member3@naver.com',0,sysdate,null,null,null,' LMC/20SS_ANMO_BK','THISISNEVERTHAT/TN20S0137','THISISNEVERTHAT/MT20ST00166',0,0,0,7);
insert into BOARD
values(4,'친구 선물로 뭐가 좋을까요?','골라주세요!',1,'member4@naver.com',0,sysdate,null,null,null,'루키 볼캡 ','레거시91 테크 스우시 캡','스몰로고LA다저스클린업',0,0,0,23);
insert into BOARD
values(5,'어떤게 어울릴까요?','소개팅 때 입으려고 사는건데 뭐가 어울릴까요? 깔끔했으면 좋겠습니다.',1,'member5@naver.com',0,sysdate,null,null,null,' PARTIMENTO/Hair Line Stripe',' COVERNAT/C1901SS01BM',null,0,0,0,1);

insert into BOARD
values(6,'틴트 비교&추천','생각해놓은게 몇 개 있긴 한데 여러개 샀다가 안바르면 너무 아까워서 비교와 추천 부탁드립니다',2,'member1@naver.com',0,sysdate,null,null,null,'이니스프리 에코 꽃물 틴트','이니스프리 에코 후르츠 틴트','스킨푸드 토마토 쿨 젤리 틴트',0,0,0,0);
insert into BOARD
values(7,'립톤 겟잇틴트vs 립톤 겟잇틴트 HD','1. 지속력은 어떤게 더 좋나요?2. 성분은 어떤게 더 좋나요?3. 겟잇틴트 중에서 가을웜톤에 잘 어울리는 색과 겟잇틴트 HD중에서 가을웜톤에 잘어울리는 색좀 추천해 주세요~',2,'member2@naver.com',0,sysdate,null,null,null,'자외선크림1','자외선크림2','자외선크림3',0,0,0,0);
insert into BOARD
values(8,'자외선 차단제 추천 해주세요','건성입니다.',2,'member3@naver.com',0,sysdate,null,null,null,'에뛰드하우스/슈퍼아쿠아선가드라구 ',' 스킨푸드/토마토선크림',null,0,0,0,0);
insert into BOARD
values(9,'비비크림 어떤게 좋을까요?','비비크림 추천받아서 사려고 글 올립니다.여드름있고 기름기있는 피부예요',2,'member4@naver.com',0,sysdate,null,null,null,'토니모리/토니랩 ac','미샤 /초보양비비 ','null',0,0,0,0);
insert into BOARD
values(10,'비비크림!','어떤게 좋을까요?',2,'member5@naver.com',0,sysdate,null,null,null,'XTM 멀티 비비(보통피부용)','엠도씨 맨즈 비비 크림','null',0,0,0,0);

insert into BOARD
values(11,'이 유모차 사용하신분 계신가요?','어떤게 좋을지 고민됩니다.',3,'member1@naver.com',0,sysdate,'ch1.PNG','ch2.PNG',null,'페도라 유모차 쌍둥이 ','유모차 시티셀렉트 럭스 차콜',null,0,0,0,0);
insert into BOARD
values(12,'울애기가 쓸 젖병 골라주세요ㅠㅠ','너무 고민됩니다ㅜㅜ',3,'member2@naver.com',0,sysdate,null,null,null,'더블하트 신 모유실감 PPSU 젖병','유미 PPSU 젖병',null,0,0,0,0);
insert into BOARD
values(13,'젖병소독기 추천 부탁드립니다.','첫번째 제품은 세일하고 있고 나머지 제품은 세일 안하고 있어요ㅜㅜ',3,'member3@naver.com',0,sysdate,null,null,null,'레이퀸 플러스 젖병소독기','NEW 유팡플러스 젖병소독기','유진메디케어 스펙트라 젖병 소독기',0,0,0,0);
insert into BOARD
values(14,'너무 고민됩니다 조카 줄 유모차 좀 골라주세요','찾아봣는데 이 두제품이 맘에 들어요!',3,'member4@naver.com',0,sysdate,null,null,null,'퀴니 무드3 디럭스 유모차','스토케 스쿠트 V2유모차',null,0,0,0,0);
insert into BOARD
values(15,'하이체어 골라주세요!','저는 첫번째인데 아내는 두번째래요 여러분이 골라주세요',3,'member5@naver.com',0,sysdate,null,null,null,'스토케 스텝스','스토케 클릭',null,0,0,0,0);

insert into BOARD
values(16,'최고의 믹서기는?','찾아보니까 이게 좋다는데 사용해 보신분 계신가요?',4,'member1@naver.com',0,sysdate,'mix1.PNG','mix2.PNG',null,'필립스진공 초고속 믹서기 HR3752/00','필립스 HR3752 진공믹서기',null,0,0,0,0);
insert into BOARD
values(17,'냄비 좀 골라주세요!','이번에 냄비 새로 살려고 합니다 추천 부탁드려요.',4,'member2@naver.com',0,sysdate,null,null,null,'엘리트 통3중 스텐냄비','내추럴 블랙우드','아인스 스텐냄비',0,0,0,0);
insert into BOARD
values(18,'에어프라이기 추천이요~','골라주세요~',4,'member3@naver.com',0,sysdate,null,null,null,'필립스 에어프라이어 hd9220/22','모즈스웨덴 에어프라이어 대용량 5.5l',null,0,0,0,0);
insert into BOARD
values(19,'인덕션 가성비 끝판왕은?','용도는 자취입니다.',4,'member4@naver.com',0,sysdate,null,null,null,'프리미엄 골드 인덕션 전기레인지','전기레인지 인덕션 프리스탠딩',null,0,0,0,0);
insert into BOARD
values(20,'자취용 에어프라이기?','이번에 자취를 시작하는데 에어프라이기 살 생각 입니다. 두제품 중 골라주세요',4,'member5@naver.com',0,sysdate,null,null,null,'보글리아3L 대용량 에어프라이기 에어프라이','버디쿡 에어프라이어',null,0,0,0,0);

insert into BOARD 
values(21,'방에 놓을 파티션 보고가세요~','선택장애가 와서 그러는데 좀 골라주세요 가격차이가 어마어마해요ㅜ',5,'member1@naver.com',0,sysdate,'pa1.PNG','pa2.PNG',null,'연철 빈티지 파티션','퍼즐 블라인드 파티션',null,0,0,0,0);
insert into BOARD
values(22,'침대옆 스텐드 이거 어때요?','차분한 느낌이 좋을거 같아요 둘중에 골라주세요!',5,'member2@naver.com',0,sysdate,'jang.PNG','jang2.PNG',null,'마켓비 VARDI 장스탠드','마켓비 VARDI 장스탠드',null,0,0,0,0);
insert into BOARD
values(23,'거실에 러그vs카페트','좋을까요?',5,'member3@naver.com',0,sysdate,'lat.PNG','pa.PNG',null,'내츄럴 라탄스타일 러그','바이빔/페르시안 투르크 카페트',null,0,0,0,0);
insert into BOARD
values(24,'거실에 벽시계 추천 부탁드려요!','어울릴까요?',5,'member4@naver.com',0,sysdate,'nw1.PNG','nw2.PNG',null,'북유럽풍 벽시계','북유럽 대형 몽틀 벽시계',null,0,0,0,0);
insert into BOARD
values(25,'방에 걸어둘 액자','어떤게 예쁠까요?',5,'member5@naver.com',0,sysdate,'jsp.PNG','nd.PNG',null,' 1984년 전시 포스터','에드 반 데르 엘스켄의 포스터',null,0,0,0,0);

insert into BOARD
values(26,'아이폰 기종 선택 좀 도와주세요ㅠ','성능의 아이폰11이냐 가성비의 se냐 골라주세요!제가 휴대폰 한번 사면 2년정도 쓰고요 게임은 안하고 사진은 많이 찍어요',6,'member1@naver.com',0,sysdate,'ip11m.PNG','se2.PNG',null,'아이폰11','아이폰se2',null,0,0,0,0);
insert into BOARD
values(27,'갤럭시 유저인데 아이폰se2 어떤가요?','갤럭시s20으로 갈지 가성비 좋은 se일까요? 아이폰 한번 가보고싶은데 괜찮을지...',6,'member2@naver.com',0,sysdate,'sg20.PNG','se1.PNG',null,'갤럭시20','아이폰se2',null,0,0,0,0);
insert into BOARD
values(28,'아이폰11 색 골라주세요ㅜ','지금 선택장애에 걸렷어요ㅜㅜ 흰색이냐 퍼플이냐 혹시나 퍼플 질릴까봐 걱정되네요.. 아 아니면 검은색은 어떤가',6,'member3@naver.com',0,sysdate,'ip11p.PNG','ip11w.PNG','ip11b.PNG','아이폰11퍼플','아이폰11화이트','아이폰11블랙',0,0,0,0);
insert into BOARD
values(29,'게임기 좀 골라주세요 스위치 vs 플스4','스위치는 여러명이랑 하면 재밌다는데 저는 혼자 즐기거든요 혼자 즐기기에는 좀 그럴까',6,'member4@naver.com',0,sysdate,'sw.PNG','ps4.PNG',null,'닌텐도스위치','소니 ps4',null,0,0,0,0);
insert into BOARD
values(30,'그램 인치 좀 추천해주세요','14인치 사면 좀 답답할까요?',6,'member5@naver.com',0,sysdate,'gr14.PNG','gr17.PNG',null,'그램 14인치','그램17인치',null,0,0,0,0);

insert into BOARD
values(31,'폼롤러 아무거나 사도 되나요?','지금 세개 정도 찾았는데 괜찮을까요?',7,'member1@naver.com',0,sysdate,'gre.PNG','bkp.PNG','eva.PNG','그리드 2.0 폼롤러','마이 블랙 하드 폼롤러','EVA 필라테스 폼롤러',0,0,0,0);
insert into BOARD
values(32,'요가링 추천해 주세요','어떤게 효과가 좋을까요? 두개 평이 좋던데',7,'member2@naver.com',0,sysdate,'ri.PNG','tra.PNG',null,'리타 필라테스링','TRATAC 요가링',null,0,0,0,0);
insert into BOARD
values(33,'헬린이 도와주세요! 단백질보충제 뭐가 좋을까요?','그냥 싼거 살까요? 아니면 브랜드? ',7,'member3@naver.com',0,sysdate,'dy.PNG','ac.PNG',null,'다이나믹 엑스 게이너','액티브기어 글루타민',null,0,0,0,0);
insert into BOARD
values(34,'런닝머신 뭐가 좋나요?','헬스장 갈시간이 없어서요ㅜ  혹시나 이제품들 말고 추천하시는거 있으면 댓글에 같이 달아주세요~ ',7,'member4@naver.com',0,sysdate,'xml.PNG','ew503.PNG',null,'XQIAO 접이식 런닝머신','워킹머신 EW-503',null,0,0,0,0);


