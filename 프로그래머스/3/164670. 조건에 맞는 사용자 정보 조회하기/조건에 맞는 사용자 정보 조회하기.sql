-- 코드를 입력하세요
SELECT user_id, nickname, concat(city,' ', street_address1, ' ', ifnull(street_address2, '')) as 전체주소,
    concat(substr(tlno, 1, 3), '-', substr(tlno, 4, 4), '-', substr(tlno, 8, 4)) as 전화번호
FROM used_goods_user
where user_id in (select writer_id from USED_GOODS_BOARD group by writer_id having count(*)>=3)
ORDER BY user_id desc;

# select writer_id from USED_GOODS_BOARD group by writer_id having count(*)>=3;