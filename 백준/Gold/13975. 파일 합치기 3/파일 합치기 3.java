import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//백준 13975 _ 파일 합치기3
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // ㅌ스트 개수 
		
		StringBuilder sb = new StringBuilder();
		for (int t = 0 ; t < T ; t++) {
			int N = Integer.parseInt(br.readLine()); // 파일 개수 : 3이상 1,000,000이하 
			PriorityQueue<Long> pq = new PriorityQueue<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < N ; i++) {
				pq.offer((long) Integer.parseInt(st.nextToken())); // 10,000 미만 
			}
			
			long total = 0;
			while(pq.size() > 1) { // pq에 하나 남았을 때 빠져나옴 
				long minCost1 = pq.poll();
				long minCost2 = pq.poll();
				
				long cost = minCost1 + minCost2;
				total += cost;
				
				pq.offer(cost);
			}
			sb.append(total).append("\n");
		}
		System.out.println(sb);

	}
}
