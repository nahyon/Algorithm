-- 코드를 입력하세요
SELECT MONTH(start_date) as month , history.car_id, COUNT(history.car_id) as RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY history
JOIN (SELECT car_id FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE start_date between '2022-08-01' and '2022-10-31'
        GROUP BY car_id
        having COUNT(*) >= 5) cars -- 기간 중 '총' 대여횟수가 5회이상인 자동차들
ON history.car_id = cars.car_id
WHERE start_date between '2022-08-01' and '2022-10-31' -- 여기서도 적어줘야함 잊지말기
GROUP BY history.car_id, MONTH(start_date)
HAVING COUNT(history.car_id) != 0 -- 월별로 묶었을 때 0인 애들 제외
ORDER BY MONTH(start_date), history.car_id desc;

-- 기간 중 총 대여 횟수가 5회이상인 애들 && "특정 월"의 총 대여횟수 0은 제외
# SELECT car_id FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
# WHERE date_format(start_date, "%Y-%m-%d") between '2022-08-01' and '2022-10-31'
# GROUP BY car_id
# having COUNT(car_id) >= 5;