import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11403 _ 경로찾기
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
//		int[][] map = new int[N][N];
		boolean[][] matrix = new boolean[N][N];
		for (int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
				matrix[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
			}
		}
		
		for (int k = 0 ; k < N; k++) {
			for (int i = 0 ; i < N ; i++) {
				for (int j = 0 ; j < N; j++) {
					if (matrix[i][k] && matrix[k][j]) matrix[i][j] = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < N ; j++) {
				if (matrix[i][j]) sb.append("1 ");
				else sb.append("0 ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
