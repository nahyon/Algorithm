import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 3055 _ 탈출 
public class Main {
	static int N, M;
	static int[][] map;
	static Queue<int[]> waterQ;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int startR=0 , startC=0 ; 
        waterQ = new ArrayDeque<>();
        boolean[][] waterVisited = new boolean[N][M];
        // 일반 땅 : -1
        // 물 : 0 ~ 숫자 (며칠에 물 되는지)
        // 목적지(비버굴) : -2
        // 돌 : -3 (물, 고슴도치 둘 다 못감) 
        for (int i = 0; i < N; i++){
        	char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (arr[j] == 'S') { // 고슴도치 시작점 
                	startR = i; startC = j;
                    map[i][j] = -1; //그냥 땅으로 변경 
                } else if (arr[j] == 'X') map[i][j] = -3;
                else if (arr[j] == 'D') map[i][j] = -2;
                else if (arr[j] == '.') map[i][j] = -1;
                else { // 초기상태 물 ('*')
                	map[i][j] = 0; // 0일
                	waterQ.offer(new int[] {i, j, 0}); // 0일에 물인상태 
                	// waterVisited[i][j] = true;
                }
            }     
        }
//        printMap();
        changeMap();
//        printMap();
        int day = bfs(startR, startC);
        
        System.out.println(day == -1 ? "KAKTUS" : day);
        
	}

	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	// 시간에 따른 물 확장 맵
	static void changeMap() {
		
		// 이 날 처음 물 개수 : size
//		int size = waterQ.size();
		while(!waterQ.isEmpty()) {
			int[] cur= waterQ.poll();
			int curR = cur[0]; int curC = cur[1]; int day = cur[2];
			
			for (int i = 0 ; i < 4 ;i++) {
				int nextR = curR + dr[i];
				int nextC = curC + dc[i];
				
				if (nextR < 0 || nextC < 0 ||nextR >= N || nextC >= M) continue;
				if (map[nextR][nextC] == -3 || map[nextR][nextC] == -2) continue; //돌, 비버굴
				if (map[nextR][nextC]>=0) continue; //이미 물인 경우
				map[nextR][nextC] = day+1;
				waterQ.offer(new int[] {nextR, nextC, day+1}) ;
			}
		}
	}
	
	// 고슴도치움직이기 
	static int bfs(int startR, int startC) {
		boolean[][] isVisited = new boolean[N][M];
		isVisited[startR][startC] = true;
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {startR, startC, 0});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curR = cur[0]; int curC = cur[1]; int day = cur[2];
			
			if (map[curR][curC]== -2) return day; // 비버굴 도착 (목적지) 
			
			for (int i = 0 ; i < 4 ;i++) {
				int nextR = curR + dr[i];
				int nextC = curC + dc[i];
				
				if (nextR < 0 || nextC < 0 ||nextR >= N || nextC >= M) continue;
				if (map[nextR][nextC] == -3) continue; //돌
				if (isVisited[nextR][nextC]) continue;
				// 당일 (day) 물이거나 다음날(day+1)인 경우 못감
				// 오늘 = day -> 다음번에 갈 수 있는 곳 ? map[nextR][nextC] > day+1인경우
				if (map[nextR][nextC] >= 0 && map[nextR][nextC] <= day+1) continue;
				
				// 물이 아니거나 땅인 경우 : 비버 움직임
				queue.offer(new int[] {nextR, nextC, day+1}) ;
				isVisited[nextR][nextC] = true; 
				
			}
		}
		return -1;
		
	}
	
	static void printMap() {
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < M ;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
