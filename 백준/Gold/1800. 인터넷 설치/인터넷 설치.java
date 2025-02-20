import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int INF = 999_999_999;
	static int N, P, K;
	static class Node implements Comparable<Node> {
		int num, cost ;
		int cnt ; // pq에서 사용 . 무료 사용 개수
		public Node (int num, int cost) {
			this.num = num; this.cost = cost;
		}
		public Node (int num, int cost, int cnt) {
			this.num = num; this.cost = cost; this.cnt = cnt;
		}
		@Override
		public int compareTo(Node o) {
			return (this.cost - o.cost);
		}
	}
	static ArrayList<Node>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수 (1이상 1000이하)
		P = Integer.parseInt(st.nextToken()); // 케이블선 개수 (1이상 1만이하)
		K = Integer.parseInt(st.nextToken()); //공짜 케이블 수 (0이상 N미만)
		
		
		graph = new ArrayList[N+1];
		for (int i = 1 ; i <= N ; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int maxC = 0;
		for (int i = 0 ; i < P ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));

			maxC = Math.max(maxC, c);
		}


		
		int left = 0;
		int right = maxC;
		
		int minCost = INF;
		while(left <= right ) {
			int mid = (left + right) / 2; //금액 설정 //인덱스가 아니라 그값으로 하기!
			
			if (!dijkstra(mid)) { //실패 ->mid 늘리기
				left = mid+1;
			} else {
				right = mid-1;
				minCost = Math.min(minCost, mid );
			}
		}
		
		System.out.println(minCost == INF ? -1 : minCost);
				
	}
		
	static boolean dijkstra(int maxCost) { //최대 maxCost의 금액으로 N번까지 갈 수 있나 
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int[][] dist = new int[N+1][K+1]; 
		// 출발지 dist[1] = 0
		for (int i = 2 ; i <= N ; i++) {
			Arrays.fill(dist[i], INF);
		}
		queue.offer(new Node(1, 0, 0));

		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			// 종료 조건 ; 
			if (cur.num == N) return true; //가능
						
			if (cur.cost > dist[cur.num][cur.cnt]) continue;
			
			// 이웃
			for (Node next : graph[cur.num]) {
//				int newCost = cur.cost + next.cost;
				int newCost = Math.max(cur.cost, next.cost);
				// 무료 사용
				if (cur.cnt < K && next.cost > maxCost  ) { 
					if (dist[next.num][cur.cnt+1] > cur.cost) { // -> cur.cost를 유지할 수 있도록!
						dist[next.num][cur.cnt+1] = cur.cost;
						queue.offer(new Node(next.num, dist[next.num][cur.cnt+1], cur.cnt+1));
					}
					
				}
				// 무료 사용 x ; 비용 지불 
				if (newCost <= maxCost ) { 
					if (dist[next.num][cur.cnt] > newCost) { // -> newCost로 유지할 수 있도록!
						dist[next.num][cur.cnt] = newCost;
						queue.offer(new Node(next.num, dist[next.num][cur.cnt], cur.cnt));
					}
				}
			}
		}
		return false;
	}

}
