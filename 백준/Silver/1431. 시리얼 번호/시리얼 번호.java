import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 1431 _ 시리얼 번호
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 기타 개수
		
		String[] arr = new String[N];
		for (int i = 0 ; i < N ;i++) {
			arr[i] = br.readLine();
		}

		
		Arrays.sort(arr, (a, b) ->  {
			if (a.length() != b.length()) return a.length() - b.length(); // 오름차순
			
			int sumA = digitSum(a);
			int sumB = digitSum(b);
			
			if (sumA != sumB) return sumA - sumB ; // 오름차순
			
			return a.compareTo(b);
		});
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < N ; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb);
	}
	
	static int digitSum(String str) {
		int sum = 0;
		for (int i = 0;  i < str.length(); i++) {
			char c = str.charAt(i);
			
			if (c >= '0' && c <= '9' ) { // 숫자인 경우
				sum += (c-'0');
			}
		}
		return sum;
	}
}
