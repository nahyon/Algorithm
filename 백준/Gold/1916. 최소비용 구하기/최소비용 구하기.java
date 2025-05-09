import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1916 _ 최소비용 구하기
// 다익스트라 - PQ이용
public class Main {
	static class Node implements Comparable<Node> {
		int idx, weight;
		public Node(int idx, int weight) {
			this.idx = idx ; 
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	static int INF = 999_999_999;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 도시 개수 N
		int M = Integer.parseInt(br.readLine()); // 버스 개수 M
		
		// 그래프 표현 : 인접리스트
		ArrayList<Node>[] graph = new ArrayList[N+1];
		for (int i = 1 ; i <= N ; i++) {
			graph[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[start].add(new Node(end, weight));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int startIdx = Integer.parseInt(st.nextToken());
		int endIdx = Integer.parseInt(st.nextToken());
		
		
		//
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		dist[startIdx] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(startIdx, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
            if (now.idx == endIdx) break; //최종 목적지 도달 완료
            if (dist[now.idx] < now.weight) continue; //이미 방문한거
			
			for (Node next : graph[now.idx]) {
				int nextW = next.weight; // now~인접정점 사이 weight
				
				if (dist[next.idx] > (now.weight + nextW)) {
					dist[next.idx] = (now.weight + nextW) ; 
					pq.offer(new Node(next.idx, dist[next.idx]));
				}
			}			
		}
		System.out.println(dist[endIdx]);

	}

}
