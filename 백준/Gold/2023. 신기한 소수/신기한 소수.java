import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		
		int[] first = {2, 3, 5, 7};
		if (N==1) {
			sb.append(2).append("\n").append(3).append("\n").append(5).append("\n").append(7).append("\n");
		} else {
			for (int i = 0; i < 4; i++) {
				dfs(first[i]);
			}
		}
		
		
		System.out.println(sb);
	}
		
	static void dfs(int num) {
		
		if (String.valueOf(num).length() == N) {
			if (is_prime(num)) sb.append(num).append("\n");
			return;
		}
		
		for (int i = 1 ; i <= 9 ; i+=2) {
			// numbers[i] : 새로 오른쪽에 붙일 한자리
			if (!is_prime(num*10 + i)) continue;
			num *= 10;
			num += i;
			dfs(num);
			num /= 10;
		}
		
	}
	
	static boolean is_prime(int n) {
		if (n <= 1) return false;
		if (n <= 3) return true;
		if (n % 2 == 0) return false;
		for (int i = 2; i <= n - 1; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}
	
}
