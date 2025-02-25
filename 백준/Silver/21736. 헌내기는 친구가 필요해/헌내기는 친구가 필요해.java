import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int N, M;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int r=0, c=0;
		map = new char[N][M];
		for (int i = 0 ; i < N ; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0 ; j <M ;j++) {
				if (map[i][j] == 'I') {
					r = i; c = j;
				}
			}
		}
		
		bfs(r, c);
		System.out.println(cnt == 0 ? "TT" : cnt);
		
	}
	static void bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		boolean[][] isVisited = new boolean[N][M];
		isVisited[r][c] = true;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			r = cur[0];
			c = cur[1];
			
			for (int i = 0 ; i <4 ;i++) {
				int nextR = r + dr[i];
				int nextC = c + dc[i];
				
				if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M ) continue;
				if (isVisited[nextR][nextC]) continue;
				if (map[nextR][nextC] == 'X') continue; // 벽 
				
				if (map[nextR][nextC] == 'P') cnt++;
				
				isVisited[nextR][nextC] = true;
				queue.offer(new int[] {nextR, nextC});
			}
		}
	}
}
