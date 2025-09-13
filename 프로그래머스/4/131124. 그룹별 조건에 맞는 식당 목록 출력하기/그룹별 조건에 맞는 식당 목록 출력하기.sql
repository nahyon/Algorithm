WITH member_review_counts AS (
    SELECT member_id, COUNT(*) AS cnt
    FROM rest_review
    GROUP BY member_id
), -- -> member_id & 걔가 쓴 댓글 수 cnt 컬럼 
top_member AS (
    SELECT member_id
    FROM member_review_counts
    WHERE cnt = (SELECT MAX(cnt) FROM member_review_counts)
) -- -> cnt에서 젤 MAX인 애의 member_id 가져옴
-- -----------
SELECT mp.member_name, rr.review_text, DATE_FORMAT(rr.review_date, '%Y-%m-%d') AS review_date
FROM rest_review rr
JOIN member_profile mp ON rr.member_id = mp.member_id
JOIN top_member tm ON rr.member_id = tm.member_id -- tm에는 댓글 개수 최대인 애의 member_id만 있음
ORDER BY rr.review_date, rr.review_text;