import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 15666 _ N과 M (12)
// N개의 자연수 중 M개를 고른 수열
// 고른 수열의 순서 : 비내림차순으로 고정 -> 순서 의미 X  => startIdx 파라미터 사용
// 같은 수 여러 번 가능 = 중복 가능 O
// 출력은 사전 순으로 증가하도록 -> arr배열 정렬
public class Main {
	static StringBuilder sb = null;
	static int N, M;
	static int[] arr, res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 정렬

		sb = new StringBuilder();

		res = new int[M];
		dfs(0, 0);
		System.out.println(sb);
	}
	
	static int before;

	static void dfs(int depth, int startIdx) {
		// 종료조건
		if (depth == M) {
			for (int r : res) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}

		before = -1; // *****

		for (int idx = startIdx; idx < N; idx++) {

			if (arr[idx] == before)
				continue;

			res[depth] = arr[idx];
			dfs(depth + 1, idx); //중복가능이니 idx. (idx+1 xx)

			before = arr[idx];
		}

	}

}
