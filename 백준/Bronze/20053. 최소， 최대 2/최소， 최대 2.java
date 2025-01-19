import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //테스트 케이스
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0 ; t < T ; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int minValue = n;
			int maxValue = n;
			for (int i = 0 ; i < N-1 ;i++) {
				n = Integer.parseInt(st.nextToken());
				minValue = Math.min(minValue, n);
				maxValue = Math.max(maxValue, n);
			}
			sb.append(minValue).append(" ").append(maxValue).append("\n");
		}
		System.out.println(sb);
	}

}
