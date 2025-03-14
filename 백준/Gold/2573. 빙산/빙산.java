import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 2573 _ 빙산
public class Main {
	static StringTokenizer st;
	static int N, M;
	static int[][] map;
	static boolean[][] ice;
	static Queue<Node> queue;
	static boolean[][] visit;
	static int total;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; //3<= N,M <=300 / 빙하<=10,000
		ice = new boolean[N][M];
		queue = new ArrayDeque<>();
		
		for (int r = 0 ; r < N ; r++) {
			st = new StringTokenizer(br.readLine());	
			for (int c = 0 ; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				//빙하라면
				if (map[r][c] > 0) {
					queue.offer(new Node(r, c));
					ice[r][c] = true;
				}
			}
		}
		total = queue.size(); //빙하인애들
		
		
		int year = 0;
		
		boolean flag = false;
		
		while(true) {
			
			//녹이기
			melt();
			
			if (total == 0) break; //개수 셀 것도 없이 실패
			
			year++;
			
//			for (int r = 0 ; r < N ;r++) {
//				for (int c = 0 ; c <M ;c++) {
//					System.out.print(map[r][c] + " ");
//				}
//				System.out.println();
//			}
			
			if (count()) {
				flag = true;
				break;
			}
			
		}
		
		
		if (flag) System.out.println(year);
		else System.out.println(0); //실패

		
		
	}
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	public static void melt() {
		Queue<Node> meltq = new ArrayDeque<>(); //다음에도 빙하인애들
		int size = queue.size(); //이번 단계만 보기
		for (int t = 0 ; t < size; t++) {
			Node cur = queue.poll(); //나 : 빙하
			
			boolean tempflag = false;
			for (int i = 0 ; i < 4; i++) { //인접정점들 살펴본다. 
				int nextR = cur.r + dr[i];
				int nextC = cur.c + dc[i];
				
				if (nextR<0 || nextC<0 || nextR>=N || nextC>=M) continue;
				
				if (ice[nextR][nextC]) continue; //인접정점이 빙하다.
				else { //인접정점이 바다면 나(빙하)자신이 -1
//					if (map[cur.r][cur.c] == 0) continue; //이미 0됐으면 다음으로 
					map[cur.r][cur.c]--; //여기서 녹이면 이게 한 바퀴도는 동안 다른 데에 영향을 미치면 안됨.
					if (map[cur.r][cur.c] == 0) { //바다가 됐다면
						total--;//빙하개수 1개 빼기
						tempflag = true;
						break; //인접정점 더 볼 필요없음
					}
				}
			}
			if (tempflag) meltq.offer(new Node(cur.r, cur.c)); //나(cur)는 바다가 되었다. -> ice맵 바꿔줌
			else queue.offer(new Node(cur.r, cur.c)); //다음번에도 빙하
		}
		//다 끝난 후 ice맵 바꿔주기 (한 단계 내에서는 ice맵을 유지했어야함. 이걸로 if문 분기하기때문)
		
		//meltq에 넣은 애들은 새로 바다가된 애들 -> ice맵 바꿔주기
		size = meltq.size();
		for (int t = 0; t < size; t++) {
			Node cur = meltq.poll();
			ice[cur.r][cur.c]= false; //녹임
		}
		
	}
	static boolean count() {
		//빙하 덩어리 수 세기
		int cnt = 0;
		visit = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c= 0 ; c < M; c++) {
				if(!ice[r][c]) continue; //바다
				if(visit[r][c]) continue;
				// 빙하라면 한묶음씩
				countbfs(r,c);
				cnt++;
				if (cnt >=2 ) { //덩어리 2개이상됨
					return true;
				}
			}
		}
		//다 돌고 나왔는데 덩어리 2개미만이다. 
		return false;
	}
	static void countbfs(int r, int c) {
		Queue<Node> q = new ArrayDeque<>();
		visit[r][c] = true;
		q.offer(new Node(r, c));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int i = 0; i < 4; i++) { //인접정점
				int nextR = cur.r + dr[i];
				int nextC = cur.c + dc[i];
				if (nextR<0 || nextC<0 || nextR>=N || nextC>=M) continue;
				if (visit[nextR][nextC]) continue;
				if (ice[nextR][nextC]) q.offer(new Node(nextR, nextC)); //빙하만 큐에 넣음
				visit[nextR][nextC] = true;
			}
		}
	}
	static class Node{
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}	
	}
}
