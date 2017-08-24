drop sequence seq_emaillist;

create sequence seq_emaillist
start with 1
increment BY 1
MAXVALUE 9999999999;

--insert
insert into emaillist
values (seq_emaillist.nextval,'마','이콜', 'sosgur82@naver.com');
--list

select no, last_name, first_name, email from emaillist ORDER BY no desc;



commit;