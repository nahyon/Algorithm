import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//실버3 _ 1, 2, 3 더하기 
//DP 
//코드트리 _ Trail3. Lesson7. 아이템을 적절히 고르는 문제 
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		int[] coins = {1, 2, 3};
		
		for (int t = 0 ; t < T ; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[N+1];
			dp[0] = 1;
			
			for (int i = 1 ; i <= N ; i++) {
				for (int coin : coins) {
					if (coin > i) continue;
					dp[i] += dp[i-coin];
				}
			}
			sb.append(dp[N]).append("\n");
			
		}
		System.out.println(sb);
		
	

	}

}
