import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < m ;  i++){
            st = new StringTokenizer(br.readLine());
            int lo = Integer.parseInt(st.nextToken());
            int hi = Integer.parseInt(st.nextToken());

            // hi의 upperBound , lo의 lowerBound
            int cnt = upperBound(arr, hi) - lowerBound(arr, lo) + 1;
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    // num"이상"을 만족하는 값들 중 idx가 가장 작은 값 ( 이 리턴값 포함시키면됨)
    static int lowerBound(int[] arr , int num) {
        
        int left = 0 ; int right = arr.length - 1;
        int minIdx = arr.length; // 가장 최대로 설정

        while (left <= right) {
            int mid = (left + right)/2;
            
            if (num <= arr[mid]) {
                right = mid -1;
                minIdx = Math.min(mid, minIdx);
            }
            else left = mid +1;
        }
        return minIdx; 
    }

    // num이하를 만족하는 값들 중 idx가 가장 큰 값 (이 리턴값 포함시키면됨)
    static int upperBound(int[] arr , int num) {
        int left = 0 ; int right = arr.length - 1;
        int maxIdx = -1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (num >= arr[mid]) {
                left = mid + 1;  
                
                maxIdx =Math.max(mid, maxIdx);
            }
            else right = mid - 1;
        }
        return maxIdx;

    }
}
