-- 코드를 입력하세요
SELECT ins.animal_id, ins.name -- , outs.datetime, ins.datetime, datediff(outs.datetime, ins.datetime)
FROM animal_ins ins JOIN animal_outs outs
on ins.animal_id = outs.animal_id
order by datediff(outs.datetime, ins.datetime) desc limit 2;
