import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 2206 _ 벽 부수고 이동하기
// 3차원 방문배열 사용 문제
public class Main {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M; //N * M 행렬로 표현된 맵
	static int[][] map;
	static int[][][] isVisited; //방문배열 3차원
	static int cnt ; //정답. 칸의개수 (시작 칸 포함해서 세기 떄문에 cnt=1로 초기화) 
	//불가능하면  -1 출력
	
	static class Node {
		int r, c;
		int dist;
		boolean flag;
		public Node(int r, int c, int dist, boolean flag) {
			this.r = r;
			this.c = c;
			this.dist = dist; //이 노드까지 오는데에 걸린 최단경로
			this.flag = flag; //이전에 벽 뚫고 온 경우 true
		}
		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", dist=" + dist + ", flag=" + flag + "]";
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1]; //(1,1)시작 (N,M)위치까지 이동
		
		for (int r = 1; r <= N; r++) {
			char[] str = br.readLine().toCharArray();
			for (int c = 1; c <= M; c++) {
				map[r][c] = str[c-1] -'0';
			}
		}//맵 완성 (0 이동가능, 1 벽)
		
		//////////
		isVisited = new int[2][N+1][M+1]; //[0]벽안뚫은경우 / [1]벽 뚫은경우
//		isVisited[0][1][1]=isVisited[1][1][1] = 1; //출발지 1로 초기화
		bfs(1, 1);
		
//		System.out.println(Math.min(isVisited[0][N][M], isVisited[1][N][M]) == 0 ? -1 : Math.min(isVisited[0][N][M], isVisited[1][N][M]));
		
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void bfs(int startR, int startC) {
		Queue<Node> queue = new ArrayDeque<>();
		
		queue.offer(new Node(startR, startC, 1, false)); //출발지 dist는 1
		isVisited[0][startR][startC]=isVisited[1][startR][startC] = 1; //출발지 1로 초기화
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll()	;
			
//			System.out.println(cur);
			
			if (cur.r == N && cur.c == M) {
				System.out.println(cur.dist);
//				break;
				return;
			}
			
			boolean flag = cur.flag; //이번 노드가 이전에 벽 부수고 온 앤지(true->visit[0]에 저장) 안부순앤지(..?
			//처리
			
			//사방탐색
			for (int i = 0 ; i < 4; i++) {
				int r = cur.r + dr[i];
				int c = cur.c + dc[i];
				
				//맵 범위 벗어나면 continue
				if (r < 1 || c < 1 || r > N || c > M) continue;
				
				//이미 visit한 곳이라면 ??? 
				//BFS면 당연히 맨처음에 값 업데이트된게 최솟값이다. 
				//flag에 따라서, 내가 이번에 갈 곳이 이미 방문됐는지 안됐는지 본다.
				if (flag && isVisited[1][r][c] > 0) continue;
				if (!flag && isVisited[0][r][c] > 0 ) continue;

				
				//이전에 벽을 부수고 왔다면(flag),
				if (flag) {  //true
					
					if (map[r][c] == 1)  //이전에 벽을 부수고 왔는데, 인접 노드가 벽이면(map[r][c] == 1) 
						continue;			// 아무것도못한다. -> continue
					
					//벽 아니면 (이전칸 cur.r, cur.c에서 +1) flag==true였으니까 [1]에 저장되어있음
					//벽 부수고 온 값 저장하는 배열은[1] , 이전정점은 (cur.r)(cur.c) 여기서 이번 칸 (r, c)으로 온 것
//					isVisited[1][r][c] = isVisited[1][cur.r][cur.c]+1;
					isVisited[1][r][c] = cur.dist+1;
					queue.offer(new Node(r, c, isVisited[1][r][c], true));  //true : 이미 이전에 부수고왔으니까 그대로(flag는 true)넘겨줌
				}
				else { //false --> 이번에 벽 부술수도 , 안부술수도
					if (map[r][c] == 0) { //벽 없음 . 그냥 갈 수 있으면
						//안부수고 온 상태고, 안부수고 지나가니까 cur.dist +1  => [0]배열 이번에보는정점(r,c)에 값 저장
						isVisited[0][r][c] = cur.dist+1;
						queue.offer(new Node(r, c, isVisited[0][r][c], false));  //false : 여전히 안부순상태
					} else { //벽일 경우, 부술수도있고 안부술수도있다.
						//안부순다.
						//xx
						
						//부순다.
						isVisited[1][r][c] = cur.dist+1;
						queue.offer(new Node(r, c, isVisited[1][r][c], true));  //부순거니까 [1]배열 true : 부숨
						
					}
				}
			}
		}
		System.out.println(-1);
	}

}
