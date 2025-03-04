-- 코드를 입력하세요
SELECT FLOOR(price/10000)*10000 AS price_group, count(*) AS products
from product 
group by FLOOR(price/10000)
order by price_group;

# select price, FLOOR(price/10000)*10000
# from product order by price;