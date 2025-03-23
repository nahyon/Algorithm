-- 코드를 작성해주세요

# select emp_no, year, avg(score)
# from hr_grade
# group by emp_no, year;

select emp.emp_no, emp.emp_name, 
    case 
        when grade.score >= 96 then 'S'
        when grade.score >= 90 then 'A'
        when grade.score >= 80 then 'B'
        else 'C'
    end as grade,
        case 
        when grade.score >= 96 then emp.sal * 0.2
        when grade.score >= 90 then emp.sal * 0.15
        when grade.score >= 80 then emp.sal * 0.1
        else 0
    end as bonus
from hr_employees emp
join (select emp_no, year, avg(score) as score
    from hr_grade
    group by emp_no, year) grade
on emp.emp_no = grade.emp_no
order by emp.emp_no;