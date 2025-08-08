import java.util.*;
import java.util.Map.Entry;
class Solution {
    public int solution(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        
        for (Entry<String, Integer> entry : map.entrySet() ) {
            String str = entry.getKey();
            Integer num = entry.getValue();
            // int -> string
            String numstr = Integer.toString(num);
            // System.out.println(str + " : " + numstr + " " + s.contains(str));
            if (s.contains(str)) s = s.replace(str, numstr) ; // s= 해줘야함
        }


        int answer = Integer.parseInt(s);
        return answer;
    }
}