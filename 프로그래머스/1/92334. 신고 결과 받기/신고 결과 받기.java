import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        HashMap<String, HashSet<String>> attacked = new HashMap<>(); // 나를 신고한애들 리스트
        HashMap<String, HashSet<String>> attackList = new HashMap<>(); // 내가 신고한애들 리스트
        
        
        // hashmap 초기화
        for (int i = 0 ; i  < id_list.length ; i++ ) {
        	attacked.put(id_list[i], new HashSet<>());
        	attackList.put(id_list[i], new HashSet<>());
        }
        
        
        // 입력받기
        for (int i = 0 ; i  < report.length ; i++ ) {
            String[] nameArray = report[i].split(" ");
            for(int j=0;j < nameArray.length ; j++) {
            	// [0] : 신고자
            	// [1] : 신고당한 애
            	attacked.get(nameArray[1]).add(nameArray[0]);
            	attackList.get(nameArray[0]).add(nameArray[1]); 
            }
        }

        // 답
        int[] answer = new int[id_list.length];
        for (int i = 0 ; i  < id_list.length ; i++ ) {
        	// id_list[i] 가 신고한사람들 목록(attackList.get(id_list[i])에서 몇 명이 실제 신고당했는지 answer[i]++
            for (String name : attackList.get(id_list[i])){
            	if (attacked.get(name).size() >= k) answer[i]++;
            }
            System.out.println(answer[i]);
        }
    
        return answer;
    }
}