import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M; 
	static int[][] map ;
	static Queue<int[]> fireQ;
	static boolean[][] fireVisited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0 ; t < T ; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			// -1 : 일반 땅
			// -2 : 벽
			// 0 ~ 양수 : 불 옮겨붙는 시간
			fireQ = new ArrayDeque<>();
			fireVisited = new boolean[N][M];
			
			int startR = 0 ; int startC = 0; //시작위치 
			for ( int i = 0 ; i < N ;i++) {
				char[] arr = br.readLine().toCharArray();
				for (int j = 0 ; j < M ; j++) {
					if(arr[j] == '@') {
						startR = i; startC = j; //시작위치 초기화 
						map[i][j] = -1 ; //일반 땅으로 변경 
					} else if (arr[j] == '#') map[i][j] = -2;
					else if(arr[j] == '.') map[i][j] = -1;
					else { // 불
						map[i][j] = 0;
						fireQ.offer(new int[] {i, j, 0});
					}
				}
			}
			expandFire();
			int ans = bfs(startR, startC);
			sb.append(ans == -1 ? "IMPOSSIBLE" : ans).append("\n");
		}
		System.out.println(sb);

	}
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void expandFire() {
		while(!fireQ.isEmpty()) {
			int[] cur = fireQ.poll();
			int curR = cur[0]; int curC = cur[1]; int day = cur[2];
			for (int i = 0 ; i < 4 ;i++ ) {
				int nextR = curR + dr[i];
				int nextC = curC + dc[i];
				
				if (nextR<0 || nextC<0 ||nextR>=N || nextC>=M) continue;
				if (map[nextR][nextC] >= 0 ) continue; // 이미 불이 옮겨붙은 곳 
				if (map[nextR][nextC] == -2) continue; //벽 불가능
				
				// 일반 땅이거나 아직 불이 옮겨붙지않은 곳 
				map[nextR][nextC] = day+1;
				fireQ.offer(new int[] {nextR, nextC, day+1});
			}
		}
	}
	
	// 상근이 이동시키기 
	static int bfs(int startR, int startC) {
		boolean[][] isVisited = new boolean[N][M];
		isVisited[startR][startC] = true;
		Queue<int[]> queue= new ArrayDeque<>();
		queue.offer(new int[] {startR, startC, 0}) ;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curR = cur[0]; int curC = cur[1]; int day = cur[2];
			

			// 종료조건 : 테두리에 도달 시, day+1일 때 탈출 
			if (curR == N-1 || curC == M-1 || curR == 0 || curC == 0) return day+1;
			
			for (int i = 0 ; i < 4 ;i++ ) {
				int nextR = curR + dr[i];
				int nextC = curC + dc[i];
				
				if (nextR<0 || nextC<0 ||nextR>=N || nextC>=M) continue;
				if (isVisited[nextR][nextC] ) continue; 
				if (map[nextR][nextC] == -2) continue; //벽 불가능
				// 현재 불 or 다음 날 불인 경우 
				if (map[nextR][nextC] >= 0 && map[nextR][nextC] <= day+1) continue;
				
				// 일반 땅이거나 다음날까지 불이 아니라 갈 수 있음 
				isVisited[nextR][nextC] = true;
				queue.offer(new int[] {nextR, nextC, day+1});
			}
		}
		
		return -1;
	}
	
	static void printMap() {
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < M ;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
