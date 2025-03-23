-- 코드를 입력하세요
SELECT first_half.flavor
FROM (select flavor, sum(total_order) as total_order from first_half group by flavor) first_half
JOIN (select flavor, sum(total_order) as total_order from july group by flavor) july
ON first_half.flavor = july.flavor
order by first_half.total_order + july.total_order desc limit 3;