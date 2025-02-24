import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] map ;
	static int[][] changedMap;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		changedMap = new int[N][M];
		for (int i = 0 ; i < N ;i++ ) {
			Arrays.fill(changedMap[i], -1);
		}
		
		int r = 0; int c = 0;
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 2) { // 시작점 
					r = i; c = j;
				} else if (n == 1)	map[i][j] = true;
				// (n==2) map[i][j] = false;
				else changedMap[i][j] = 0; //원래 갈 수 없는 곳 
			}
		}
		

		bfs(r, c); // 갈 수 없는 곳 0으로 바꾸기, 시작점 0으로 바꾸기 
		
		// bfs 끝난 후 상태 : 어짜피 못가는 곳은 -1로 그대로임
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < N; i++) {
			for (int j = 0; j < M ; j++ ) {
				sb.append(changedMap[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static void bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		changedMap[r][c] = 0 ; // 시작점 0
		queue.offer(new int[] {r, c, 0});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			r = cur[0];
			c = cur[1];
			int cnt = cur[2]; // 현재 거쳐온 칸의 개수 
			
			for (int i = 0; i < 4 ; i++) {
				int nextR = r+dr[i];
				int nextC = c+dc[i];
				
				// 범위 벗어남
				if (nextR <0 || nextC < 0 || nextR >=N || nextC >= M) continue;
				// 갈 수 없는 곳 (map[i][j] == false )
				if (!map[nextR][nextC]) {
					changedMap[nextR][nextC] = 0; //0으로 바꾸기 
					continue;
				}
				// 이미 방문한 곳 
				if (changedMap[nextR][nextC] != -1 ) continue;
				
				
				// 방문 안 한 곳 (changedMap[nextR}[nextC] == -1)
				changedMap[nextR][nextC] = cnt + 1;
				queue.offer(new int[] {nextR, nextC, cnt+1});
				
			}
		}
	}
	
}
