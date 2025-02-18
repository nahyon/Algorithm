import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 백준 1149 _ RGB거리
// DP
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 집 개수 N개
		
		int min = Integer.MAX_VALUE;
		
		// 입력 받기
		int[][] cost = new int[N][3];
		for (int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 집 I의 (r, g, b) 비용
			cost[i][0] = Integer.parseInt(st.nextToken()); // R
			cost[i][1] = Integer.parseInt(st.nextToken()); // G
			cost[i][2] = Integer.parseInt(st.nextToken()); // B
		}
		
		// 0번은 [1]만 / [N-1]번은 [N-1]만 고려
		// 1<= i <= n-2 : [i] 는 [i-1] [i+1]과 같으면 안됨
		int[][] dp = new int[N][3];
		// dp배열 0번 초기화
		dp[0][0] = cost[0][0]; //r
		dp[0][1] = cost[0][1]; //g
		dp[0][2] = cost[0][2]; //b
		for (int i = 1 ; i < N ; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
		}
		System.out.println(Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]));
		

	}
}
