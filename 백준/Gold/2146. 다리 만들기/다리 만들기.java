import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2146 _ 다리만들기 
public class Main {
	static int N;
	static ArrayList<int[]> outline;
	static boolean[][] isVisited;
	static int[][] map;
	static int ansLength = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		isVisited = new boolean[N][N]; 
		map = new int[N][N]; // 0 :육지(-> 1, 2, 3.. 섬 분류) / -1 :바다 
		for (int i = 0 ; i < N ;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) == 1 ? 0 : -1;
			}
		}
		
		
		// 섬 구분
		// & 테두리리스트 add
		outline = new ArrayList<>();
		int x = 1; 
		for (int i = 0 ; i < N ;i++) {
			for (int j = 0 ; j < N ;j++) {
				if (map[i][j] != 0) continue; // 이미 섬 판별 끝 or 바다인 경우 
				setIsland(x, i, j) ; // 섬 판별 안된 육지일 경우
				++x;
			}
		}
		// -> 바다 = -1 / 섬(육지)= 1, 2, 3, ...
	
		
		// outline : 섬 테두리 
		for (int i = 0 ; i < outline.size(); i++) {
			int r = outline.get(i)[0];
			int c = outline.get(i)[1];
			int j = outline.get(i)[2]; // 인접한 섬 번호 
			ansLength = Math.min(ansLength, bfs(r,c, j));
		}
		
		System.out.println(ansLength);
		
	}
	static int bfs(int r, int c, int startIsland) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c, 1}); // 다리 1번째
		isVisited = new boolean[N][N];
		isVisited[r][c] = true;
		
		int cnt = Integer.MAX_VALUE; 
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			r = cur[0]; c = cur[1];
			int nowCnt = cur[2];
			
			if (nowCnt > ansLength) break; // 이미 답 안됨 (cnt 여전히 최댓값)
			
			for (int i = 0 ; i <4 ;i++) {
				int nextR = r + dr[i];
				int nextC = c + dc[i];
				
				if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N) continue;
				if (isVisited[nextR][nextC]) continue; // 이미 방문
				
				isVisited[nextR][nextC] = true;
				if (map[nextR][nextC] > 0 ) { // 섬발견 
					if (map[nextR][nextC] == startIsland) continue; //새로운 섬 아님 
					else { // 새로운 섬. while문 종료 
						cnt = nowCnt;
						break;
					}
				}
				
				// ---------------------
				// if (map[nextR][nextC] == -1) { // 섬에 인접한 바다  
				queue.add(new int[] {nextR, nextC, nowCnt + 1});
			}
			
			if (cnt != Integer.MAX_VALUE) break; //while문 종료 
		}
		
		return cnt;
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static void setIsland(int x, int r, int c) { // 이번 섬번호는 x
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		map[r][c] = x; 
		isVisited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll(); // 육지
			r = cur[0]; c = cur[1];
			
			for (int i = 0 ; i <4 ;i++) {
				int nextR = r + dr[i];
				int nextC = c + dc[i];
				
				if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N) continue;
				if (isVisited[nextR][nextC]) continue; // 이미 방문
//				if (map[nextR][nextC] > 0) continue; //섬 판별 끝난 경우 (이미 방문에 포함됨)
				
				isVisited[nextR][nextC] = true;
				if (map[nextR][nextC] == -1) { // 섬에 인접한 바다 
					outline.add(new int[] {nextR, nextC, x});
					continue;
				}
				
				map[nextR][nextC] = x;
				queue.add(new int[] {nextR, nextC});
				
				
			}
		}
	}

}
