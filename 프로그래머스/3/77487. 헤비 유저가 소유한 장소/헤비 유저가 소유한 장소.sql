-- 코드를 입력하세요
SELECT id, name, host_id
FROM places
where host_id in (select host_id from places group by host_id having count(*) >= 2)
order by id;

SELECT p.id, p.name, p.host_id
FROM places p
JOIN (
    SELECT host_id
    FROM places
    GROUP BY host_id
    HAVING COUNT(*) >= 2
) heavy_users ON p.host_id = heavy_users.host_id
ORDER BY p.id;

SELECT id, name, host_id
FROM places 
WHERE host_id IN (
    SELECT host_id
    FROM places
    GROUP BY host_id
    HAVING COUNT(*) >= 2
)
ORDER BY id;