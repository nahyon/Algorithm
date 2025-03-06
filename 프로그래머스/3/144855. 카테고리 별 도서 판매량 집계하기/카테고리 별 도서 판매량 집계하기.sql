-- 코드를 입력하세요
SELECT category , sum(s.sales) as total_sales
from book b join book_sales s
on b.book_id = s.book_id
where date_format(s.sales_date, "%Y-%m") = '2022-01'
group by category
order by category;