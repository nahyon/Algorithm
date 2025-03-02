-- 코드를 입력하세요
SELECT animal_id, name
from animal_ins
where name LIKE "%EL%" 
and animal_type = "dog"
order by name;