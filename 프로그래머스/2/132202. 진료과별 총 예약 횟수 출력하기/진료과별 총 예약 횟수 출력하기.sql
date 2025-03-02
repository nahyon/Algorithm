-- 코드를 입력하세요
SELECT mcdp_cd as 진료과코드, count(*) as 5월예약건수
FROM appointment
where date_format(apnt_ymd, '%Y-%m') = '2022-05'
group by mcdp_cd
order by 5월예약건수 , 진료과코드;

# select * from appointment where date_format(apnt_ymd, '%Y-%m') = '2022-05' ;