-- 코드를 작성해주세요
select g.score as score, e.emp_no, e.emp_name, e.position, e.email
from hr_employees e 
join (select sum(score) as score, emp_no from hr_grade group by emp_no order by score desc limit 1) g 
on e.emp_no = g.emp_no;



# select * from hr_grade order by score desc limit 1; -- (emp_no, year, half_year, score)
# select sum(score) as score, emp_no from hr_grade group by emp_no order by score desc limit 1;