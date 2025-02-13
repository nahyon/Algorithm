// 백준 2230 _ 수 고르기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 1이상 10만 이하
		int M = Integer.parseInt(st.nextToken()); //차이가 m이상이면서 가장 작은 차이. (0이상 20억이하)
		
		int[] arr = new int[N];
		for (int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(br.readLine()); // -10억이상 10억이하 => 최대 차이 20억 (int형가능)
		}
		
		int minDiff = Integer.MAX_VALUE;
		

		Arrays.sort(arr);
		// 투 포인터 (같이 시작)
		int left = 0; int right = 0 ;
		// right++;하면 차이 늘림 / left++; 하면 차이 줄임
		while((left<=right) && right<N ) {
			int diff = arr[right] - arr[left];
			
			if (diff < M ) {
				right++;
				continue;
			}
			
			minDiff = Math.min(minDiff, diff);
			left++;		
			
		}
		
		System.out.println(minDiff);

	}
}
