-- 코드를 입력하세요
SELECT ROUND(AVG(daily_fee), 0) as average_fee 
FROM car_rental_company_car
where car_type = 'SUV';