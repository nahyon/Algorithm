import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] map;
	static int time = 0;
	static int[][] direction = {{0, +1}, {+1, 0}, {0, -1}, {-1, 0}};
	static int dirIdx = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 보드크기
		K = Integer.parseInt(br.readLine()); // 사과개수
		
		map = new int[N+1][N+1];
		StringTokenizer st = null;
		int r, c;
		for (int i = 0 ; i < K; i++) { //사과 k개 위치 (9)
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map[r][c] = 9;
		}
		map[1][1] = 1; //뱀 시작
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{1, 1});
		
		int time = 0;
		int L = Integer.parseInt(br.readLine()); // 방향 변환 횟수
		int x; char ld;
		boolean flag = false;
		
		int nowR = 1, nowC = 1;
		int nextR, nextC;
		int tailR = 1, tailC = 1;
		for (int i = 0 ; i < L ;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			ld = st.nextToken().charAt(0);

			while (time < x) {
				time++;
				// 다음 칸 갱신
				nextR = nowR + direction[dirIdx][0];
				nextC = nowC + direction[dirIdx][1];
				
				// check false면 break. 
				if (!check(nextR, nextC)) {
					flag = true;
					break;
				}
				
				// 다음 칸 - 사과 X
				if (map[nextR][nextC] != 9) {
					// 뱀 머리 한 칸 전진
					map[nextR][nextC] = 1; // 0=>1
					queue.offer(new int[]{nextR, nextC});
					
					// 꼬리 비우기
					map[tailR][tailC] = 0;
					queue.poll(); // 스네이크 큐에서도 꼬리 빼기
					// 꼬리 값 갱신
					tailR = queue.peek()[0];
					tailC = queue.peek()[1];
				} 
				// 다음 칸 - 사과 O
				// 사과 없어지고 뱀 머리가 한 칸 전진 
				else {
					map[nextR][nextC] = 1; // 9->1
					queue.offer(new int[]{nextR, nextC});
				}
				
				// 현재 머리 위치 갱신
				nowR = nextR;
				nowC = nextC;
			}
			if (flag) break;
			if (time == x) {
				changeDirection(ld);
			}
		}
		if (!flag) {
			while(true) {
				time++;
				// 다음 칸 갱신
				nextR = nowR + direction[dirIdx][0];
				nextC = nowC + direction[dirIdx][1];
				
				// check false면 break. 
				if (!check(nextR, nextC)) {
					break;
				}
				
				// 다음 칸 - 사과 X
				if (map[nextR][nextC] != 9) {
					// 뱀 머리 한 칸 전진
					map[nextR][nextC] = 1; // 0=>1
					queue.offer(new int[]{nextR, nextC});
					
					// 꼬리 비우기
					map[tailR][tailC] = 0;
					queue.poll(); // 스네이크 큐에서도 꼬리 빼기
					// 꼬리 값 갱신
					tailR = queue.peek()[0];
					tailC = queue.peek()[1];
				} 
				// 다음 칸 - 사과 O
				// 사과 없어지고 뱀 머리가 한 칸 전진 
				else {
					map[nextR][nextC] = 1; // 9->1
					queue.offer(new int[]{nextR, nextC});
				}
				
				// 현재 머리 위치 갱신
				nowR = nextR;
				nowC = nextC;
			}
		}



		System.out.println(time);
	}
	
	static boolean check(int nextR, int nextC) {
		if (nextR <= 0 || nextC <= 0 || nextR > N || nextC > N
				|| map[nextR][nextC] == 1) return false; // 게임종료 
		return true;
	}
	
	
	static void changeDirection(char c) {
		if (c == 'D') { //우
			dirIdx++;
		} else { // 좌
			dirIdx--;
		}
		if (dirIdx < 0) dirIdx = 3;
		else if (dirIdx == 4) dirIdx = 0;
	}

}
