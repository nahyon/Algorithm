
# select * from ecoli_data a join ecoli_data b on a.parent_id = b.id where a.parent_id is not null;
-- (id, 내 타입, 부모id, 부모 타입)
-- (a.id, a.genotype, a.parent_id, b.genotype)

SELECT a.id, a.genotype, b.genotype as parent_genotype
from ecoli_data a 
join ecoli_data b 
on a.parent_id = b.id
where a.parent_id is not null
and a.genotype & b.genotype = b.genotype
order by a.id;

-- a.genotype & parent_genotype > 0 : 형질들 중 하나만 일치해도 true나옴.. 
