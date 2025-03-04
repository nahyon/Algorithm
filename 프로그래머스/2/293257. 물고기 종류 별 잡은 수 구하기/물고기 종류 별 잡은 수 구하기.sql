-- 코드를 작성해주세요
SELECT count(*) as FISH_COUNT, name.fish_name as FISH_NAME
from fish_info fish join fish_name_info name
on fish.fish_type = name.fish_type
group by name.fish_name
order by FISH_COUNT desc;