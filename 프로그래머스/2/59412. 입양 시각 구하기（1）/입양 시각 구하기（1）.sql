-- 코드를 입력하세요
SELECT date_format(datetime, '%H') as HOUR, count(*) as COUNT
from animal_outs
WHERE date_format(datetime, '%H') >= 9 and date_format(datetime, '%H') < 20
group by HOUR
order by hour;