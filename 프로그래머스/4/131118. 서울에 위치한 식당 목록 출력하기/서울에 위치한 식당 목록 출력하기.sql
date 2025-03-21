-- 코드를 입력하세요
SELECT a.rest_id, a.rest_name, a.food_type, a.favorites, a.address, round(b.avg_score, 2) as SCORE -- 리뷰평균평점
from rest_info a
join (select rest_id, avg(review_score) as avg_score from rest_review group by rest_id) b
on a.rest_id = b.rest_id
where address like "서울%"
order by round(b.avg_score, 2) desc, a.favorites desc;