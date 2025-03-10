import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1707 _이분 그래프
public class Main {
	static ArrayList<Integer>[] nodeList;
	static int[] colors;
	static int cnt ;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0 ; t < T ; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken()); // 정점 
			int E = Integer.parseInt(st.nextToken()); // 간선 
			
			nodeList = new ArrayList[V+1];
			for (int i = 1 ; i <= V ; i++) {
				nodeList[i] = new ArrayList<>();
			}
			
			for (int i = 0 ; i < E ; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				nodeList[a].add(b);
				nodeList[b].add(a);
			}
			
			boolean flag = true;
			colors = new int[V+1];
			for (int i = 1 ; i <= V; i++) {
				if (colors[i] != 0) continue; // 이미 방문한 곳
				flag = bfs(i);
				if (!flag) break; //flag = false : 불가능 
			}
			sb.append(flag ? "YES\n" : "NO\n");
		}
		System.out.println(sb);

	}

	static boolean bfs(int s) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(s);
		colors[s] = 1; 
		int beforeColor = 1;
		
		boolean flag = true;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			beforeColor = colors[cur];
//			int nextColor = beforeColor == 1 ? 2 : 1;
			
			for (int next : nodeList[cur]) {
				if (colors[next] == beforeColor) {
					flag = false;
					break;
				}
				if (colors[next] == 0) {
					colors[next] = beforeColor == 1 ? 2 : 1;
					queue.offer(next);
				}
			}
			if (!flag) break;
		}
		return flag;
	}
}
