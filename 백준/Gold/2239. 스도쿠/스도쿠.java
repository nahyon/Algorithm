import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준 2239 _ 스도쿠
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
			char[] arr = br.readLine().toCharArray();
			for (int j = 0 ; j < 9 ; j++) {
				board[i][j] = arr[j] - '0';
				if (board[i][j] == 0) blankList.add(new Node(i, j));
			}
		}
		cnt = blankList.size();	// 채워 넣어야하는 빈 칸 개수	
		
		dfs(0);
	}
	

	static boolean dfs(int depth) {
		if (depth == cnt) {
            printBoard();
            return true; // 정답을 찾으면 true 반환
		}
		
		Node blank = blankList.get(depth);
	    int r = blank.r;
	    int c = blank.c;
		for (int num = 1; num <= 9; num++) {
			
			if (!isSafe(r, c, num)) continue;
			
			// 숫자 num이 유효한 경우에만 진행
			board[r][c] = num;
			 if (dfs(depth + 1)) return true; // 다음 빈칸 탐색에서 정답을 찾으면 true 반환 
			board[r][c] = 0; //원복 ** 
		}
		return false; // 모든 숫자를 시도했지만 정답을 찾지 못한 경우 false 반환
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
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}