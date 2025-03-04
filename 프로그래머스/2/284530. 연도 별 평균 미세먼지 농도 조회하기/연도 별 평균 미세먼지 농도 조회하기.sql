-- 코드를 작성해주세요
SELECT year(YM) as YEAR
, round(sum(PM_VAL1)/count(*), 2) as PM10
, round(sum(PM_VAL2)/count(*), 2) as `PM2.5`
# , round(avg(PM_VAL1), 2) as PM10
# , round(avg(PM_VAL2), 2) as `PM2.5`
FROM air_pollution
WHERE location2 = '수원'
GROUP BY year(YM)
order by year(YM);