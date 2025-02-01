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
		
		T = new int[N+2];
		P = new int[N+2];
		
		StringTokenizer st = null;
		for (int i = 1 ; i <= N ;i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		// N일차 일까지 다 고려했을 때 최고의 profit을 구하기
        int[] dp = new int[N+2];
        int maxProfit = 0;
        for (int day = 1 ; day <= N+1 ; day++) {
            maxProfit = Math.max(dp[day], maxProfit) ; //이전까지 고려된 최고의 수익 저장
            if (day + T[day] > N+1) continue;
            dp[day + T[day]] = Math.max(dp[day + T[day]], maxProfit + P[day]);
        }
		System.out.println(maxProfit);
		
	}

}
