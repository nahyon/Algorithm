# ITEM_TREE 테이블
-- (item_id (로 업데이트됨) <- parent_item_id (부모) ) item_id의 parent가 parent_item_id
-- parent_item_id가 null 이면 root 아이템

# 더 이상 업그레이드 불가 = 해당 item_id가 parent_item_id컬럼에 있지않다.
# = (t1.item_id = t2.parent_item_id) 로 t1기준 LEFT JOIN했을 때, t2.item_id가 Null 인 t1 테이블 애들
# (즉, left join했을 때 t2테이블에 데이터가 없는 애들을 의미함)
# SELECT t1.item_id, t1.parent_item_id, t2.item_id, t2.parent_item_id
# FROM item_tree t1 LEFT JOIN item_tree t2
# ON t1.item_id = t2.parent_item_id
# WHERE t2.item_id is null; -- LEFT JOIN 시 t2 테이블에 데이터가 없는 애들

#########################정답 
SELECT info.item_id, item_name, rarity
FROM (SELECT t1.item_id
    FROM item_tree t1 LEFT JOIN item_tree t2
    ON t1.item_id = t2.parent_item_id
    WHERE t2.item_id is null) a
JOIN item_info info
ON a.item_id = info.item_id
order by item_id desc;