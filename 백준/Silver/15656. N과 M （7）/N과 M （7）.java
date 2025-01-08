import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준 15656 _ N과 M(7)
//nㅠm 중복허용O , 순서 의미 O
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
		pperm(0);
		System.out.println(sb);			
	}
	
	static void pperm(int depth) {
		if (depth == M) {
			for (int r : res) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}
		// N개의 숫자 전부 돌아가며 선택하기
		for (int i = 0 ; i < N; i++) {
			res[depth] = arr[i]; //수 arr[i] 뽑음
			pperm(depth+1);
		}
	}
}
