-- 코드를 작성해주세요
select year(differentiation_date) as year,
        (max(size_of_colony) over (partition by year(differentiation_date)) - size_of_colony) as year_dev,
        id
from ecoli_data
order by year, year_dev;

-- 분화된 연도별 가장 큰 대장균 크기
# select size_of_colony from ecoli_data
# where size_of_colony = (select max(size_of_colony) from ecoli_data);
-- order by size_of_colony desc limit 1;