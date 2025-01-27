import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2018 _ 수들의 합5
// 투 포인터
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt=0, start, end;
		start = end = 1;

		int sum = 1;
		
		while (end<=N) {
			if (sum < N) {
				end++;		//순서
				sum+=end;	//중요
			} else if (sum > N) {
				sum -= start; //순서
				start++;	  //중요
			} else {
				sum -= start; //순서
				start++;	  //중요
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
