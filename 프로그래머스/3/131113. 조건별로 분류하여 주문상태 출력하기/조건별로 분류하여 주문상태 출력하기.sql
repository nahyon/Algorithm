-- 코드를 입력하세요
SELECT order_id, product_id, date_format(out_date, "%Y-%m-%d") as out_date,
    IF (out_date is null , "출고미정", IF((DATEDIFF('2022-05-01',out_date)>=0), "출고완료", "출고대기")) as 출고여부
    # CASE
    #     WHEN out_date is null then `출고미정`
    #     ELSE
    #         WHEN (DATEDIFF('2022-05-01',out_date)>=0) then `출고완료`
    #         ELSE `출고대기`
    # END as `출고여부`
FROM food_order
ORDER BY order_id;

# IF(datediff("2022-05-01"-out_date)>=0, '출고완료')
# select DATEDIFF('2022-05-01',out_date) from food_order;