import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준 1655 _ 소수의 연속합
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[] arr = new boolean[N+1] ; //2부터 N까지 소수인애들은 false
		// (1 ≤ N ≤ 4,000,000) n^2 = 16,000,000,000,000
		
		// true : 소수아님 / false : 소수임
        arr[1] = true; //1은 소수아님
		for (int i = 2 ; i*i <= N ; i++) {
			if (arr[i] ) continue; //이미 소수아닌걸로 판정남
			for (int j = i*i ; j <= N ; j+=i) {
				arr[j] = true;
			}
		}
		
		ArrayList<Integer> primeList = new ArrayList<>();
		for (int i = 2 ; i < N ; i++) {
			if (!arr[i])  primeList.add(i);
		}
		
		int cnt = 0; 
		if (!arr[N]) cnt = 1; //자기자신 소수 
		
		int left = 0; int right = 1; //인덱스임
		int primeSize = primeList.size();
		int sum = 0;
		if (primeSize > 1) sum = primeList.get(left) + primeList.get(right); //초기화 
		
		while(left < right && right < primeSize) {
			if (sum < N) { //키우기
				right++;
				if (right == primeSize) break; //끝도달
				sum+=primeList.get(right);
			} else if (sum > N) { //줄이기
				sum-=primeList.get(left);
				left++;
			} else { // (sum==N)
				cnt++;
				right++;
				if (right == primeSize) break; //끝도달
				sum+=primeList.get(right);
			}
			
		}
		System.out.println(cnt);
	}
}
