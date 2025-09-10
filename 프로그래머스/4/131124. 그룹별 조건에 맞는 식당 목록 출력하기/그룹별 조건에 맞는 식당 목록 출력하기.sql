WITH review_counts AS (
    SELECT member_id, COUNT(*) AS review_cnt,
           RANK() OVER (ORDER BY COUNT(*) DESC) AS rnk
    FROM rest_review
    GROUP BY member_id
)
SELECT mp.member_name, rr.review_text, DATE_FORMAT(rr.review_date, "%Y-%m-%d") AS review_date
FROM rest_review rr
JOIN review_counts rc ON rr.member_id = rc.member_id
JOIN member_profile mp ON rr.member_id = mp.member_id
WHERE rc.rnk = 1
ORDER BY rr.review_date, rr.review_text;
