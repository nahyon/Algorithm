import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 17141 _ 연구소 2
public class Main {
	static int N, M;
	static int[][] map;
	static ArrayList<Node> possibleVirus; //놓을 수 있는 곳 몇 갠지 모르니까 ArrayList
	static Node[] startVirus;
	static int minDays = Integer.MAX_VALUE;
	static int space;
	
	static class Node{
		int r, c; int day;
		public Node(int r, int c) {
			this.r = r; this.c = c;
		}
		public Node(int r, int c, int day) {
			this.r = r; this.c = c; this.day = day;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 연구소 크기 N * N
		M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스 개수 M (1이상 10이하)
		
		map = new int[N][N];
		possibleVirus = new ArrayList<>(); // 바이러스 놓을 수 있는 곳  (2였던 곳)
		
		space = 0 ;
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				
				//벽(1)이면 -1, 바이러스 퍼질 수 있으면(0, 2) -2
				if (n != 1) { //바이러스 퍼질 수 있음 (0, 1)
					space++;
					map[i][j] = -2;
					
					 //바이러스 놓을 수 있음
					if (n == 2) possibleVirus.add(new Node(i, j));
				} 
				else map[i][j] = -1;
			}
		}
		
		// possibleVirus (2였던곳) 개수(cnt) 중에서 M개 뽑아야함
		int cnt = possibleVirus.size();
		startVirus = new Node[M]; //뽑은 곳들 저장
		
//		space -= M; //바이러스 더 퍼져야하는 공간
		
		dfs(0, 0);
		
		
		
		if (minDays == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minDays);
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// M개 뽑기 (Combination)
	static void dfs(int depth, int startIdx) {
//		if (depth == M) return;
		// 종료 조건 : 뽑은 M개에 대해 바이러스 퍼뜨리러 감
		if (depth == M) {
			expandVirus();
			return;
		}
		
		int possibleCnt = possibleVirus.size();
		for(int idx = startIdx; idx < possibleCnt ; idx++	) {
			startVirus[depth] = possibleVirus.get(idx);
			dfs(depth+1, idx+1);
		}
	}
	static void expandVirus() {
		boolean[][] visit = new boolean[N][N];
		
		Queue<Node> queue = new ArrayDeque<>();
		for (Node node : startVirus) {
			queue.offer(new Node(node.r, node.c, 0));
			visit[node.r][node.c] = true;
		}
		
		int cnt = 0; int day = -1;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int curR = cur.r; int curC = cur.c; 
			day = cur.day;
			cnt++;
			
			for (int i = 0 ; i < 4; i++) {
				int nextR = curR + dr[i];
				int nextC = curC + dc[i];
				
				if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N) continue; //OOR
				if (map[nextR][nextC] == -1) continue; //벽
				if (visit[nextR][nextC]) continue; //이미 방문
				
				queue.offer(new Node(nextR, nextC, day+1));
				visit[nextR][nextC] = true;
			}
		}
		// 다 빠져나오면 모든 가능한 공간까지 바이러스 다 퍼진 것
		if (space == cnt) { // 모든 공간에 다 퍼짐
			if ((day < minDays) && (day != -1))
				minDays = day;
		}
		
		
	}
}
