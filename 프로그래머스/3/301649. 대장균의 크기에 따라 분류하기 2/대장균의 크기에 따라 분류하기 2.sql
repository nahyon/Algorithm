-- 코드를 작성해주세요
SELECT id, 
    CASE 
        when percent <= 0.25 then 'CRITICAL'
        when percent <= 0.5 then 'HIGH'
        when percent <= 0.75 then 'MEDIUM'
        else 'LOW'
    END as COLONY_NAME
from (
    select id, 
        percent_rank() over (order by size_of_colony desc) as percent
    from ecoli_data) a
order by id;
