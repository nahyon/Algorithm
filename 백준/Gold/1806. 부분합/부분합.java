import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1806 _ 부분합
// 투포인터
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int S = Integer.parseInt(st.nextToken()); 
		
		int[] arr = new int[N+1];		//게산 편의위해 인덱스0안씀
		int[] sumArr = new int[N+1];	//게산 편의위해 인덱스0안씀
		st = new StringTokenizer(br.readLine());
		for (int i = 1 ; i <= N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sumArr[i] = sumArr[i-1] + arr[i];
		}
		

		int minLen = 100000;
		int stIdx = 1;
		int enIdx = 1; 
		int sum = arr[1];
		
		while (stIdx >= 1  && enIdx <= N) {
			if (sum >= S) {
				minLen = Math.min(minLen, enIdx-stIdx+1);
				// 하나 더 빼기 (좌측+1)
				stIdx++;
			} else {
				// 하나 더 추가 (우측+1)
				enIdx++;
			}
			if (stIdx == 0 || enIdx > N) break;
			sum = sumArr[enIdx] - sumArr[stIdx-1];
		}
		
		
		
		if (minLen == 100000) System.out.println(0);
		else System.out.println(minLen);
	}

}
