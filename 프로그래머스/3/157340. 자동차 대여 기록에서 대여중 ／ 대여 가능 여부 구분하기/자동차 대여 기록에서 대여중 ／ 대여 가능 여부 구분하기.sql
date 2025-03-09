-- 코드를 입력하세요
# SELECT car_id, if(datediff(start_date, '2022-10-16')>=0 and datediff('2022-10-16', end_date)>=0, '대여중', '대여가능') as availability, history_id, start_date, end_date
# FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
# ORDER BY car_id desc;

-- 해당 날짜에 car_id가 대여중인 레코드가 있다면 "대여중" 으로 표시, 아니면 "대여가능"
# SELECT distinct(car_id), if(timestampdiff(day, start_date, '2022-10-16')>=0 and timestampdiff(day, '2022-10-16', end_date)>=0, '대여중', '대여가능') as availability, history_id, start_date, end_date
# FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
# WHERE 
# ORDER BY car_id desc;

SELECT car_id, 
       CASE 
           WHEN MAX(CASE WHEN '2022-10-16' BETWEEN start_date AND end_date THEN 1 ELSE 0 END) = 1
           THEN '대여중' 
           ELSE '대여 가능' 
       END AS availability
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY car_id
ORDER BY car_id DESC;

-- 이 기간 중 대여중인 car
# select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY where '2022-10-16' BETWEEN start_date AND end_date;
