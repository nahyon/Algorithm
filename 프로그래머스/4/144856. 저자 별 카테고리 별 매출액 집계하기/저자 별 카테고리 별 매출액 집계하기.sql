-- 코드를 입력하세요
SELECT book.author_id, author.author_name, book.category, sum(sales.sales*book.price) as total_sales
FROM book book join author author on book.author_id = author.author_id 
join book_sales sales on book.book_id = sales.book_id 
where sales.SALES_DATE like "2022-01%"
group by book.author_id, book.category
order by book.author_id, book.category desc;