-- 코드를 작성해주세요
SELECT count(*) as FISH_COUNT, max(length) as MAX_LENGTH, FISH_TYPE
FROM (select id, fish_type, ifnull(length,10) as length from fish_info) a
group by fish_type
having avg(length) >= 33
order by fish_type asc;