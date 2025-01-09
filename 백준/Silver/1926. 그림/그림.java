import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1926 _ 그림
public class Main {
	static boolean[][] map;
	static boolean[][] visit;
	static int N, M;
	static int cnt, maxArea;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		visit = new boolean[N][M];
		
		
		// 그림 입력받기
//		int startR = -1 , startC = -1; // 1인 부분부터 찾아서 bfs 탐색 시작 위해 시작 인덱스 저장
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M; j++) {
				boolean pic = Integer.parseInt(st.nextToken()) == 1 ? true : false;
				map[i][j] = pic;
				if (!pic) visit[i][j] = true; // 0인 곳 미리 방문처리해두기 
//				if ((startR == -1 && startC == -1) && a) {
//					startR = i; startC = j;
//				}
			}
		}
		
		// 1인 부분부터 찾아서 bfs 탐색 시작
//		if (!(startR == -1 && startC == -1)) bfs(startR, startC);
		for (int i = 0 ; i < N; i++) {
			for (int j = 0 ; j < M ; j++) {
				if (visit[i][j]) continue; //이미 방문 or 그림X인 곳 넘어감
				bfs(i, j);
				cnt++; //그림횟수 + 1
			}
		}
		
		System.out.println(cnt);
		System.out.println(maxArea);
		
	}
	static class Node {
		int r, c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static void bfs(int curR, int curC) {
		Queue<Node> queue = new ArrayDeque<>();
		visit[curR][curC] = true;
		queue.offer(new Node(curR, curC));
		
		int area = 1; // 0아님!
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			curR = cur.r;
			curC = cur.c;
			
			//사방탐색
			for (int i = 0 ; i < 4 ; i++) {
				int nextR = curR + dr[i];
				int nextC = curC + dc[i];
				
				if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;
				if (visit[nextR][nextC]) continue;
				
				if (map[nextR][nextC]) queue.offer(new Node(nextR, nextC)); //이거 if문필요한가?
				area++;
				visit[nextR][nextC] = true; //방문체크
			}
		}
		if (maxArea < area) maxArea = area;
		
	}

}
