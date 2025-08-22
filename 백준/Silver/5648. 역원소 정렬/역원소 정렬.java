import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		Long[] arr = new Long[N];
		
		int idx = 0;
		
		while (true) {
			if (st.hasMoreTokens()) {
				StringBuilder str = new StringBuilder(st.nextToken());
				Long num = Long.parseLong(str.reverse().toString());
				arr[idx++] = num;
				if (idx == N) break;
			} else {
				st = new StringTokenizer(br.readLine());
			}
		}
		Arrays.sort(arr);//오름차순 정렬
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < N ; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb);

	}
}
