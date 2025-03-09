-- 코드를 입력하세요
# SELECT food_type, rest_id, rest_name, favorites
# FROM rest_info
# group by food_type
# having favorites = max(favorites)
# order by food_type desc;

-- food_type 한 그룹 내에서 최대의 favorites를 가지는 rest_id를 찾아야함

SELECT food_type, rest_id, rest_name, favorites
FROM rest_info
where (food_type, favorites) in (select food_type, max(favorites) from rest_info group by food_type)
order by food_type desc;
