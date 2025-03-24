-- 코드를 작성해주세요
-- DEVELOPERS.SKILL_CODE (INTEGER) => SKILLCODES.CODE (2의제곱수 형태. bit로 구분 가능)
-- skillcodes테이블의 CODE들을 OR연산(합)했을 때 SKILL_CODE나옴 
-- (developers의 skill_code컬럼) & 연산 (skillcodes.code)해서 >0 인 경우 그 skillcodes.code스킬 가지고있다고 판단

# select code from skillcodes where category = 'Front End' ;

# select id, email, first_name, last_name
select d.id, d.email, d.first_name, d.last_name
from developers d join (select code from skillcodes where category = 'Front End' ) c
where d.SKILL_CODE & c.code > 0 -- FE스킬 여러개 가지고있으면 한 사람도 여러번 나올 수 있기 때문에 distinct써야함
group by d.id, d.email, d.first_name, d.last_name
order by id;