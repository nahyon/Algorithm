-- 입양(outs) 간 기록은 있는데, 보호소에 들어온(ins) 기록 x
-- = join헀을 떄 outs에는 있지만, ins에는 없는 경우. -> outs 기준으로 Join하기
SELECT outs.animal_id, outs.name
FROM animal_ins ins RIGHT JOIN animal_outs outs
ON ins.animal_id = outs.animal_id
where ins.animal_id is null
ORDER BY outs.animal_id;