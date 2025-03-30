import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2294 동전2 
// DP
// 동전 중복 사용 가능 ,순서X 
// 금액 K를 만들 때 "(최소) 동전 개수" 구하기
public class Main {
	static final int MAX = 10001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 가치의 합 
		
		int[] coins = new int[N];
		for (int i = 0 ; i < N ;i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		// dp[i] : 금액 i일 때 "최소 동전 개수"  
		int[] dp = new int[K+1];
		Arrays.fill(dp, MAX);
		dp[0] = 0; // i-coin이 0이 될 때를 생각 
//		for (int i = 0 ;i <= K ; i++) {
//			for (int coin : coins) {
//				if (coin > i ) continue;
//				dp[i] = Math.min(dp[i], dp[i-coin] + 1 );
//			}
//		}
		for (int coin : coins) {
			for (int i = 0 ;i <= K ; i++) { // 이번에 보는 coin으로 금액 K까지 다 보고 채우기
				if (coin > i ) continue;
				dp[i] = Math.min(dp[i], dp[i-coin] + 1 );
			}
		}
		System.out.println(dp[K]==MAX ? -1 : dp[K]);
	
	}

}
