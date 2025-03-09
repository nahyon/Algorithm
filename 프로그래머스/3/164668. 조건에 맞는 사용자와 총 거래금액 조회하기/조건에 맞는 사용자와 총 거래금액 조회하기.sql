-- 코드를 입력하세요
SELECT  users.user_id, users.nickname, sum(board.price) as total_sales
FROM used_goods_user users join used_goods_board board
on users.user_id = board.writer_id
where board.status = 'DONE'
-- group by board.status
group by users.user_id
having sum(board.price) >= 700000
order by total_sales;
