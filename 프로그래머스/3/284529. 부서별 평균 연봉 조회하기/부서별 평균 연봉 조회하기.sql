-- 코드를 작성해주세요
# select d.dept_id, d.dept_name_en, round(avg(e.sal)) as avg_sal
# FROM hr_department d JOIN hr_employees e
# join d.dept_id = e.dept_id
# group by d.dept_id -- 부서별 
# order by avg_sal desc;

select d.dept_id, d.dept_name_en, e.avg_sal
FROM hr_department d 
JOIN (select  dept_id, round(avg(sal)) as avg_sal
        from hr_employees
        group by dept_id) e
on d.dept_id = e.dept_id
order by e.avg_sal desc;

-- 부서별 평균 봉급
# select  dept_id, avg(sal)
# from hr_employees
# group by dept_id;