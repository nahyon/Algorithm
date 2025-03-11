import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 9466 _ 텀 프로젝트 
public class Main {
	static boolean[] isVisited ;
	static boolean[] isFinished ;
	static int[] next;
	static int cnt ;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0 ; t < T ; t++) { // 테스트 케이스 
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			isVisited = new boolean[N+1];
			isFinished = new boolean[N+1];
			
			next = new int[N+1]; 
			for (int i = 1 ; i <= N ; i++) {
				next[i] = Integer.parseInt(st.nextToken());
			}
			
			cnt = 0;
			for (int i = 1; i <= N ; i++) {
				if (isFinished[i]) continue; // 이미 그룹 완성된 애들
				dfs(i);
			}

			sb.append(N-cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	static void dfs(int x ) { 
		
		if (isFinished[x]) return;
		
		if (isVisited[x]) { // 사이클 형성됨
			
			// 개수 세기 
			int temp = x;
			int count = 0;
			do {
				count++;
				isFinished[temp] = true;  // 사이클 형성 완료되는 애들 표시 
				temp = next[temp]; // 다음 
			} while (temp != x);  // 다시 처음으로 돌아올때까지 
			
			cnt += count;
			return;
		}
		
		isVisited[x] = true;	
		dfs(next[x]);
		isFinished[x] = true; //
		isVisited[x] = false; // 원복 
		
	}
}