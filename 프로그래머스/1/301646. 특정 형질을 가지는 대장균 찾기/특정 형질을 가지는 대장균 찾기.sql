-- 코드를 작성해주세요
select count(*) as COUNT
from ecoli_data
where ((genotype & (1<<2)) > 0 OR (genotype & (1<<0)) > 0) and (genotype & (1<<1)) = 0;