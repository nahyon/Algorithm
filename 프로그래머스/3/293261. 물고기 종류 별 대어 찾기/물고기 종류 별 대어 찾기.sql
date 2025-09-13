select a.id, b.fish_name, a.length
from fish_info a join fish_name_info b
on a.fish_type = b.fish_type
where (a.fish_type, a.length) in (select fish_type, max(length) from fish_info group by fish_type)


# WITH fish_with_max AS (
#     SELECT id, fish_type, length,
#            MAX(length) OVER (PARTITION BY fish_type) as max_length
#     FROM fish_info
# )
# SELECT id, fish_type, length, max_length
# FROM fish_with_max
# WHERE length = max_length;

-- 코드를 작성해주세요
# select a.id as id, b.fish_name, a.length
# from fish_info a join fish_name_info b on a.fish_type = b.fish_type
# where (a.fish_type, a.length) in (select fish_type, max(length) from fish_info group by fish_type)
# order by a.id;

# SELECT *
# , MAX(LENGTH) OVER (PARTITION BY FISH_TYPE) AS MAX_LENGTH
# FROM FISH_INFO;

# select a.id as id, b.fish_name, a.length
# from (SELECT *, MAX(LENGTH) OVER (PARTITION BY FISH_TYPE) AS MAX_LENGTH
#     FROM FISH_INFO ) a 
# left join fish_name_info b 
# on a.fish_type = b.fish_type 
# where a.length = a.max_length
# order by a.id;