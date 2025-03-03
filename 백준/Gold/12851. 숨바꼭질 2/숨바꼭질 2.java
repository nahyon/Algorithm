import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 12851 _숨바꼭질2 
public class Main {
	static int start, end;
	static int time = Integer.MAX_VALUE;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		start = Integer.parseInt(st.nextToken()); // 수빈위치 
		end = Integer.parseInt(st.nextToken()); // 동생 위치 
		
        if (start == end) { // 시작과 끝이 같다면 즉시 출력
            System.out.println(0);
            System.out.println(1);
            return;
        }
        
		search(); 
		
		System.out.println(time);
		System.out.println(cnt);
	
	}
	static int[] dir = {2, -1, +1};
	static void search() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		int[] dist = new int[100001]; // 소요 시간 넣기
		Arrays.fill(dist,Integer.MAX_VALUE);
		
		dist[start] = 0;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
            if  (dist[cur] > time) continue;
            
			for (int i = 0; i < 3; i++) {
			    int next = (i == 0) ? cur * dir[i] : cur + dir[i];
			
			    if (OOB(next)) continue; // 범위 밖 
			    if (dist[next] < dist[cur] + 1) continue; // 기존보다 오래걸림 
			
			    if (dist[next] >=  dist[cur] + 1) {
			        dist[next] = dist[cur] + 1;
			        queue.offer(next);
			    }
			    if (next == end) {
                    cnt++;
                    time = dist[end];
                }
			}
		}
	}
	
	
	static boolean OOB(int x) {
		return (x < 0 || x > 100000) ; // || (x>end*2)
	}
}
