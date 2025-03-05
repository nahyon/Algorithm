-- 코드를 작성해주세요
select concat(quarter(DIFFERENTIATION_DATE), 'Q') as QUARTER, count(*) as ECOLI_COUNT
from ecoli_data
group by quarter
order by quarter ;