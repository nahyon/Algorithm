import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1753 _ 최단경로
// 다익스트라
// : 특정 정점 ~ 모든 정점 최단경로 구하기 (양의 가중치 다를 때)
public class Main {
	static int INF = 999_999_999;
	
	// 방향 그래프
	static class Node implements Comparable<Node> {
		int idx, w;
		
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
		
		public Node(int idx, int w) {
			this.idx = idx; this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//정점V개, 간선E개
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		// 시작 정점 startIdx
		int startIdx = Integer.parseInt(br.readLine());
		
		// 그래프 초기화
		ArrayList<Node>[] nodeList = new ArrayList[V+1]; // 정점 [1] ~ [V]까지 사용
		for (int i = 1 ; i <= V; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		// nodeList 채우기 - 간선(E개)만큼 정보받기
		for (int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());   // 출발
			int end = Integer.parseInt(st.nextToken());		// 도착
			int weight = Integer.parseInt(st.nextToken());  // 가중치
			
			nodeList[start].add(new Node(end, weight));
		}
		
		// 거리 배열 초기화
		int[] dist = new int[V+1]; // 정점 [1] ~ [V]까지 사용
		Arrays.fill(dist, INF);
		dist[startIdx] = 0; //출발점은 0
		
		// 우선순위 큐
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(startIdx, 0));
		boolean[] isVisited = new boolean[V+1];
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			if (isVisited[now.idx]) continue;
			isVisited[now.idx] = true;
			
			// 이웃 보기
			for (Node next : nodeList[now.idx]) {
				int nextIdx = next.idx; // now의 인접정점
				int nextW = next.w; // now~인접정점 사이 weight
				
				if (isVisited[nextIdx]) continue; // 이미 최단경로가 나온 경우 볼 필요 없음
				
				if (dist[nextIdx] > (dist[now.idx] + nextW)) {
					dist[nextIdx] = (dist[now.idx] + nextW); //dist배열 업데이트
					queue.offer(new Node(nextIdx, dist[nextIdx]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1 ; i <= V; i++) {
			int d = dist[i];
			if (d == INF) sb.append("INF");
			else sb.append(d);
			sb.append("\n");
		}
		System.out.println(sb);
	
	}
	

}
