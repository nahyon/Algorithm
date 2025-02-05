import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1654 _ 랜선 자르기
// 파라매트릭 서치
public class Main {
	static int K, N;
	static int[] arr ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken()); //가진 랜선 개수
		N = Integer.parseInt(st.nextToken()); //필요 랜선 개수. N개 이상의 개수가 나오도록하기
		
		arr = new int[K];
		for (int i = 0 ; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// ans : 최대 랜선 길이
		long ans = 0; //최대를 구해야하니 최소로 초기화
		
		// 최소의 length (ans) 를 찾아서 ...
		long left = 1; long right = 0x7fffffff; //최댓값 설정??
		while (left <= right) {
			long mid = (left + right) / 2; // length.. ans의 후보군
			
			if (calculateCnt(mid)) { // 랜선 개수가 N개 이상이다.
				// length를 늘려도됨. (최대를 찾아서..)
				ans = Math.max(ans,  mid);
				left = mid + 1;
			} else right = mid-1;
		}
		
		System.out.println(ans);
		
	}
	
	// 기준 길이가 length일 때 랜선의 개수
	// N개 이상이면 true , 아니면 false 반환하기
	static boolean calculateCnt(long length) {
		long cnt = 0; 
		for (int i = 0 ; i < K ;i++) {
			cnt += arr[i] / length;
		}
		return cnt >= N; 	
	}

}
