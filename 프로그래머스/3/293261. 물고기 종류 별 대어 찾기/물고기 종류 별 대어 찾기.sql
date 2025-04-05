-- 코드를 작성해주세요
# select a.id as id, b.fish_name, a.length
# from fish_info a join fish_name_info b on a.fish_type = b.fish_type
# where (a.fish_type, a.length) in (select fish_type, max(length) from fish_info group by fish_type)
# order by a.id;

# SELECT *
# , MAX(LENGTH) OVER (PARTITION BY FISH_TYPE) AS MAX_LENGTH
# FROM FISH_INFO;

select a.id as id, b.fish_name, a.length
from (SELECT *, MAX(LENGTH) OVER (PARTITION BY FISH_TYPE) AS MAX_LENGTH
    FROM FISH_INFO ) a 
left join fish_name_info b 
on a.fish_type = b.fish_type 
where a.length = a.max_length
order by a.id;