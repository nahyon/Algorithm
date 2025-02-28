import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2531 _ 회전 초밥
// 투포인터
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 접시 수 
		int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수 (종류 :1이상 d이하 숫자)
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수  => 최대한 다양한 종류로 먹기
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호 (초밥 종류 번호) (벨트에 없으면 만들어서제공 )
		
		boolean flag = false; // 쿠폰에 적힌 번호가 벨트 위에 있는지 여부
		int[] arr = new int[N+k-1];
		for (int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i] == c) flag = true;
		}
		for (int i = 0 ; i < k-1 ; i++) { // 원형이니까 뒤에 (k-1)개 더 추가 
			arr[N+i] = arr[i];
		}
		
		// k그릇 안에 몇 개 
		int[] foods = new int[d+1]; 
		int cnt = 0; // k그릇 살펴본 후 , 최대 가짓수
		// 정답 최대 = k 가지 + 쿠폰 1 = (k+1)
		
		// 변수 초기화 
		int left = 0 ; int right = left+k-1; // 인덱스
		for (int i = left ; i <= right ; i++) {
			if (foods[arr[i]] == 0) cnt++;
			foods[arr[i]]++;
		}
//		if (!foods[c]) cnt++; // 쿠폰 안썼으면 
		
		if (foods[c]==0 && cnt == k) { // 쿠폰 사용 -> 이미 최대 (함수 종료) 
			System.out.println(k+1);
			return; 
		}
		
		int maxCnt = 0;
		while (right < N+k-2) { // right == N+k-1로 인덱스 최대 달성 시 종료 
			
			foods[arr[left]]--;
			if (foods[arr[left]] == 0) cnt--;
			left++; right++;
			foods[arr[right]]++;
			if (foods[arr[right]] == 1) cnt++; //새로

			if (foods[c]==0 && cnt == k) { // 쿠폰 사용 -> 이미 최대 (함수 종료) 
				maxCnt = cnt+1;
				break; 
			}
			
			if (foods[c] == 0) maxCnt = Math.max(cnt+1, maxCnt); //cnt값은 유지되어야함 다음을 위해..그래서 cnt++안함
			else maxCnt = Math.max(cnt, maxCnt);

		}
		System.out.println(maxCnt);
		
	}

}
