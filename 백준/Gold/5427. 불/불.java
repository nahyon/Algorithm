import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 5427 _ 불
public class Main {
	static int[][] map;
	static boolean[][] visit;
	static Queue<Node> fireQueue;
	static int w, h;
	static StringTokenizer st = null;
	static StringBuilder sb = null;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for (int t = 0 ; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken()); //너비 (열)col 가로
			h = Integer.parseInt(st.nextToken()); //높이 (행)row 세로
			
			//맵 초기화
			int startR = -1, startC = -1; //시작위치
			map = new int[h][w];
			visit = new boolean[h][w];
			fireQueue = new ArrayDeque<>();
			for (int i = 0 ; i < h ; i++) { //행
				char[]arr = br.readLine().toCharArray();
				// . : 빈공간 -> 0 / # : 벽 -> -1 / @ : 시작위치 -> 0 / * : 불 -> 1(1일을 뜻함)
				for (int j = 0 ; j < w ; j++) {
					
					if (arr[j] == '@') { //시작 위치 저장
						startR = i ; startC = j;
						map[i][j] = 0 ; //변경 : 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다. 떄문
					}
					
					else if (arr[j] == '#') { // 벽 = -1
						map[i][j] = -1;
					}
					
					else if (arr[j] == '*') { // 불
						map[i][j] = 1; // 불 초기화 : 1
						fireQueue.offer(new Node(i, j, 1)); // 불일 때 : 초기 fire큐 초기화
					}
					else map[i][j] = 0; // 일반 반공간(.) : 0
				}
			}
			//맵 끝
			expandFire(); // fire 번지는거 변경
			
			bfs(startR, startC);
			
		} //테스트케이스 완료
		System.out.println(sb);

	}
	static class Node {
		int r, c, day;
		
		public Node(int r, int c) {
			this.r = r; this.c = c;
		}
		public Node(int r, int c, int day) {
			this.r = r; this.c = c; this.day = day;
		}
	}
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void bfs(int curR, int curC) {
		Queue<Node> queue = new ArrayDeque<>();
		visit[curR][curC] = true; // 시작 위치 @ visit 처리
		queue.offer(new Node(curR, curC, 1)); //1일차
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			curR = cur.r;
			curC = cur.c;
			int day = cur.day; //내 방문 날짜
			
			// 종료조건
			if (isFinish(curR, curC)) {
				sb.append(day).append("\n");
				return;
			}
			
			for (int i = 0 ; i < 4; i++) {
				int nextR = cur.r + dr[i];
				int nextC = cur.c + dc[i];
				
				if (nextR<0 || nextC<0 || nextR>=h || nextC>=w ) continue;
				if (visit[nextR][nextC]) continue;
				// 벽
				if (map[nextR][nextC] ==  -1) continue; //벽일 때
				// day일 당시 인근 [nextR][nextC] 이 day+1이하면 && 1이상이면 불 or 불 에정이라 갈 수 없는 곳
				if (map[nextR][nextC] >= 1 && map[nextR][nextC] <= day+1) continue; // 오늘 이미 불 or 내일 불 예정일 때 > 0 && <= today+1
				
//				if (map[nextR][nextC] == 0) queue.offer(new Node(nextR, nextC)); //빈 공간만 넣음
				queue.offer(new Node(nextR, nextC, day + 1));
				visit[nextR][nextC] = true;
			}
			
		}
		sb.append("IMPOSSIBLE").append("\n");
	}
	
	
	static void expandFire() {
		// 처음 fireQueue에 있는 것만 검사하고 / while문안에서 새로 큐에 추가되는 것들은 검사하면안됨.((더퍼지면안됨)
		while(!fireQueue.isEmpty()) {
			Node cur = fireQueue.poll();
			int today = cur.day;
			
			for (int i = 0 ; i < 4; i++) {
				int nextR = cur.r + dr[i];
				int nextC = cur.c + dc[i];
				
				if (nextR<0 || nextC<0 || nextR>=h || nextC>=w ) continue;
//				if (visit[nextR][nextC]) continue;
				if (map[nextR][nextC] > 0) continue; //방문처리 대신 이미 불인건 >0으로 판단??? 맞나모르겠음
				if (map[nextR][nextC] == -1) continue; //벽
				
				// 빈공간 = 0 / 이미 불 > 0 / 벽 = -1 
				
//				if (map[nextR][nextC] == 0) { // 인근이 빈공간일 떄
					map[nextR][nextC] = today + 1 ; //불로바꿈(다음날짜로)
					fireQueue.offer(new Node(nextR, nextC, today + 1));	
//				}
			}
			
		}
		
	}

	static boolean isFinish(int r, int c) {
		if (r==0 || c==0 || r==h-1 || c==w-1) return true;
		return false;
	}
}
