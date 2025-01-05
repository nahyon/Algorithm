import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 15654 _ N과 M(5)
// nPm 중복허용X , 순서 의미 O
public class Main {
	static StringBuilder sb = null;
	static int N, M; 
	static int[] arr, res;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 증가하는 순으로 출력해야하므로 배열 정렬
		Arrays.sort(arr);
		
		sb = new StringBuilder();
		res = new int[M];
		perm(0);
		System.out.println(sb);			
	}
	static void perm(int depth) {
		// 종료조건 : M개 뽑기 완료
		if (depth == M) {
			for (int r : res) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0 ; i < N; i++) {
			if (isSelected[i]) continue; //중복허용X이므로
			
			isSelected[i] = true; //숫자 i번째거 뽑음
			res[depth] = arr[i];
			perm(depth+1); //다음거 뽑으러
			isSelected[i] = false; //원복
		}
	}
}
