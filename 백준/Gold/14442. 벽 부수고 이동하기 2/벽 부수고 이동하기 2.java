import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 14442 _ 벽 부수고 이동하기 2 (B2206벽부수고이동1 응용)
// 벽부수고이동1은 벽 1번만 부숨, 이번엔 횟수 주어짐 -> 3차원방문배열 높이 더 커짐
public class Main {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M, K; // N * M 행렬로 표현된 맵 , 부술 수 있는 횟수
	static int[][] map;
	static boolean[][][] isVisited; // 방문배열 3차원

	static class Node {
		int r, c;
		int dist;
		int leftK; //벽 부술 수 있는 남은 횟수

		public Node(int r, int c, int dist, int leftK) {
			this.r = r;
			this.c = c;
			this.dist = dist; // 이 노드까지 오는데에 걸린 최단경로
			this.leftK = leftK; // 이전에 벽 뚫고 온 경우 true
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1]; // (1,1)시작 (N,M)위치까지 이동

		for (int r = 1; r <= N; r++) {
			char[] str = br.readLine().toCharArray();
			for (int c = 1; c <= M; c++) {
				map[r][c] = str[c - 1] - '0';
			}
		} // 맵 완성 (0 이동가능, 1 벽)

		isVisited = new boolean[K+1][N + 1][M + 1]; // [K] : 벽 k번 뚫은 경우 (0번~k번까지 존재 -> 크기 K+1)

		bfs(1, 1); // 시작정점(1, 1)
		
		br.close();
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static void bfs(int startR, int startC) {
		Queue<Node> queue = new ArrayDeque<>();
		
		//출발지 초기 설정
		for (int i = 0 ; i <= K; i++) {
			isVisited[i][startR][startC] = true; //방문처리
		}
		queue.offer(new Node(startR, startC, 1, K)); // 출발지에서 dist는 1, leftK(남은K)는 K
		//저장되는 맵 : [K-leftK]

		while (!queue.isEmpty()) {
			
			Node cur = queue.poll();

			// 종료조건 : 최종목적지 도달 (도달못하면 while문 밖에서 -1 출력)
			if (cur.r == N && cur.c == M) { 
				System.out.println(cur.dist);
				return;
			}

			// 사방탐색
			for (int i = 0; i < 4; i++) {
				int r = cur.r + dr[i];
				int c = cur.c + dc[i];

				// 맵 범위 벗어나면 continue
				if (r < 1 || c < 1 || r > N || c > M)
					continue;

				// <1> 벽이다.
				if (map[r][c] == 1) {
					// <1-2(1)> 부술 기회가 남음. -> (i) 부순다 (ii) 안부순다 (ㄴㄴ)
					if (cur.leftK > 0) {
						// (i) 부순다
						// 이미 visit[K-leftK + 1] true면 못 간다. 
						if (isVisited[K-cur.leftK+1][r][c]) continue;
						isVisited[K-cur.leftK+1][r][c] = true; //[K-leftK + 1]
						queue.offer(new Node(r, c, cur.dist+1, cur.leftK - 1)); //cur.leftK - 1
					}
				}
				
				// <2> 벽이 아니다. -> 그냥 이동
				else {
					// 이미 visit[K-leftK] true면 못 간다. 
					if (isVisited[K-cur.leftK][r][c]) continue;
					isVisited[K-cur.leftK][r][c] = true; //[K-leftK] 그대로
					queue.offer(new Node(r, c, cur.dist+1, cur.leftK)); //cur.leftK 그대로
				}
			}
		}
		System.out.println(-1);
	}

}
