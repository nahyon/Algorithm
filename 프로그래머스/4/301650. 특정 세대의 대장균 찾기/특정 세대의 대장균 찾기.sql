-- 코드를 작성해주세요
-- 1세대
# SELECT ID FROM ECOLI_DATA WHERE PARENT_ID IS NULL;
-- 2세대
# SELECT ID FROM ECOLI_DATA WHERE PARENT_ID IN (SELECT ID FROM ECOLI_DATA WHERE PARENT_ID IS NULL);
-- 
SELECT ID FROM ECOLI_DATA 
WHERE PARENT_ID IN (SELECT ID FROM ECOLI_DATA 
                    WHERE PARENT_ID IN (SELECT ID 
                                        FROM ECOLI_DATA WHERE PARENT_ID IS NULL))
ORDER BY ID;
