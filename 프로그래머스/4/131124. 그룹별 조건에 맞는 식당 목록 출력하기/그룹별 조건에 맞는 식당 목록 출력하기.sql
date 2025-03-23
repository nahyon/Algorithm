-- 코드를 입력하세요
SELECT member.member_name, review.review_text, date_format(review.review_date, "%Y-%m-%d") as review_date
FROM member_profile member join rest_review review on member.member_id = review.member_id
WHERE member.member_id = (select member_id
        from rest_review 
        group by member_id
        order by count(*) desc limit 1 ) -- 가장 최대인 사람 1명만
order by review_date, review_text;

