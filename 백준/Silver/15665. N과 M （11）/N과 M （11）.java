import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 15665 _ N과 M (11)
// N개의 자연수 중 M개를 고른 수열
// 같은 수 여러 번 골라도 된다 = 중복 허용O -> isSelected 필요없음
// 순서 의미 O (1 7) (7 1) 다름 -> for문 idx 0부터 전부
// 출력은 사전 순으로 증가하는 순서 -> arr배열 정렬
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
		dfs(0);
		System.out.println(sb);

	}

	static int before;

	static void dfs(int depth) {
		// 종료조건
		if (depth == M) {
			for (int r : res) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}

		before = -1; // *****

		for (int idx = 0; idx < N; idx++) {

			if (arr[idx] == before)
				continue;

			res[depth] = arr[idx];
			dfs(depth + 1);
			before = arr[idx];
		}

	}

}
