import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 2580 _ 스도쿠
// 백트래킹
public class Main {
	static StringBuilder sb = null;
	static int[][] board;
	static ArrayList<Node> blankList;
	static int cnt;
	
	static class Node {
		int r, c;
		public Node(int r, int c) {
			this.r = r; this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		board = new int[9][9];
		blankList = new ArrayList<>();
		for (int i = 0 ; i < 9 ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < 9 ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 0) blankList.add(new Node(i, j));
			}
		}
		cnt = blankList.size();	
/*		for (int i = 0 ; i < cnt; i++) {
			Node blank = blankList.get(i);
			for (int num = 1; num <= 9; num++) {
				board[blank.r][blank.c] = num; // 보드판에 수 채워넣음
				if (!isOK(blank.r, blank.c)) continue;
				break;
//				if (isOK(blank.r, blank.c)) {
//					board[blank.r][blank.c] = i; 
//					dfs(depth+1);
//				}
			}
		}*/
		
		dfs(0);
		
//		printBoard();
	}
	
	
	static void dfs(int depth) {
		if (depth == cnt) {
//			printBoard();
//			return;
	        printBoard();
	        System.exit(0); // 정답을 찾은 경우 프로그램 종료
		}
		
		Node blank = blankList.get(depth);
	    int r = blank.r;
	    int c = blank.c;
		for (int i = 1; i <= 9; i++) {
//			board[blank.r][blank.c] = i; // 보드판에 수 채워넣음
//			if (!isOK(blank.r, blank.c)) continue;
//			dfs(depth+1);
	        if (isSafe(r, c, i)) { // 숫자 i가 유효한 경우에만 진행
	            board[r][c] = i; // 숫자 배치
	            dfs(depth + 1);  // 다음 빈칸 탐색
	            board[r][c] = 0; // 백트래킹을 위해 복구
	        }
		}
		
	}

/*	0.0 0.1 0.2 | 0.3 0.4 0.5 | 0.6 0.7 0.8
	1
	2
	
	3.0 3.1 3.2 | 3.3 3.4 3.5 | 3.6 3.7 3.8
	4
	5
	
	6.0 6.1 6.2 | 6.3
	7
	8
	*/
	
	static boolean isOK(int r, int c) {
		boolean[] numbers = new boolean[10]; //[1] ~ [9]  사용
		// 세로 검사 (c열)
		for (int i = 0 ; i < 9 ; i++ ) {
			if (board[i][c] == 0) continue; //아직 안 채워진 부분
			if (numbers[board[i][c]]) return false;
			numbers[board[i][c]] = true; //방문처리
		}		
		// 가로 검사 (r행)
		numbers = new boolean[10];  // 다시 초기화 
		for (int j = 0 ; j < 9 ; j++ ) {
			if (board[r][j] == 0) continue;
			if (numbers[board[r][j]]) return false;
			numbers[board[r][j]] = true; //방문처리
		}		
		// 3*3 검사
		numbers = new boolean[10];  // 다시 초기화 
		for (int i = (r/3)*3 ; i < (r/3)*3+3; i++) {
			for (int j = (c/3)*3 ; j < (c/3)*3+3 ; j++) {
				if (board[i][j] == 0) continue;
				if (numbers[board[i][j]]) return false;
				numbers[board[i][j]] = true; //방문처리
			}
		}
		return true;
	}
	// 숫자가 r, c 위치에 배치될 수 있는지 확인하는 함수
	static boolean isSafe(int r, int c, int num) {
	    // 가로 확인
	    for (int i = 0; i < 9; i++) {
	        if (board[r][i] == num) return false;
	    }
	    // 세로 확인
	    for (int i = 0; i < 9; i++) {
	        if (board[i][c] == num) return false;
	    }
	    // 3x3 확인
	    int startRow = (r / 3) * 3;
	    int startCol = (c / 3) * 3;
	    for (int i = startRow; i < startRow + 3; i++) {
	        for (int j = startCol; j < startCol + 3; j++) {
	            if (board[i][j] == num) return false;
	        }
	    }
	    return true;
	}
	
	
	static void printBoard() {
		for (int i = 0 ; i < 9; i++) {
			for (int j = 0 ; j < 9; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
