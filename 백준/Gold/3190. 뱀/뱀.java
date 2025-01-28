import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 3190 _ 뱀
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
		
		int[] snakeHead = {1, 1}; //r, c
		int[] snakeTail = {1, 1}; //r, c
		for (int i = 0 ; i < L ;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			ld = st.nextToken().charAt(0);

			while (time < x) { // 방향전환직전까지 뱀 이동
				time++;
				
				// 뱀 한 칸 움직이기
				if (!moveSnake(queue, snakeHead, snakeTail)) {
					flag = true; //게임 진짜 끝
					break;
				}
			}
			if (flag) break; // 게임끝
			if (time == x) {
				changeDirection(ld);
			}
		}
		// 마지막 방향 전환 이후
		if (!flag) { // 게임이 아직 안끝났다면 끝날 때까지 계속 이동
			while(true) {
				time++;
				// 뱀 한 칸 움직이기
				if (!moveSnake(queue, snakeHead, snakeTail))  {
					break;
				}
			}
		}

		System.out.println(time);
	}
	
	static boolean moveSnake(Queue<int[]> snake, int[] snakeHead, int[] snakeTail) {
		// 다음 칸 갱신
		int nextR = snakeHead[0] + direction[dirIdx][0];
		int nextC = snakeHead[1] + direction[dirIdx][1];
		
		// check false면 게임 끝
		if (!check(nextR, nextC)) {
			return false;
		}
		
		// 다음 칸 - 사과 X
		// 꼬리 비우고 머리 한 칸 전진
		if (map[nextR][nextC] != 9) {
			// 뱀 머리 한 칸 전진
			map[nextR][nextC] = 1; // 0=>1 (뱀 머리)
			snake.offer(new int[]{nextR, nextC}); // 스네이크 큐 추가
			
			// 꼬리 비우기
			map[snakeTail[0]][snakeTail[1]] = 0;
			snake.poll(); // 스네이크 큐에서도 꼬리 빼기
			// 꼬리 값 갱신
			snakeTail[0] = snake.peek()[0];
			snakeTail[1] = snake.peek()[1];
		} 
		
		// 다음 칸 - 사과 O
		// 사과 없어지고 뱀 머리가 한 칸 전진 
		else {
			map[nextR][nextC] = 1; // 9->1 (사과 먹, 뱀 머리)
			snake.offer(new int[]{nextR, nextC}); // 스네이크 큐 추가
		}
		
		// 현재 머리 위치 갱신
		snakeHead[0] = nextR;
		snakeHead[1] = nextC;
		
		return true;
		
	}
	
	// 게임 종료 검사
	static boolean check(int nextR, int nextC) {
//		if (nextR <= 0 || nextC <= 0 || nextR > N || nextC > N
//				|| map[nextR][nextC] == 1) return false; // 게임종료 
//		return true;
		return nextR > 0 && nextC > 0 && nextR <= N && nextC <= N && map[nextR][nextC] != 1;
	}
	
	
	static void changeDirection(char c) {
	    if (c == 'D') { // 우회전
	        dirIdx = (dirIdx + 1) % 4;
	    } else { // 좌회전
	        dirIdx = (dirIdx + 3) % 4; // (dirIdx - 1 + 4) % 4 
	    }
	}

}
