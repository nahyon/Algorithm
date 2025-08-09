import java.util.*;
// import java.util.Map.Entry;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        // <문자 : 몇 번>
        HashMap<Character, Integer> map = new HashMap<>();
        
        // target문자를 보면 같은 문자 여러번 쓰이기 때문에, (타겟길이 최대100) 각 문자가 몇 번 인지 맵 사용해서 미리 저장해둔거 사용하기
        for (String s : keymap) {
            for (int idx = 0; idx < s.length(); idx++) {
                char c = s.charAt(idx); //현재 문자
                if (map.getOrDefault(c, 101) > idx+1 ) map.put(c, idx+1) ; // 더 작은 수로 업데이트
            }            
        }
        
        // map 확인
        // for (Entry<Character, Integer> entrySet : map.entrySet()) {
        //     System.out.println(entrySet.getKey() + " " + entrySet.getValue());
        // }
        
        int[] answer = new int[targets.length];
        int i = 0;
        
        for (String target: targets) {
            int cnt = 0;
            char[] targetArr = target.toCharArray();
            for (char c : targetArr) {
                if (!map.containsKey(c)) {
                    cnt = -1; 
                    break;
                }
                cnt += map.get(c);
            }
            answer[i++] = cnt;
        }
        
        return answer;
    }
}