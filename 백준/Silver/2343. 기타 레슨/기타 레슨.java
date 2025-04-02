import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2344 _ 기타레슨 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] videos = new int[N];
		int left = 0; // 비디오 가장 최소ㄴㄴ 최대  길이 저장
        int right = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i++) {
			videos[i] = Integer.parseInt(st.nextToken());
			left = Math.max(left, videos[i]); // 보다 작으면 잘리는 비디오있으니까 
		    right += videos[i];
        }
		
		int minLength = Integer.MAX_VALUE; // 블루레이 길이(정답)

		// int right = 1000000000 ;// 최대 10만*1만 = 10억
		while(left<=right) {
			int targetLength = (left+right)/2; // 이번에 설정하는 길이
			
			
			int cnt = 0; 
			int temp = 0;
			for (int len : videos) { // 비디오 길이 하나씩 보기

				if (temp + len > targetLength ) {
					cnt++; temp = len;
				} else if (temp + len == targetLength) {
					cnt++; temp = 0;
				} else temp += len;
				if (cnt > M) break; // 설정길이(targetLength)늘려야함
			}
			//** for문 끝까지 다 돌고도 temp가 남아있다면
			if (cnt <= M  && temp > 0) cnt++; 
						
			if (cnt > M) { // targetLength 늘려야함 -> left 오른쪽으로 
				left = targetLength + 1;
				// continue; // while문 다시 돌러 
			} else { // targetLength 줄여야함 -> right 왼쪽으로 
				minLength = Math.min(minLength, targetLength);
				right = targetLength - 1;
			}
		}	

		System.out.println(minLength);

	}
}
