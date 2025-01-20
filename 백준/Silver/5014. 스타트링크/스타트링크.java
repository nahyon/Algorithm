import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 5014 _ 스타트링크
public class Main {
	static int F,S,G,U,D;
	static boolean[] building;
	static int[] move;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken()); // 건물 총
		S = Integer.parseInt(st.nextToken()); // 시작
		G = Integer.parseInt(st.nextToken()); // 목표
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		if (S==G) {
			System.out.println(0);
			return;
		}
		building = new boolean[F+1];
		building[0] = true;
		
		move = new int[] {U, -D};
		
		building[S] = true;
		int ans = bfs(S);
		
		
		if (ans == -1 ) System.out.println("use the stairs");
		else System.out.println(ans);
		
	}
	
	static int bfs(int start) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {start, 0});
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			
			if (now[0] == G) {
				return now[1];
			}
			
			for (int i = 0 ; i < 2 ;i++) {
				int next = now[0] + move[i];
				
				if (!OOB(next)) continue;
				building[next] = true;
				queue.offer(new int[] {next, now[1]+1 } );
			}
		}
		
		return -1;
	}
	
	static boolean OOB(int floor) {
		if (floor < 1 || floor > F) return false;
		if (building[floor]) return false;
		return true;
	}

}
