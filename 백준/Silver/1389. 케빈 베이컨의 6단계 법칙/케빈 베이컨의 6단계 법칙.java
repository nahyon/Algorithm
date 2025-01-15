import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1389 _ 케빈 베이컨의 6단계 법칙
public class Main {
	static int INF = 999_999_999;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 유저의 수 
		int M = Integer.parseInt(st.nextToken()); // 친구 관계의 수
		
		int[][] matrix = new int[N+1][N+1];
		// 그래프 초기화
		for (int i = 1; i<=N; i++) {
			Arrays.fill(matrix[i], INF); //간선없음으로 초기화
			matrix[i][i] = 0; //자기자신
		}
		
		for (int i = 0 ; i < M ; i++ ) {
			st = new StringTokenizer(br.readLine());
			int idx1 = Integer.parseInt(st.nextToken()); 
			int idx2 = Integer.parseInt(st.nextToken()); 
			
			// 무방향이니까 둘 다 추가
			matrix[idx1][idx2] = matrix[idx2][idx1] = 1; // 이어져있음을 의미
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i<=N; i++) {
				for (int j= 1; j <= N; j++) {
					if (matrix[i][j] > matrix[i][k] + matrix[k][j])
						matrix[i][j] = matrix[i][k] + matrix[k][j];
				}
			}
		}
		
		int minSum = Integer.MAX_VALUE;
		int minIdx = 0;
		for (int i = 1 ; i <= N; i++) {
			int tempSum = 0;
			for (int n : matrix[i]) tempSum+=n;
			if (minSum > tempSum) {
				minSum = tempSum;
				minIdx = i;
			}
		}
		System.out.println(minIdx);

	}

}
