# -- 코드를 입력하세요
# SELECT year(b.sales_date) as year,  month(b.sales_date) as month, 
# a.gender, count(distinct(a.user_id)) as users
# FROM user_info a
# # FROM (select * from user_info where gender is not null) a
# join online_sale b
# on a.user_id = b.user_id
# where a.gender is not null
# group by year(b.sales_date), month(b.sales_date), a.gender
# order by year(b.sales_date), month(b.sales_date), a.gender;

select year(sale.sales_date) as YEAR, month(sale.sales_date) as MONTH, info.gender as GENDER, count(distinct info.user_id) as users
from user_info info join online_sale sale on sale.user_id = info.user_id
where info.gender is not null
group by year(sale.sales_date) , month(sale.sales_date), info.gender
order by year(sale.sales_date) , month(sale.sales_date), info.gender;