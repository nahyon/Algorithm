import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2869 _ 달팽이는 올라가고 싶다
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A, B, V; 
		A = Integer.parseInt(st.nextToken()); //낮에 올라갈 수 있음 +
		B = Integer.parseInt(st.nextToken()); //밤에 미끄러짐 -
		V = Integer.parseInt(st.nextToken()); //나무막대
		
		int diff = A-B;
		// 처음 초기화
		int day = 1;
		if (V > A) {
			int plus = ((V-A)<diff) ? 1 : (V-A) / diff; 
			if (plus * diff < (V-A)) plus+=1 ;
			day += plus;
		}
		System.out.println(day);
		
	}
}