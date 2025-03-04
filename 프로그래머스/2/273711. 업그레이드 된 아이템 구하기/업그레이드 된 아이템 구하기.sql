-- 코드를 작성해주세요
select info.item_id, info.item_name, info.rarity
FROM item_info info 
JOIN (select b.item_id from item_info a join item_tree b on a.item_id = b.parent_item_id 
    where a.rarity = 'RARE') child
ON info.item_id = child.item_id
ORDER BY info.item_id desc;

