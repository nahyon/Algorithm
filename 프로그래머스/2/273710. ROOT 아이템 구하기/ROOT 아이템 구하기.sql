-- 코드를 작성해주세요
SELECT info.item_id, info.item_name
FROM ITEM_INFO info JOIN ITEM_TREE tree
on info.item_id = tree.item_id
where tree.parent_item_id is null
order by info.item_id;