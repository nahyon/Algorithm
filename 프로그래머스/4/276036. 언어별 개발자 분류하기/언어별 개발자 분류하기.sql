# SELECT
#     MAX(CASE WHEN name = 'Python' THEN code END) AS python_code,
#     MAX(CASE WHEN name = 'C#' THEN code END) AS csharp_code,
#     (SELECT SUM(code) FROM skillcodes WHERE category = 'Front End') AS fe_mask
# FROM skillcodes

WITH skill_mask as (
    SELECT
        MAX(CASE WHEN name = 'Python' THEN code END) AS python_code,
        MAX(CASE WHEN name = 'C#' THEN code END) AS csharp_code,
        (SELECT SUM(code) FROM skillcodes WHERE category = 'Front End') AS fe_mask
    FROM skillcodes
), grade_table as (
    SELECT 
        CASE 
            WHEN d.SKILL_CODE & s.fe_mask > 0 AND d.SKILL_CODE & s.python_code > 0 THEN 'A'
            WHEN d.SKILL_CODE & s.csharp_code > 0 THEN 'B'
            WHEN d.SKILL_CODE & s.fe_mask > 0 THEN 'C'
            ELSE NULL -- 나머지 (나중에 빼야함)
        END AS grade, d.id, d.email
    FROM developers d JOIN skill_mask s 
)
SELECT * FROM grade_table
WHERE grade is not null
ORDER BY grade, id;