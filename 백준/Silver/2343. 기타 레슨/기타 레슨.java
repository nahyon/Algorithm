import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2343_ 기타레슨
// 파라메트릭 탐색
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //강의 수
		int M = Integer.parseInt(st.nextToken()); // M개
		
		// M개안에 최소 length로 강의N개 다 넣기
		
		int[] videos = new int[N];
		int total = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ;i++) {
			videos[i] = Integer.parseInt(st.nextToken());
			total += videos[i];
		}
		int lo = 0; int hi = total; //0, N-1아니고 최솟값 1이랑 total임 헷갈리지말기
		int ans = total; //최소를 구해야하니까 최대값으로 설정
		while (lo <= hi) {
			int len = (lo+hi)/2; // mid값을 len으로 설정하고
			
			int cnt = 0; 
			int sum = 0;
			int rest = total;
			boolean flag = false;
			for(int i = 0 ; i < N ; i++) { // N개의 비디오에 대해 계산
				if (videos[i] > len) { //아에 못 담는 경우가 있으면 안됨 -> len 크기 키우기
					flag = true; 
					break;
				}
				
				sum += videos[i];
				if (sum > len) {
					rest -= (sum-videos[i]);
					cnt++;
					
					sum = videos[i]; 
				} else if (sum == len) {
					rest -= sum;
					cnt++;
					
					sum = 0;
				}
				
				
				if (rest <= len) { // 남은 것들의 총합이 블루레이1개에 들어간다면 cnt+1하고 break
					cnt++;
					break;
				}
//				if (cnt > M) break;
			}
		
			
			if ((cnt > M) || flag) { //len크기를 키워야함 -> lo를 뒤로
				lo = len + 1;
			} else { // 조건만족. but 이 중에 가장 최솟값을 구해야함(len최대한 최소로)
				ans = Math.min(ans, len);
				hi = len-1;
			}
		}
		System.out.println(ans);
		
		
	}

}
