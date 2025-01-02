import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 9095 _ 1, 2, 3 더하기
public class Main {
	static int[] facDP = new int[11] ; //n<11
	static int a, b, c; //1개수, 2개수, 3개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 팩토리얼 DP배열 만들기
		facDP[0] = 1; facDP[1] = 1;
		for (int i = 2; i < 11; i++ ) {
			makeDP(i);
		}
		
		for (int i = 0; i < T ; i++) { 
			int num = Integer.parseInt(br.readLine());
			
			int ans = 0 ; 
			for (c = num/3 ; c >= 0 ; c--) {
				for (b = (num-3*c)/2 ; b >= 0 ; b--) {
					a = num - 3*c - 2*b;
					// (a+b+c)! / (a! * b! * c!)
					ans += (facDP[a+b+c] / (facDP[a]*facDP[b]*facDP[c]) );
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void makeDP(int n) {
		facDP[n] = n * facDP[n-1];
	}
}
