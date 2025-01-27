import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 2018 _ 연속된 자연수의 합 구하기 (투포인터)
public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		for (int i = 0 ; i < N; i++) {
			nums[i] = i+1;
		}
		
		int cnt = 1;  //자기자신인거 cnt 먼저 해둠
		
		int stIdx = 0; 
		int endIdx = 1;

		int sum = 1; 
		while (endIdx != N) {
			if (sum ==N) {
				cnt++; endIdx++; sum = sum+endIdx;
			} else if(sum>N) {
				sum = sum-stIdx; //기존거 뺴고
				stIdx++; //start 인덱스 오른쪽으로 한칸
			} else {
				endIdx++; sum = sum+endIdx;
			}
		}
		

		
		System.out.println(cnt);
		
		
	}
	
}
