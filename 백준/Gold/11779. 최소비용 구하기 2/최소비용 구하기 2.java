import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 11779 _ 최소비용 구하기2
public class Main {
	static int N, M;
	static StringBuilder sb = null;
	static class Node implements Comparable<Node> {
		int num, cost;
		int cnt; // 지나친 노드 개수 
		public Node (int num, int cost) {
			this.num = num; this.cost = cost;
		}
		public Node (int num, int cost, int cnt) {
			this.num = num; this.cost = cost; this.cnt = cnt; 
		}
		@Override
		public int compareTo(Node o) {
			return (this.cost-o.cost); // 오름차순
		}
	}
	static ArrayList<Node>[] graph ;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 도시 개수 (1이상 1,000 이하)
		M = Integer.parseInt(br.readLine()); // 버스 개수 (1이상 100,000이하)
		graph = new ArrayList[N+1]; //인덱스 1부터N까지 사용
		for (int i = 1 ; i <= N ; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// (출발, 도착, 비용) (비용 0이상 100,000이하)
		StringTokenizer st = null;
		for (int i = 0 ; i < M ; i++ ) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, c));
		}
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		// 출발, 도착지
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start, end);
		
		System.out.println(sb);
	}
	
	public static void dijkstra(int start, int end) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(start, 0, 1));
		int[] previousNode = new int[N+1];  // previousNode[다음노드] = 현재노드
		int[] dist = new int[N+1];
		Arrays.fill(dist, 999_999_999); // INF값으로 초기화 
		dist[start] = 0;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			// 종료 
			if (cur.num == end) {
				sb.append(dist[end]).append("\n");
				sb.append(cur.cnt).append("\n");
				break;
			}
			
			if (cur.cost > dist[cur.num] ) continue; 
			
			
			// 이웃
			for (Node next : graph[cur.num]) {

				if (dist[next.num] > dist[cur.num] + next.cost ) {
					dist[next.num] = dist[cur.num] + next.cost ; 
					// cur.num -> next.num
					previousNode[next.num] = cur.num; 

					queue.offer(new Node(next.num, dist[next.num], cur.cnt+1));
				}
			}
		}


		// 지나온 경로 ; 역순으로 추적
		ArrayList<Integer> path = new ArrayList<>();
		int n = end;
		
		while (n!=start) { //n==start되는 순간 나옴
			path.add(n);
			n = previousNode[n]; 
		}
		path.add(start);
		
		// 역순
		Collections.reverse(path);
		
		for (int num : path) {
			sb.append(num).append(" ");
		}
				
	}

}
