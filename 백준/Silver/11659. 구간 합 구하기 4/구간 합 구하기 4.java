import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11659 _ 구간 합 구하기 4
// 누적합 배열 사용
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1]; //인덱스0은 안씀
		int[] sumArr = new int[N+1]; //인덱스0은 안씀
		
		arr[1] = Integer.parseInt(st.nextToken());
		sumArr[1] = arr[1];
		for (int i = 2 ; i <= N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sumArr[i] = sumArr[i-1] + arr[i];
		}
		
		StringBuilder sb = new StringBuilder() ;
		for (int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(sumArr[end] - sumArr[start-1]).append("\n");
		}
		System.out.println(sb);
	}
}
