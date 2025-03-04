-- 코드를 입력하세요
SELECT car_id, ROUND(AVG(DATEDIFF(end_date, start_date)+1), 1) as AVERAGE_DURATION
FROM car_rental_company_rental_history
# WHERE ROUND(AVG(DATEDIFF(end_date, start_date)), 1) >= 7
GROUP BY car_id
HAVING ROUND(AVG(DATEDIFF(end_date, start_date)+1), 1) >= 7
ORDER BY AVERAGE_DURATION desc, car_id desc;