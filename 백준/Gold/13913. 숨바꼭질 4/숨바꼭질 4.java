import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 13913 _숨바꼭질 4 
public class Main {
	static int start, end;
	static int[] previousPos;
	static StringBuilder sb ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		
		search();
		System.out.println(sb);
		
	}
	
	static int[] dir  = {2, -1, +1};
	
	static void search() {
		Queue<int[]> queue  = new ArrayDeque<>(); // 위치, 소요시간
		
		previousPos = new int[100001];
		Arrays.fill(previousPos, -1);
		
		queue.offer(new int[] {start, 0});
		
		int ans = 0;
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			int cur = poll[0];
			int time = poll[1];
					
			if (cur == end) {
				ans = time;
				break;
			}
			
			for (int i = 0 ; i < 3 ; i++) {
				int next = (i==0) ? cur * dir[i] : cur + dir[i];
				
				if (next < 0 || next > 100000 ) continue; //범위 밖 
				
				if (previousPos[next] != -1) continue; // 이미 도착한 적 있음
				
				// cur -> next // previousPos[next] = cur;
				previousPos[next] = cur;
				queue.offer(new int[] {next, time+1});
				
			}
		}
		
		sb.append(ans).append("\n" );
		ArrayList<Integer> route = new ArrayList<>();
		int n = end;
		while (n != start) {
			route.add(n);
			n = previousPos[n];
		}
		route.add(n); // start
		Collections.reverse(route);
		
		for (int x : route) {
			sb.append(x).append(" ");
		}
	}

}
