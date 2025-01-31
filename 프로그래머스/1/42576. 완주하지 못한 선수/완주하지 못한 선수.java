import java.util.*; 
import java.util.Map.Entry; 

class Solution {
    public String solution(String[] participant, String[] completion) {
       
        HashMap<String, Integer> count = new HashMap<>();
        
        for (String name : participant ) {
            // 처음 들어가면 횟수 1로 or 이미 있으면 그 값 +1 로 업데이트느낌
            count.put(name, count.getOrDefault(name, 0) + 1); 
        }
        
        for (String name : completion) {
            count.put(name, count.get(name) - 1) ; // 1씩 줄여나가기
        }
        
        String answer = "";
        for (Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() > 0) {
                answer = entry.getKey();
                break;
            }
        }
        return answer;
    }
}