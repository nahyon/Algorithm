import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 백준 10815 _ 숫자 카드
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		HashSet<Integer> cards = new HashSet<>();
		for (int i = 0 ; i < N ;i++) {
			cards.add(Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < M ; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (cards.contains(num)) sb.append(1).append(" ");
			else sb.append(0).append(" ");
		}
		
		System.out.println(sb);
	}

}
