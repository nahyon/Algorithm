import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2293 동전1 
// DP
// 동전 중복 사용 가능 ,순서X 
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 가치의 합 
		
		int[] coins = new int[N];
		for (int i = 0 ; i < N ;i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		// dp[i] : 금액 i일 때 동전 조합 경우의 수 
		int[] dp = new int[K+1];
		dp[0] = 1; // i-coin이 0이 될 때를 생각 
//		for (int i = 0 ;i <= K ; i++) {
//			for (int coin : coins) {
//				if (coin > i ) continue;
//				// 이 동전 쓰면 + dp[i-coin] , 안쓰면 1
//				dp[i] += (dp[i-coin] );  // (x) dp[7]볼 때 dp[6], dp[5], dp[3]을 본다고 칠 때,dp[6]안에 이미 dp[3]의 경우의 수가 들어가있을 수 있음 
//			}
//		}
		for (int coin : coins) {
			for (int i = 0 ;i <= K ; i++) { // 이번에 보는 coin으로 금액 K까지 다 보고 채우기
				if (coin > i ) continue;
				dp[i] += (dp[i-coin] );
			}
		}
		System.out.println(dp[K]);
	
	}

}
