-- 코드를 입력하세요
SELECT distinct(car.car_id)
FROM car_rental_company_car car join car_rental_company_rental_history history
on car.car_id = history.car_id
where car.car_type like '%세단%' and month(history.start_date) = 10
order by car.car_id desc;