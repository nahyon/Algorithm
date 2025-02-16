import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2283 _ 구간 자르기
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken()); // N개의 선분 (1이상 1000이하)
		int K = Integer.parseInt(st.nextToken()); // 길이의 총합 K (1이상 1억이하) 
	
		int start=Integer.MAX_VALUE, end=0;
		int[] cnt = new int[1000002];// 길이 범위 : 0이상 100만이하
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			// 0 이상 1,000,000 이하의 정수
			int a = Integer.parseInt(st.nextToken()); // 시작
			int b = Integer.parseInt(st.nextToken()); // 끝

			start = Math.min(start,  a);
			end = Math.	max(end,  b);
			
			///
	        cnt[a]++;  // 시작점에서 +1
            cnt[b]--;  // 끝점에서 -1
		}
		
        for (int i = start+1; i <= end; i++) {
            cnt[i] += cnt[i - 1];
        }

        
		
		int left = 0; 
		int right = left+1;
		
		boolean flag = false;
		
		int sum = cnt[left]; // 시작 초기화
		while (left <= right && right<=end+1) {
			if (sum == K) {
				flag = true;
				break;
			}
			
			if (sum > K) { // 목표K보다 크면 left++
				sum -= cnt[left];
				left++;
			} else { // 목표K보다 작으면 right++
				sum += cnt[right];
				right++;
			}
		}
		if (flag) System.out.println(left + " " + right);
		else System.out.println("0 0");
	}
}
