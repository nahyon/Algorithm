-- 코드를 입력하세요
SELECT ins.animal_id, ins.name  -- , ins.datetime as 보호시작일, outs.datetime as 입양일, timediff(ins.datetime, outs.datetime)
from animal_ins ins join animal_outs outs
on ins.animal_id = outs.animal_id
where ins.datetime > outs.datetime
order by ins.datetime;