import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 12763 _지각하면 안 돼
public class Main {
	static int N, T, M, L;
	static class Node implements Comparable<Node>{
		int num, time, cost;
		public Node(int num, int time, int cost) {
			this.num = num;
			this.time = time;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			if (this.time == o.time) {
				return this.cost - o.cost; //오름차순
			}
			return (this.time - o.time); //오름차순
		}
	}
	static ArrayList<Node>[] graph;
	static int INF = 999_999_999;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 건물 개수 2<=N<=100
		
		graph = new ArrayList[N+1];
		for (int i = 1 ; i <= N ; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken()); // 시간 1<=T<=10,000
		M = Integer.parseInt(st.nextToken()); // 현재 가진 돈 0<=M<=1
		
		L = Integer.parseInt(br.readLine()); // 건물 사이 길 개수 1<=L <= 10,000
		// (건물 번호, 건물 번호, 이동시간, 택시비) // 이동시간, 택시비 <10,000 자연수
		for (int i = 0 ; i < L ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, t, c));
			graph[b].add(new Node(a, t, c));
		}
		
		// T분 안에 1번 건물에서 N번 건물까지 M이하의 돈으로 갈 수 있다면 최소 얼마의 지출? (없으면 -1)
		int minCost = Integer.MAX_VALUE;
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int[][] dist = new int[N+1][T+1]; //비용 저장
		
		// 1은 출발지
		for (int i = 2 ; i <= N ; i++) {
			Arrays.fill(dist[i], INF);
		}
		
		queue.offer(new Node(1, 0, 0)); // 1번, 0, 0
		
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll(); // num, cur까지 오는 데 걸린 시간, cur까지 오는 데 든 비용
			
			if (cur.time > T) continue;
			
			if (cur.cost > dist[cur.num][cur.time]) continue; //">=아님!!"
			
			// 이웃(경유지로 사용) 고려 
			for (Node next : graph[cur.num]) {
                int newTime = cur.time + next.time;
                int newCost = cur.cost + next.cost;
				
                if (newTime <= T && newCost <= M) {
    				if (dist[next.num][newTime] > newCost ) {
    					dist[next.num][newTime] = newCost ;
    					queue.offer(new Node(next.num, newTime, dist[next.num][newTime]));
    				}
                }

				
			}

			
		}
        // 도착 가능한 최소 비용 찾기
        for (int t = 1; t <= T; t++) {
            if (dist[N][t] <= M) { // M 이하의 비용이어야 함
                minCost = Math.min(minCost, dist[N][t]);
            }
        }
		
		
		System.out.println(minCost == Integer.MAX_VALUE ? -1 : minCost);
		
	}

}
