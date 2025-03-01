-- 코드를 입력하세요
SELECT f.flavor
from first_half f join icecream_info i on f.flavor = i.flavor
where ingredient_type = 'fruit_based' and total_order > 3000
order by total_order desc;