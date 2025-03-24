# -- CAR_RENTAL_COMPANY_RENTAL_HISTORY
# -- 2022년 11월 1일 부터 2022년 11월 30일까지 기간 중 대여가능한 car_id
# select car_id
# from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
# where end_date < '2022-11-01' or start_date > '2022-11-30'; 
# -- 세단, suv인 car_id, car_type, daily_fee
# select car_id, daily_fee from CAR_RENTAL_COMPANY_CAR where car_type in ('세단', 'SUV');

with dc_plan as (
    select car_type, discount_rate
    from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    where car_type in ('세단', 'SUV') and duration_type = '30일 이상'
)
select car_id, c.car_type, round(c.daily_fee * 30 * (1 - d.discount_rate / 100)) as fee
from CAR_RENTAL_COMPANY_CAR C JOIN DC_PLAN D
on c.car_type = d.car_type
where car_id not in (select car_id -- 기간 중 대여중인 차
                  from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
                 WHERE END_DATE >= '2022-11-01' AND START_DATE <= '2022-11-30')
                  # where start_date between '2022-11-01' and '2022-11-30'
                  #   or end_date between '2022-11-01' and '2022-11-30')
# and c.car_type in ('세단', 'SUV') -- 어짜피 dc_plan과 join 되면 세단,suv만 나옴
and c.daily_fee * 30 * (1 - d.discount_rate / 100) between 500000 and 2000000
order by fee desc, c.car_type, car_id;