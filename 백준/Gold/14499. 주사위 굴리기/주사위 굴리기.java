import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = null;
	static StringTokenizer st = null;
	static int [][] map ;
	static int [][] diceMap ; 
	static int N, M, x, y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; 
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(st.nextToken()); //명령 K번
		
		// 맵 입력
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 주사위 맵
		diceMap = new int[4][3];
//		diceMap[0][1] = 2; diceMap[1][0] = 4; diceMap[1][1] = 1; diceMap[1][2] = 3;
//		diceMap[2][1] = 5; diceMap[3][1] = 6;
		
		sb = new StringBuilder();
		// 명령 K번
		st = new StringTokenizer(br.readLine());
		// 1: -> (0, +1) / 2: <- (0, -1) / 3: ^ (-1, 0) / 4: 아래 (+1, 0)
		for (int i = 0 ; i < K ; i++) {
			int direction = Integer.parseInt(st.nextToken());
			
			int nextX = x; int nextY = y;
			switch(direction) {
				case 1:
					nextY = y+1;
					break;
				case 2:
					nextY = y-1;
					break;
				case 3:
					nextX = x-1;
					break;
				case 4:
					nextX = x+1;
					break;
			}
			if (!outOfIndex(nextX, nextY)) continue; // 다음 명령으로
			//x, y 업데이트
			x = nextX; y = nextY;
			
			rollingDice(direction);
			//윗면 숫자 sb에 append
			sb.append(diceMap[1][1]).append("\n");
			//아랫면 판단 diceMap[3][1]
			if (map[nextX][nextY] == 0) map[nextX][nextY] = diceMap[3][1];
			else {
				diceMap[3][1] = map[nextX][nextY];
				map[nextX][nextY] = 0;
			}
		}
		System.out.println(sb);
	}
	
	static boolean outOfIndex(int n, int m) {
		if (n < 0 || m < 0 || n >= N || m >= M ) return false;
		return true;
	}
	static void rollingDice(int direction) {
		int temp = diceMap[1][1];
		switch(direction) {
			case 1: 
				diceMap[1][1] = diceMap[1][0];
				diceMap[1][0] = diceMap[3][1];
				diceMap[3][1] = diceMap[1][2];
				diceMap[1][2] = temp;
				break;
			case 2:
				diceMap[1][1] = diceMap[1][2];
				diceMap[1][2] = diceMap[3][1];
				diceMap[3][1] = diceMap[1][0];
				diceMap[1][0] = temp;
				break;
			case 3:
				diceMap[1][1] = diceMap[2][1];
				diceMap[2][1] = diceMap[3][1];
				diceMap[3][1] = diceMap[0][1];
				diceMap[0][1] = temp;
				break;
			case 4:
				diceMap[1][1] = diceMap[0][1];
				diceMap[0][1] = diceMap[3][1];
				diceMap[3][1] = diceMap[2][1];
				diceMap[2][1] = temp;
				break;
		}
	}

}
