import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14501 _ 퇴사
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
		
		dfs(1, T[1], P[1]); //1일차 3 10
		dfs(1, 1, 0); 		//1일차 0 0 -> 다음거 선택or선택x..
		System.out.println(maxProfit);
		
	}
	
	static void dfs(int day, int until, int profit) {
		if (until > N) { //가지치기 (N일차까진 일할 수 있음)
			return;
		}
		if (day == N+1) { //종료
			maxProfit = Math.max(profit, maxProfit);
			return;
		}
		
		// 오늘(day) 일하고 있다. : 선택안함
		if (day <= until) dfs(day+1, until, profit);
		// 오늘 (day) 일 안하고 있다 : 선택해도되고 안해도됨
		else { // day > until
			dfs(day+1, until, profit);
			dfs(day+1, day + (T[day]-1) , profit + P[day]); 
		}
		
//		// 선택안함
//		dfs(day+1, until, profit);
//		// 이번 일 선택 
//		dfs(day+1, day + T[day] , profit + P[day]); 
		
		
	}
}
