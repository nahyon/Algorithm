-- 코드를 입력하세요
# SELECT distinct(user_id, product_id)
# from online_sale table1 
# JOIN (SELECT)
# ON table1.user_id = table2.user_id
# on table1.product_id = table2.product_id
# where distinct(user_id, product_id) IN (SELECT distinct(user_id, product_id) from online_sale)
# order by user_id asc, product_id desc;

SELECT user_id, product_id from online_sale
group by user_id, product_id
having count(*) > 1
order by user_id asc, product_id desc;
# select product_id, sales_date from online_sale where user_id = ?;