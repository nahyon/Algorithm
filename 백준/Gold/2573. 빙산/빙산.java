import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2573 _빙산
public class Main {
	static int[][] grid;
	static Queue<int[]> glacierList;
	static int N, M, glacierCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
        grid = new int[N][M];
        glacierList = new ArrayDeque<>();
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] > 0 ) { // 빙하
                    glacierCnt++; // 빙하 개수 +1 
                    glacierList.offer(new int[]{i, j});
                }
            }     
        }
        
        // 애초에 빙산이 한 개 있거나 아에 없을경우 <예외처리>
        if (glacierCnt <= 1) {
        	System.out.println(0);
        	return;
        }
        int time = 0 ; 
        while(count()) { //count()가 true일 땐 계속 한 덩어리인것
        	melt(); time++;
        	if (glacierCnt== 0 ) break;
        }
        System.out.println(glacierCnt == 0 ? 0 :time);
	}
	

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static void melt() {
        // 빙하리스트를 한바퀴만 돌아야하고, glacierList큐에 안넣는건 다시 집어넣을거라
        // while (!isEmpty)말고, for문 으로 처음 큐 사이즈만큼만 돌기
        Queue<int[]> nextQ = new ArrayDeque<>();
        int size = glacierList.size();
        for (int k = 0; k < size ; k++) { // 현재time에 있는 빙하를 쭉 돈다
            int[] cur = glacierList.poll();
            int curR = cur[0]; int curC = cur[1];

           	int cnt = 0; // 빙하 주변의 땅의 개수 
            for (int i = 0 ; i < 4 ;i++) { // 빙하 사방탐색
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;

                if (grid[nextR][nextC] == 0) cnt++; // 인접한 곳이 땅이다. 
            }
            // 사방에 땅이 없는 애
            if (cnt==0) glacierList.offer(new int[]{curR, curC}); // 다시넣음 
            else nextQ.offer(new int[]{curR, curC, cnt}); 

        }
        while(!nextQ.isEmpty()){ // 빙하 녹이기 - 아에 없어진게 아니라면 다시 glacierList에 넣어야한다.
            int[] cur = nextQ.poll();
            int curR = cur[0]; int curC = cur[1]; int cnt = cur[2];
            
            if (grid[curR][curC] <= cnt) {
                grid[curR][curC]= 0; // 땅으로 변경
                glacierCnt--;
            } else {
            	grid[curR][curC] -= cnt;
            	glacierList.offer(new int[] {curR, curC});
            }

        }
        return;
    }
    
    static boolean count() {
    	int cnt = 0;
    	int[] start = glacierList.peek(); // 임의의 빙하 1개에서 시작
    	int startR = start[0]; int startC = start[1];
    	boolean[][] isVisited = new boolean[N][M];
    	isVisited[startR][startC] = true;
    	Queue<int[]> queue = new ArrayDeque<>();
    	queue.offer(new int[] {startR, startC});
    	
    	while(!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		int curR = cur[0]; int curC = cur[1];
    		cnt++; // 빙하 개수 
    		
    		for (int i = 0 ; i < 4 ; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;
                if (isVisited[nextR][nextC]) continue;
                
                if (grid[nextR][nextC] != 0) {
                	queue.offer(new int[] { nextR, nextC});
                }
                isVisited[nextR][nextC] = true;
    		}
    	}
    	
    	return cnt == glacierCnt; // true : 한 덩어리 / false: 분리됨 
    }
    static void printMap() {
    	for (int i = 0 ; i < N ;i++) {
    		for (int j = 0 ; j <M ;j++) {
    			System.out.print(grid[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}
