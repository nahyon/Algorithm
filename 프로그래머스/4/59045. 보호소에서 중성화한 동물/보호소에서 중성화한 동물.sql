-- 코드를 입력하세요
SELECT ins.animal_id as animal_id, ins.animal_type as animal_type, ins.name as name
FROM animal_ins ins join animal_outs outs
on ins.animal_id = outs.animal_id
where ins.sex_upon_intake like '%Intact%' 
and outs.sex_upon_outcome not like '%Intact%'
order by ins.animal_id;

# Intact : 중성화안됨 / Spayed, Neutered : 중성화됨