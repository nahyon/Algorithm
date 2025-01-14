import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1238 _ 파티
// 다익스트라 (그래프 표현 - 인접리스트VER)
public class Main {
	static class Node implements Comparable<Node> {
		int idx, w;
		public Node(int idx, int w) {
			this.idx = idx ; 
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	static int[] dist1, dist2;
	static int INF = 999_999_999;
	static int N; //노드개수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N개의 마을
		int M = Integer.parseInt(st.nextToken()); // M개의 간선
		int destIdx = Integer.parseInt(st.nextToken()); // 목적지
		
		// 그래프 초기화
		ArrayList<Node>[] graph = new ArrayList[N+1];
		for (int i = 1 ; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 역방향 계산에 사용될 그래프
		ArrayList<Node>[] graph2 = new ArrayList[N+1];
		for (int i = 1 ; i <= N; i++) {
			graph2[i] = new ArrayList<>();
		}

		
		// 입력
		for (int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[start].add(new Node(end, weight));
			graph2[end].add(new Node(start, weight)); //역방향용
		}
		

		dist1 = new int[N+1]; //전부다 ~ 도착지dest (역방향으로 생각) 도착지dest ~ 전부다
		dist2 = new int[N+1]; //출발지dest에서 전부다
		Arrays.fill(dist1, INF);
		Arrays.fill(dist2, INF);
		
		dijkstra(destIdx, dist1, graph2); //그래프 역방향으로 보기
		dijkstra(destIdx, dist2, graph); //그래프 정방향으로 보기
		
		
		int maxDist = 0;
		for (int i = 1 ; i <= N ; i++	) {
			int d = dist1[i] + dist2[i];
			if (maxDist < d) maxDist = d;
		}
		System.out.println(maxDist);
		
	}
	
	static void dijkstra(int start, int[] dist, ArrayList<Node>[] graph) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		dist[start] = 0;
		queue.offer(new Node(start, dist[start]));
		
		int cnt = 0; // 노드개수
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if (dist[cur.idx] < cur.w) continue; // 이미 방문완료한 노드 무시 
			
			if (++cnt == N) break; // 종료조건(시간단축) : 모든 노드 방문함
			
			for (Node next : graph[cur.idx]) { //이웃 노드 탐색
				int nextIdx = next.idx;
				int stopWeight = next.w; //경유지cur ~ nextIdx 사이 가중치값
				
				// 경유지(cur) 거쳐가는 경우 dist배열 업데이트 판단
				if (dist[nextIdx] > cur.w + stopWeight) {
					dist[nextIdx] = cur.w + stopWeight;
					queue.offer(new Node(nextIdx, dist[nextIdx])); 
				}
			}
			
		}
		
	}
	
	
	

}
