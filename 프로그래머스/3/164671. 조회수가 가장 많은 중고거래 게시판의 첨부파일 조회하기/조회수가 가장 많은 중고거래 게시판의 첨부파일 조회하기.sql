-- 코드를 입력하세요
SELECT concat("/home/grep/src/", board.board_id, "/", file.file_id, file.file_name, file.file_ext) as FILE_PATH
FROM used_goods_board board join used_goods_file file
on board.board_id = file.board_id
where board.views = (select max(views) from used_goods_board)
order by file.file_id desc;