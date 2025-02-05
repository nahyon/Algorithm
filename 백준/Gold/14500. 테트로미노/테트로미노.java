import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14500 _ 테트로미노
public class Main {
	static int[][] map ;
	static boolean[][] isVisited ;
	static int N, M;
	
	static int[] dr = {+1, -1, 0, 0};
	static int[] dc = {0, 0, +1, -1};
	
	static int maxSum;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //세로
		M = Integer.parseInt(st.nextToken()); //가로
		
		map = new int[N][M];
		isVisited = new boolean[N][M];
		
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 완탐 돌리기
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < M ; j++) {
				isVisited[i][j]= true;
				dfs(i, j, 1, map[i][j]); // 첫 칸 선택 
				isVisited[i][j] = false; // 다음 첫칸선택 때 영향가지않게 원복
				checkSpecialShape(i, j);
			}
		}
		System.out.println(maxSum);
		
	}
	
	static void dfs(int r, int c , int depth, int sum) {
		if (depth == 4) { //4칸 고름
			maxSum = Math.max(sum,  maxSum);
			return;
		}
		
		for (int i = 0 ;  i < 4 ; i++) {
			if (!OOB(r+dr[i], c+dc[i])) continue; // 범위 벗어남 -> 다른 칸 선택
			int nextR = r+dr[i]; int nextC = c+dc[i];
			if (isVisited[nextR][nextC]) continue; //방문한 곳이면 안됨
			
			isVisited[nextR][nextC] = true;
			dfs(nextR, nextC, depth+1, sum+map[nextR][nextC]);
			isVisited[nextR][nextC] = false; // 원복	
		}
		
	}
	
    // ㅗ, ㅏ, ㅜ, ㅓ 모양 검사
    static void checkSpecialShape(int r, int c) {
        // 4가지 특수 모양을 직접 검사
        int[][] shapes = {
            {0, 1, 0, -1, 1, 0},  // ㅗ
            {0, 1, 0, -1, -1, 0}, // ㅜ
            {1, 0, -1, 0, 0, 1},  // ㅏ
            {1, 0, -1, 0, 0, -1}  // ㅓ
        };

        for (int i = 0; i < 4; i++) {
            int sum = map[r][c];
            boolean valid = true;
            for (int j = 0; j < 3; j++) {
                int nr = r + shapes[i][j * 2];
                int nc = c + shapes[i][j * 2 + 1];

                if (!OOB(nr, nc)) {
                    valid = false;
                    break;
                }
                sum += map[nr][nc];
            }
            if (valid) maxSum = Math.max(maxSum, sum);
        }
    }
	
	static boolean OOB(int r, int c) {
		if (r<0 || c<0 || r>=N ||c>=M) return false; //벗어남
		return true; //가능
	}
}
