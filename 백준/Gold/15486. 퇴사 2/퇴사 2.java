import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15486 _ 퇴사
public class Main {
	static int N;
	static int[] T, P;
	static int maxProfit;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		T = new int[N+1];
		P = new int[N+1];
		
		StringTokenizer st = null;
		for (int i = 1 ; i <= N ;i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		
		int[] dp = new int[N+2];
		int maxProfit = 0;
		for (int day = 1 ; day <= N ; day++) {
			maxProfit = Math.max(dp[day], maxProfit) ; // 이 전날까지 고려된 가장 최고수익 갱신
			int until = day + T[day] - 1 ; // 'until'일까지 일함
			if (until > N) continue;
			dp[until+1] = Math.max(dp[until+1], maxProfit + P[day]); //기존거 vs 오늘거 선택
		}
		maxProfit = Math.max(dp[N+1], maxProfit) ;
		
		System.out.println(maxProfit);
		
	}

}
