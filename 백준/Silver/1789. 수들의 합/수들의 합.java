import java.util.*;
import java.io.*;

public class Main {
    public static final int MAX_S = 2000000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long target = Long.parseLong(br.readLine()); //목표하는 합

        // 1~n까지 합 공식 : (n*(n+1))/2
        // TTTTTFFFFF 
        // 합이 s이하인걸 만족하는 범위 중 가장 최대 n 구하기
        long left = 1;
        // long right = target;
        long right = MAX_S;
        long maxNum = 0; //
        while (left <= right) {
            long mid = (left+right)/2 ;

            // 1부터 mid까지 합 = mid*(mid+1)/2
            if ( mid*(mid+1)/2 <= target) {
                left = mid + 1; // 왼쪽 늘리기 
                maxNum = Math.max(maxNum, mid);
            } else right = mid -1; // 오른쪽 더 줄이기 
        }

        System.out.println(maxNum);

    }

    
}