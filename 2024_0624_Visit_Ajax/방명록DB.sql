/*
--방명록 DB

-- 일련번호 관리하는 객체(시퀀스)
create sequence seq_visit_idx 

-- 방명록 테이블 생성
create table visit
(	
	idx		int,
	name	varchar2(100)	not null,
	content	varchar2(2000)	not null, 
	pwd		varchar2(100)	not null,
	ip		varchar2(100)	not null,
	regdate	date
)	

-- 기본키		
alter table visit 
	add constraint pk_visit_idx	primary key(idx);
	
-- sample data
insert into visit values(seq_visit_idx.nextVal,
						 '일길동',
						 '내가 1등이다',
						 '1234',
						 '192.168.219.170',
						 sysdate
  						);	
insert into visit values(seq_visit_idx.nextVal,
						 '이길동',
						 '아쉽네 내가 1등할 수 있었는데...',
						 '1234',
						 '192.168.219.54',
						 sysdate
  						);
 -- JDBC용 insert문 							
insert into visit values(seq_visit_idx.nextVal,?,?,?,?,sysdate);	

-- view로 생성

create or replace view visit_view
as        
	select
		rownum as no, v.*
	from	  						 																		
		(select * from visit order by idx desc) v	
	
select * from visit_view where idx = 5

-- 수정
update visit set name='일길동',
				 content='내가 1등이요',
				 pwd='1234',
				 ip='192.168.219.170',
				 regdate=sysdate
where idx=1	

delete from visit where idx= 80	
select nvl(count(*)) from visit																		

*/