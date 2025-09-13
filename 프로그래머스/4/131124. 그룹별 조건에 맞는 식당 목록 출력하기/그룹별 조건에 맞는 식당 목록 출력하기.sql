# WITH review_counts AS (
#     SELECT member_id, COUNT(*) AS review_cnt,
#            RANK() OVER (ORDER BY COUNT(*) DESC) AS rnk
#     FROM rest_review
#     GROUP BY member_id
# )
# SELECT mp.member_name, rr.review_text, DATE_FORMAT(rr.review_date, "%Y-%m-%d") AS review_date
# FROM rest_review rr
# JOIN review_counts rc ON rr.member_id = rc.member_id
# JOIN member_profile mp ON rr.member_id = mp.member_id
# WHERE rc.rnk = 1
# ORDER BY rr.review_date, rr.review_text;

-- 필요한 것 : member_id 별 리뷰개수 (count(*))해서 가장 최대 개수인 '애'의 member_id
select a.member_name, b.review_text, b.review_date 
from member_profile a join rest_review b on a.member_id = b.member_id
where (b.member_id) = (select member_id from rest_review group by member_id order by count(*) desc limit 1)
order by b.review_date, b.review_text;


select member_id, count(*) from rest_review group by member_id order by count(*) desc; -- count(*)이 최대인 값이 3명 같음
-- having max(count(*)) 쓰고싶다............
# select *, count(*) over (partition by member_id) as cnt from rest_review order by cnt desc;

select a.member_name, b.review_text, b.review_date 
from member_profile a join rest_review b on a.member_id = b.member_id
where (b.member_id) = (select member_id from rest_review group by member_id order by count(*) desc limit 1)
order by b.review_date, b.review_text;

SELECT member.member_name, review.review_text, date_format(review.review_date, "%Y-%m-%d") as review_date
FROM member_profile member join rest_review review on member.member_id = review.member_id
WHERE member.member_id = (select member_id
        from rest_review 
        group by member_id
        order by count(*) desc limit 1 ) -- 가장 최대인 사람 1명만
order by review_date, review_text;

# WITH review_max as (
#     SELECT * , count(*) over (partition by member_id) -- member_id별 리뷰 개수(=count(*))
#     FROM rest_review
# )
# SELECT a.member_name, b.review_text, b.review_date
# FROM member_profile a JOIN review_max b ON a.member_id = b.member_id

