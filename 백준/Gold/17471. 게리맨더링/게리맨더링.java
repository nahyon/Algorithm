import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 17471 _ 게리맨더링
public class Main {
	static StringTokenizer st;
	static int N; // 2<= N <= 10 
	//인접행렬로 받기
	static boolean[][] map; //인접행렬
	static int[] peopleCnt; //인구수
	static int diffMin = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //N개의 구역
		//인구 입력받기
		peopleCnt = new int[N+1]; //인덱스 0 사용안함
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			peopleCnt[i] = Integer.parseInt(st.nextToken());
		}
		//인접 여부 채우기
		map = new boolean[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken()); //이 구역과 인접한 구역의 수
			for (int j = 0; j < cnt; j++) {
				map[i][Integer.parseInt(st.nextToken())] = true;
			}
		} //인접행렬 채우기 완
		// 입력받기 완료 //
		
		groupAtf = new boolean[N+1];
		makeA(1, 0); //depth 는 1부터시작(인덱스 1부터 사용하니까)
		
		if (diffMin == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(diffMin);
		
	}
	static boolean[] groupAtf;
	public static void makeA(int depth, int cntA) { //cntA : 그룹A에 포함된 구역 개수
		//N개 다 고려완료
		if (depth == N+1) {
			if (cntA == 0 ) return; //적어도 하나의 구역은 포함해야함
			int diff = check();
			if (diff != -1) {
				diffMin = Math.min(diffMin, diff);
			}
			return;
		}
		
		groupAtf[depth] = true;
		makeA(depth+1, cntA+1);
		groupAtf[depth] = false;
		makeA(depth+1, cntA);
	}
	public static int check() {
		//이어져있는지 확인 -> 그룹형성안되면 -1리턴(false의 의미)
		//이어져있다면 그룹 A, B인구수 차이 리턴
		int cntA = 0, cntB = 0; 

		boolean[] groupBtf = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			if (!groupAtf[i]) groupBtf[i] = true;
		}

		
		cntA = checkConnect(groupAtf);
		if (cntA == -1 ) return -1; //A그릅형성 실패
		cntB = checkConnect(groupBtf);
		if (cntB == -1 ) return -1; //B그릅형성 실패
		
		return Math.abs(cntA-cntB); //둘 다 그룹형성 성공 시, 그 차이 리턴
	}
	//그룹이 형성되는지, 인접여부 체크
	//
	public static int checkConnect(boolean[] group) { 
		//group[i] : true면 i는 그 그룹에 속한 애
		boolean[] visit  = new boolean[N+1];		
		Queue<Integer> queue = new ArrayDeque<>();
		
		int start = 0;
		int size = 0; //이번에 살펴볼 그룹의 사이즈
		for (int i = 1; i <=N ;i++) {
			if (group[i]) {
				start = i;
				size++;
			}
		}
		
		queue.offer(start);
		visit[start] = true;
		int cnt = 1;
		int sum = peopleCnt[start];
		while(!queue.isEmpty()) {
			int cur = queue.poll();
//			System.out.println("[cur] : "+ cur);
			for (int i = 1 ; i <= N ; i++) {
//				System.out.println("[i] : " + i + " " + visit[i] + map[cur][i] + group[i]);;
				if (!visit[i] && map[cur][i] && group[i] ) {
					//i : 아직 방문X이고, 현재(cur)과 인접점이고, group에 속하면
					queue.offer(i);
					visit[i] = true;
					cnt++;
					sum += peopleCnt[i];
				}
			}
		}

		if (size == cnt) return sum; //그룹안에 애들이 다 인접해있으면 그 합을 리턴
		else return -1; //그룹형성 실패하면 -1 리턴
	}

}
