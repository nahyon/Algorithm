-- 코드를 입력하세요
SELECT year(b.sales_date) as year,  month(b.sales_date) as month, 
a.gender, count(distinct(a.user_id)) as users
FROM user_info a
# FROM (select * from user_info where gender is not null) a
join online_sale b
on a.user_id = b.user_id
where a.gender is not null
group by year(b.sales_date), month(b.sales_date), a.gender
order by year(b.sales_date), month(b.sales_date), a.gender;