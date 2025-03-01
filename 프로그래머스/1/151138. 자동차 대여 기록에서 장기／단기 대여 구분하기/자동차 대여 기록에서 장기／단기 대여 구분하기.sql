-- 코드를 입력하세요
SELECT history_id, car_id, 
date_format(start_date, '%Y-%m-%d') as start_date,
date_format(end_date, '%Y-%m-%d') as end_date, 
CASE 
    WHEN DATEDIFF(end_date, start_date) +1 >= 30 THEN '장기 대여' 
    ELSE '단기 대여' 
END as RENT_TYPE
from car_rental_company_rental_history
where date_format(start_date, '%Y-%m') = '2022-09'
order by history_id desc;