-- 코드를 입력하세요
# SELECT HOUR(DATETIME) AS HOUR, COUNT(*) AS COUNT FROM ANIMAL_OUTS
# GROUP BY HOUR(DATETIME)
# ORDER BY HOUR(DATETIME) ASC;
-- 0~23 시간대 생성
SELECT h.HOUR,
       COUNT(a.DATETIME) AS COUNT
FROM (
    SELECT 0 AS HOUR UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3
    UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7
    UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11
    UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15
    UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19
    UNION ALL SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23
) AS h
LEFT JOIN ANIMAL_OUTS a
  ON HOUR(a.DATETIME) = h.HOUR
GROUP BY h.HOUR
ORDER BY h.HOUR;
