-- 코드를 입력하세요
SELECT a.apnt_no, a.pt_name, a.pt_no, a.mcdp_cd, d.dr_name, a.apnt_ymd
FROM doctor d join
(SELECT a.apnt_no, a.pt_no, a.APNT_YMD, p.pt_name, a.mcdp_cd , a.mddr_id
 from appointment a
 join patient p on a.pt_no = p.pt_no where a.mcdp_cd = 'CS' and a.apnt_cncl_yn = 'N' 
 # and date_format(a.APNT_YMD, "%Y-%m-%d") <= "2022-04-13") a
 and a.apnt_ymd like "2022-04-13%") a
on d.dr_id = a.mddr_id
order by a.apnt_ymd;