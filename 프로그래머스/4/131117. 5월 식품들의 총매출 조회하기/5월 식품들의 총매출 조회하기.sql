-- 코드를 입력하세요
SELECT p.product_id as product_id, p.product_name as product_name, 
    p.price * o.amount as total_sales
FROM (SELECT product_id, sum(amount) as amount from food_order where produce_date like "2022-05%"
     group by product_id) o
JOIN food_product p ON o.product_id = p.product_id
order by total_sales desc, product_id;