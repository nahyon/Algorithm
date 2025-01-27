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
		
		int[] arr = new int[N];		
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int minLen = 100000;
		int stIdx = 0;
		int enIdx = 0; 
		int sum = arr[0];
		
		while (enIdx < N) {
			if (sum >= S) {
				minLen = Math.min(minLen, enIdx-stIdx+1);
				// 하나 더 빼기 (좌측+1)
				sum -= arr[stIdx];
				stIdx++;
			} else {
				// 하나 더 추가 (우측+1)
				enIdx++;
				if (enIdx == N) break;
				sum += arr[enIdx];
			}
		}
		
		if (minLen == 100000) System.out.println(0);
		else System.out.println(minLen);
	}

}
