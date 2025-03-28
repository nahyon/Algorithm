import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 3197 _백조의 호수
public class Main {
	static int N, M;
	static int[][] map;
	static Queue<int[]> iceList, waterQ;
	static int startR, startC, endR, endC;
	static final int ICE = Integer.MAX_VALUE; // 처음 빙하 상태 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
        map = new int[N][M]; // 물이 되는 날짜 저장
        //초기화 : 0 = 물 / -1 = 처음 빙하
//        iceList = new ArrayDeque<>();
        waterQ = new ArrayDeque<>();
        startR = -1; startC = -1;
        for (int i = 0; i < N; i++){
        	char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (arr[j] == 'L') { // 백조
                	if (startR == -1 ) {
                		startR = i; startC = j;
                	} else {
                		endR = i; endC = j;
                	}
                    map[i][j] = 0; // 그냥 물로 변경
                    waterQ.offer(new int[] {i, j}) ;
                } else if (arr[j] == 'X'){ // 빙하 
//                	iceList.offer(new int[] {i, j, 0}); //초기 :0일로 설정 
                	map[i][j] = ICE; // 빙하
                } else {
                	map[i][j] = 0 ; // 물
                	waterQ.offer(new int[] {i, j}) ;
                }
            }     
        }
//        int time = 0; 
//        while(!move()) { // false : 아직 못만남
//        	melt();
//        	time++;
//        }
//        System.out.println(time);
        melt();
//        printMap();
        System.out.println(move());
	}
	

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    
    
    static void melt() {
        while (!waterQ.isEmpty()) {
            int[] cur = waterQ.poll();
            int curR = cur[0]; int curC = cur[1]; 
            int day = map[curR][curC]; // day = 오늘 날짜

            for (int i = 0 ; i < 4 ;i++) { // 빙하 사방탐색
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;

                // 오늘(day) 물인 상태 : <=day
                // 아직 빙하인 애 : > day
                if (map[nextR][nextC] == ICE ) { 
                	map[nextR][nextC] = day+1; // day+1에 물이됨 
                	waterQ.offer(new int[] {nextR, nextC}); // day+1에 물이 됨
                }
            }
        }
        return;
    }
    
    // 빙하 -> 물로 변하는 날짜로 map 변경
    static void melt2() {
    	
        Queue<int[]> nextQ = new ArrayDeque<>();
        
        while (!iceList.isEmpty()) {
            int size = iceList.size();
            for (int cnt = 0; cnt < size ; cnt++) { // 현재 time에 있는 빙하를 쭉 돈다
                int[] cur = iceList.poll();
                int curR = cur[0]; int curC = cur[1]; 
                int day = cur[2]; // day = 오늘 날짜

                boolean flag = false;
                for (int i = 0 ; i < 4 ;i++) { // 빙하 사방탐색
                    int nextR = curR + dr[i];
                    int nextC = curC + dc[i];

                    if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;
                    
                    // 오늘(day) 물인 상태 : <=day
                    if (map[nextR][nextC] <= day) { // 한곳이라도 물이면 다음에 녹을 애
                        nextQ.offer(new int[] {curR, curC, day+1}); // day+1에 물이 됨
                        flag = true; // 녹을 애
                        break; // 더 볼 필요 x
                    }
                }
                // 사방에 땅이 없는 애
                if (!flag) iceList.offer(new int[]{curR, curC, day+1}); // 다시넣음 

            }
            while(!nextQ.isEmpty()){ // 빙하 녹이기
                int[] cur = nextQ.poll();
                int curR = cur[0]; int curC = cur[1]; int day = cur[2];
                map[curR][curC]= day; // 언제 물로 되는지 맵 변경 
            }
        }
        return;
    }
    
    // 오리 움직이기 
    static int move() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[N][M];
        isVisited[startR][startC] = true;
        queue.offer(new int[] {startR, startC}); 
        
        Queue<int[]> nextQ = new ArrayDeque<>(); // ***
        int today = 0;
        while(true) {
        	nextQ = new ArrayDeque<>();
            while(!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curR = cur[0]; int curC = cur[1];
//                System.out.println("오늘 : " + today + " (" + curR + " , "  + curC+ " )");
                
                // 종료 조건 
                if (curR == endR && curC == endC) return today; // 만났다
                
                for (int i = 0 ; i < 4 ;i++) { // 빙하 사방탐색
                    int nextR = curR + dr[i];
                    int nextC = curC + dc[i];

                    
                    if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;
                    if (isVisited[nextR][nextC]) continue;

                    // 갈 수 있는 경우 : <=today
                    if (map[nextR][nextC] <= today) { 
                        queue.offer(new int[] {nextR, nextC});
                    } else {
                    	nextQ.offer(new int[] {nextR, nextC});
                    }
                    isVisited[nextR][nextC] = true;
                }
            }
            
            queue = nextQ;
            today++;
        	
        }
    }
    static void printMap() {
    	for (int i = 0 ; i < N ;i++) {
    		for (int j = 0 ; j <M ;j++) {
    			System.out.print(map[i][j]);
    		}
    		System.out.println();
    	}
    }

}
