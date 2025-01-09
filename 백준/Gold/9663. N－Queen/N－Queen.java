import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 9663 _ N-Queen
// N*N 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓기
public class Main {
	static int N;
	static int[] map;
	static boolean[] isSelected;
	static int cnt;
		
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N];
		Arrays.fill(map, -1);
		isSelected = new boolean[N];
		
		cnt = 0;
		dfs(0);
		System.out.println(cnt);
	}
	
	//depth행에서 선택된 "열"은  map[depth]에 저장됨
	static void dfs(int depth) {
		if (depth == N) {
			cnt++;
			return;
		}
		// "열" 하나씩 선택
		for (int col = 0 ; col < N ; col++) {
			/* (depth, col) 이 되는지 판단 */
			
			//같은 열 안됨
			if (isSelected[col]) continue; 
			
			// 대각선 안됨 (위쪽부터 밑으로 열 내려오니까. 위 대각선만 판단하면됨)
			boolean flag = false;
			if (depth > 0) {
				for (int i = 0 ; i < depth ; i++) {
					if ( ((depth-i) == (col-map[i])) 
							|| ((i-depth) == (col-map[i])) ) {
						flag = true;
						break;
					}
				}
			}
			if (flag) continue;
			
			isSelected[col] = true; //열 col 선택됨
			map[depth] = col; //depth행에서 선택된 열 col
			
			dfs(depth+1); // 다음 "행" 선택하러
			
			isSelected[col] = false;
			
		}
	}

}
