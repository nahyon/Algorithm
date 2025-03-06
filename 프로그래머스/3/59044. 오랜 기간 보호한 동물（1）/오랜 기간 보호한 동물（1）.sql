-- 코드를 입력하세요
SELECT ins.name, ins.datetime
FROM animal_ins ins LEFT JOIN animal_outs outs
ON ins.animal_id = outs.animal_id
WHERE outs.animal_id is null -- 입양 못감 
ORDER BY ins.datetime asc LIMIT 3;


-- LEFT JOIN : right에서 Null 발생 (left기준 조인)
# SELECT ins.animal_id as ins , outs.animal_id as outs
# FROM animal_ins ins LEFT JOIN animal_outs outs
# ON ins.animal_id = outs.animal_id