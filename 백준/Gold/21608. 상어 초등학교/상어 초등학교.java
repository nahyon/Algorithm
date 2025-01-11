import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[] selected;
	static Node[] studentPos; // 앉은 위치 저장
	static int[][] favorites;
	
	static class Node{
		int r, c; int blankCnt;
		public Node(int r, int c) {
			this.r = r; this.c = c;
		}
	}
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		selected = new boolean[N*N+1]; //1 ~ N*N까지만 씀
		studentPos = new Node[N*N+1];; //학생의 앉은 위치 저장
		favorites = new int[N*N+1][4]; // 학생r(행 인덱스)의 각각의 최애친구 4명(4개의 열에 저장됨)
		
		// favorites배열 : 학생들의 최애 친구 저장
		StringTokenizer st = null;
		for (int i = 0 ; i < N*N; i++ ) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); 
			for (int j = 0 ; j < 4; j++) {
				// 학생 n의 최애 친구들 4명 [n][0]~[n][3]
				favorites[n][j] = Integer.parseInt(st.nextToken()); 
			}
			// 학생 n 앉히기
			setStudent(n);
//			printMap();
//			System.out.println();
		}

		// 만족도 총합
		System.out.println(getScore());
	}
	
	// 학생 앉히기
	public static void setStudent(int student) { //학생번호 student
		// 1. 최애 4명 중 한 명이라도 앉아있는지 체크
		boolean exist = false;
		int[][] cntMap = new int[N][N];
		
		int ddd = 0;// 반례 해결용
		for (int n : favorites[student]) { //이 학생(student)의 최애 4명
			if (selected[n]) { // 이미 자리에 앉아있음
				exist = true; // 변경
				Node pos = studentPos[n]; //최애 학생n의 위치
				// 이 아이 4방탐색하며 카운팅 +1 하기 (== 그 위치에서 최애인접 수를 의미하게됨 cntMap)
				for (int i = 0 ; i < 4; i++) {
					int nextR = pos.r+dr[i];
					int nextC = pos.c+dc[i];
					if (nextR<0 || nextC<0 || nextR>=N || nextC>=N ) continue;
					if (map[nextR][nextC] > 0) continue; //
					cntMap[nextR][nextC]++;
					ddd++; //
				}
			}
		}
		// 1-1. 후보가 아에없으면 왼쪽 위부터 차례차례 보면서 인근에 blank가장 많은 곳 or 인근에 blank도 없으면 맨첨에 나오는애..?
		Node maxNode = null ;
		int blankCnt = 0;
//		System.out.println("ddd : " + ddd);
		if (!exist || (ddd == 0)) {
			for (int i = 0 ; i < N ; i++) {
				for (int j = 0 ; j < N; j++) {
					if (map[i][j] > 0 ) continue; //이미 앉은 칸 (빈 칸 아님)
					
					// blank 개수 세기
					int tempCnt = 0;
					for (int k = 0 ; k < 4; k++) { // 사방탐색
						int nextR = i+dr[k];
						int nextC = j+dc[k];
						if (nextR<0 || nextC<0 || nextR>=N || nextC>=N ) continue;
						if (map[nextR][nextC] == 0 ) tempCnt++; // 빈 칸
					}
					
					if (blankCnt < tempCnt) {
						maxNode = new Node(i, j);
						blankCnt = tempCnt;
					}


				}
			}
			
			// ㅇㄹㅇㄹㅇㄹㅇㄹ
			if (blankCnt == 0 ) {
				for (int i = 0 ; i < N ; i++) {
					for (int j = 0 ; j < N; j++) {
						if (map[i][j] == 0) {
							setStudentPos(student, i, j);
							return;
						}
					}
				}
			}
			
			setStudentPos(student, maxNode.r, maxNode.c);
			return; //함수 종료
			
		}
		
		// 1-2. 후보 중에 고르기. 있으면 인접 학생이 제일 최대로 많은 곳에 위치 (cntMap사용)
		// cntMap에서 가장 큰 수 저장된 곳== 인접학생이 최대로 많은 곳
		// 최대숫자 동일한게 여러개라면 blank가 가장 많은 곳 
//		Node maxNode = null ;
//		int maxCnt = 0; int blankCnt = 0;
		int maxCnt = 0;
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < N; j++) { //좌측맨위부터 보면 자동으로 3번 조건까지 만족 가능
//				if (cntMap[i][j] == 0 ) continue; //후보 칸이 아님. 후보X
				if (map[i][j] > 0 ) continue; //이미 앉은 칸 (빈 칸 아님)
				
				if (cntMap[i][j] >= maxCnt) {
					
					// blank 개수 세기
					int tempCnt = 0;
					for (int k = 0 ; k < 4; k++) { //후보들 중 하나 추려내기
						int nextR = i+dr[k];
						int nextC = j+dc[k];
						if (nextR<0 || nextC<0 || nextR>=N || nextC>=N ) continue;
						if (map[nextR][nextC] == 0 ) tempCnt++; // 빈 칸
					}
					
					if (cntMap[i][j] > maxCnt) { //maxCnt보다 큰 경우 : 무조건 업데이트
						maxCnt = cntMap[i][j];
						maxNode = new Node(i, j);
						blankCnt = tempCnt;
					} else { //같을 땐 빈칸 개수 비교해서 업데이트 필요
						if (tempCnt > blankCnt) { //이 경우만 해당 노드로 업데이트
							maxNode = new Node(i, j);
							blankCnt = tempCnt;
						}
						// 아니면(blank개수 "같거나!!" 적으면 계속 이전거로 유지함 )
						// (같은 경우는 최대한 왼쪽 위니까 . 이 경우도 업데이트하면안되고 이전거로 유지해야되는거)
					}
				}

			}
		}
		setStudentPos(student, maxNode.r, maxNode.c);

	}
	// 자리 선정
	public static void setStudentPos(int i, int r, int c) {
		selected[i] = true;
		map[r][c] = i;
		studentPos[i] = new Node(r, c);
	}
			
	// 만족도 계산
	public static int getScore() {
		int student;
		int score = 0;
		// 지도 한 칸씩
		for (int i = 0; i < N; i++) {
			for (int j = 0 ; j < N; j++) {
				student = map[i][j];
				int cnt = 0; 
				for (int k = 0 ; k < 4; k++	) { //4방 탐색
					int nextR = i+dr[k];
					int nextC = j+dc[k];
					if (nextR<0 || nextC<0 || nextR>=N || nextC>=N ) continue;
					for (int n : favorites[student]) { //이 학생의 최애 4명
						if (map[nextR][nextC] == n) { // 인접칸에 최애가 있다면
							cnt++;
							break;
						}
					}
				}
				if (cnt == 0) continue;
				score += Math.pow(10, cnt-1);
			}
		}
		return score;
	}
	
	static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0 ; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
