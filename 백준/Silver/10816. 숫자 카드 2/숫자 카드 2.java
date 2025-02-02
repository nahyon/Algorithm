import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
// 백준 10816 _ 숫자카드2
// 이분탐색?
// HashMap?
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Integer, Integer> cardCntMap = new HashMap<>();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			int n =  Integer.parseInt(st.nextToken());
			cardCntMap.put(n, cardCntMap.getOrDefault(n, 0)+1);
		}
		int M = Integer.parseInt(br.readLine()); 
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < M; i++) {
			int searchNum = Integer.parseInt(st.nextToken());
			int cnt = cardCntMap.get(searchNum) == null ? 0 : cardCntMap.get(searchNum);
			sb.append(cnt).append(" ");
		}
		System.out.println(sb);


	}

}
