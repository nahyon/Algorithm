import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int glacierCnt;
    static int[][] grid;
    static Queue<int[]> glacierList; // 빼고 순환하고 해야되니까 ArrayList(x) , 큐사용하기

    static Queue<int[]> nextMelt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        glacierList = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) { // 빙하
                    glacierCnt++;
                    glacierList.offer(new int[]{i, j});
                }
            }     
        }

        // 다음 녹을 빙하 찾기
        // isVisited = new boolean[n][m];
        startR = 0; startC = 0;
        int time = 1;
        int cnt = 0;
        while(true) {
            cnt = melt();
            if (glacierCnt == 0) break;
            time++;
            // printMap();
        }
        System.out.println(time);
        System.out.println(cnt);
    }

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    // static boolean[][] isVisited;
    static int startR, startC;
    static int melt() {
        Queue<int[]> nextQ = new ArrayDeque<>();
        
        boolean[][] isVisited = new boolean[n][m];
        isVisited[startR][startC] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startR, startC});

        //초기화. 땅을 지나다니면서 녹일 빙하 찾기
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curR = cur[0]; int curC = cur[1];

            // isVisited[curR][curC] = true; //*******
            boolean flag = false;
            for (int i = 0 ; i < 4 ; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) continue;
                if (isVisited[nextR][nextC]) continue;

                if (grid[nextR][nextC] == 1) { // cur(땅) 인접 빙하 => 녹을 애
                    // isVisited[nextR][nextC] = true; // 인접 한거 안드가게 
                    nextQ.offer(new int[] {nextR, nextC});
                } else { // 땅
                    queue.offer(new int[] {nextR, nextC});
                    // isVisited[nextR][nextC] = true; 
                }    
                isVisited[nextR][nextC] = true;  
            }
        }
        int size = nextQ.size();
        while(!nextQ.isEmpty()){ // 빙하 녹이기
            int[] cur = nextQ.poll();
            int curR = cur[0]; int curC = cur[1];
            grid[curR][curC]= 0; // 땅으로 변경
            glacierCnt--;
            // startR = curR; startC = curC; // 새로 녹는애로 업데이트
        }
        // for (int i = 0 ; i < n ; i++) {
        //     for (int j = 0; j < m ; j++) {
        //         System.out.print(isVisited[i][j] ? 1:0);
        //         System.out.print(" ");
        //     }
        //     System.out.println();
        // }

        // 처음 빙하 개수 - 녹은 후 빙하 개수
        return size; // 이번타임에 녹은 개수
    }

    static void printMap() {
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0; j < m ; j++) {
                System.out.print(grid[i][j] + " " );
            }
            System.out.println();
        }

    }

    
}
