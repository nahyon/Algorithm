-- 코드를 입력하세요
# SELECT category, max_price, product_name
# FROM (SELECT *,
#     max(price) over (partition by category) as max_price from food_product) a
# WHERE category in ('과자', '국', '김치', '식용유')
# and max_price = price
# order by max_price desc;

-- with으로 빼기
WITH a as (
    SELECT * , 
    max(price) over (partition by category) as max_price FROM food_product
)
SELECT category, max_price, product_name
FROM a
WHERE category in ('과자', '국', '김치', '식용유')
and max_price = price
order by max_price desc;