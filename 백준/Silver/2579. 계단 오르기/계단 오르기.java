
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2579 _ 계단 오르기
// DP
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] stairs = new int[N+1];
		for (int i = 1; i <= N ;i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N+1];
		
		if (N==1) System.out.println(stairs[1]);
		else if (N==2) System.out.println(stairs[1]+stairs[2]);
		else if (N==3) System.out.println(Math.max(stairs[1], stairs[2]) +  stairs[3]);
		else {
			dp[1] = stairs[1]; // 한 계단
			dp[2] = stairs[1]+stairs[2]; //두 계단 연속
			dp[3] = Math.max(stairs[1], stairs[2]) +  stairs[3];  
			
			for (int i = 4; i<=N ; i++) {
				dp[i] = Math.max(dp[i-3]+stairs[i-1], dp[i-2]) +stairs[i];
			}
			
			System.out.println(dp[N]);
		}
	}

	

}
