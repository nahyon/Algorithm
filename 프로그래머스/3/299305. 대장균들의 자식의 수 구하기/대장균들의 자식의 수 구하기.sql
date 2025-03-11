-- 코드를 작성해주세요
select id, ifnull(child_count, 0) as child_count
from ecoli_data e1
LEFT join (select parent_id, count(*) as child_count 
    from ecoli_data 
    where parent_id is not null
    group by parent_id) e2
on e1.id = e2.parent_id -- e1기준으로 id가 다 나올 수 있도록 LEFT JOIN
order by id;




-- (id, parent_id) 현재 아이디, 얘 부모 아이디
# select parent_id, count(*) as child_count from ecoli_data group by parent_id; -- parent가 아닌 id들은 0이 나올 수 있도록, 그리고 parent_id가 Null인건 나오지않도록
#
# select parent_id, count(*) as child_count 
# from ecoli_data 
# where parent_id is not null
# group by parent_id;