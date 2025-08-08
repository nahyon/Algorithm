class Solution {
    public String solution(String s) {
        int midIdx = s.length() / 2; // 짝수면 midIdx - 1이랑 midIdx
        String answer = "";
        if (s.length() % 2 == 0) answer = s.substring(midIdx-1, midIdx+1);
        else answer = String.valueOf(s.charAt(midIdx));
        return answer;
    }
}