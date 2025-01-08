import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준 15657 _ N과 M(8)
//nHm 중복허용O , 순서 의미 X(비내림차순으로 확정.정해져있음)
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
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 증가하는 순으로 출력해야하므로 배열 정렬
		Arrays.sort(arr);
		
		sb = new StringBuilder();
		res = new int[M];
		comb(0, 0);
		System.out.println(sb);			
	}
	
	static void comb(int depth, int startIdx) {
		//종료조건
		if (depth == M) {
			for (int r : res) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// res배열의 [depth] 뽑기 & 그다음 [depth+1], ... 재귀호출
		for (int i = startIdx ; i < N ; i++) {
			res[depth] = arr[i]; // i임 (startIdx아님!)
			comb(depth+1 , i); // i + 1 임 !! 중복허용이니까 i도 또 나올 수 있음
		}
		
	}
}
