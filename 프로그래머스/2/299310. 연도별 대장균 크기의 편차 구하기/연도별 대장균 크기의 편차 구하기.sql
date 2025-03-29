-- 코드를 작성해주세요
# select year(differentiation_date) as year,
#         (max(size_of_colony) over (partition by year(differentiation_date)) - size_of_colony) as year_dev,
#         id
# from ecoli_data
# order by year, year_dev;


SELECT YEAR(DIFFERENTIATION_DATE) AS YEAR, 
MAX(SIZE_OF_COLONY) OVER (PARTITION BY YEAR(DIFFERENTIATION_DATE)) - SIZE_OF_COLONY AS YEAR_DEV,
ID
FROM ECOLI_DATA
ORDER BY YEAR, YEAR_DEV;
