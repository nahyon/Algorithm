import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int INF = 999_999_999;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine()); // M개의 간선
		
		int[][] graph = new int[N+1][N+1];
		for (int i = 1 ; i<= N; i++) {
			Arrays.fill(graph[i], INF);
			graph[i][i] = 0; //자기자신
		}
		
		// 입력
		StringTokenizer st = null;
		for (int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if (graph[start][end] > 0) graph[start][end] = Math.min(graph[start][end], weight);
			else graph[start][end] = weight;
		}
		
		for (int k = 1; k <= N; k++) { // 경유지
			for (int i = 1 ; i <= N; i++) {  // 시작
				for (int j = 1 ; j <= N ; j++) { //종점
					if (graph[i][k] + graph[k][j] < graph[i][j]) 
						graph[i][j] = graph[i][k] + graph[k][j] ;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1 ; i <= N; i++) {  // 시작
			for (int j = 1 ; j <= N ; j++) { //종점
				if (graph[i][j] == INF) graph[i][j] = 0;
                sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}
