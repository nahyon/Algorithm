import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int n = answers.length; // 문제 수
        
        int[] p1ans = new int[n];
        int[] p2ans = new int[n]; // 짝수 2, 홀수 1,3,4,5 반복
        int[] p3ans = new int[n]; // 3 1 2 4 5 2개씩 반복
        
        int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int p1Cnt = 0, p2Cnt = 0, p3Cnt = 0;
        if (answers[0] == 1) p1Cnt++;
        else if (answers[0] == 2) p2Cnt++;
        else if (answers[0] == 3) p3Cnt++;
        for (int i = 1 ; i < n ; i++ ) {
            // p1ans[i] = i%5 + 1 ;
            // p2ans[i] = p2[i%8] ;
            // p3ans[i] = p3[i%5] ;
            
            // 정답 비교
            int ans = answers[i];
            if ((i%5 + 1) == ans) p1Cnt++;
            if (p2[i%8] == ans) p2Cnt++; // 0 1 2 3 4 5 6 7
            if (p3[i%10] == ans) p3Cnt++;
        }

        int max = Math.max(p1Cnt, Math.max(p2Cnt, p3Cnt));
        
        if (max == p1Cnt && max == p2Cnt && max == p3Cnt) return new int[]{1,2,3};
        else if(max == p1Cnt && max == p2Cnt) return new int[]{1,2};
        else if(max == p1Cnt && max == p3Cnt) return new int[]{1,3};
        else if(max == p2Cnt && max == p3Cnt) return new int[]{2,3};
        else if(max == p1Cnt) return new int[]{1};
        else if(max == p2Cnt) return new int[]{2};

        return new int[]{3};

    }
}