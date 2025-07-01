import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

// 백트래킹 (DFS) 
// 백준 2668 _ 숫자고르기 
public class Main {
	static int[] nums;
	static HashSet<Integer> resNums; 
	static ArrayList<Integer> resIdx;
	static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 1 <= N <= 100 
		nums = new int[N+1];
		resIdx = new ArrayList<>();
		resNums = new HashSet<>();
		for (int i = 1 ; i <= N ;i++) {
			nums[i] = Integer.parseInt(br.readLine());
			if ((i) == nums[i]) { // 자기자신가리키는 것
				resIdx.add(i); // 위 숫자
				resNums.add(i); // 아래 숫자(nums배열) 
			}
		}
		
		for (int i = 1; i<= N ; i++) { //숫자 i부터 시작해서 파고들어가기
			if (!resNums.contains(i)) { // 아직 정답배열에 안들어감 
				boolean[] isVisited = new boolean[N+1];
				isVisited[i] = true; // 시작은 visit하지 않고 가기
				dfs(i, nums[i], isVisited); // nowIdx, nowNum (==> nextIdx가 됨 )
			}
		}
		
		Collections.sort(resIdx);
		StringBuilder sb = new StringBuilder();
		sb.append(resIdx.size()).append("\n");
		for (int i = 0 ; i < resIdx.size(); i++) {
			sb.append(resIdx.get(i)).append("\n");
		}
		System.out.println(sb);
		
	}
	
	static void dfs(int startIdx, int nowNum, boolean[] isVisited) {

		// 가지치기 
//		if (resNums.contains(nowNum)) return; // 이전에 선택된 숫자
		// 종료조건
		if (isVisited[nowNum]) { // 사이클 생김
			if (startIdx == nowNum) {
				for (int i = 1; i <= N ; i++) {
					if (isVisited[i]) {
						resIdx.add(i); // 위 숫자
						resNums.add(i); // 아래 숫자(nums배열) 
					}
				}
			}
			return; 
		}
		
		isVisited[nowNum] = true; // 위 조건탐색후 여기서 방문처리 

//		// 가지치기 
		if (resNums.contains(nums[nowNum])) return; // 이전에 선택된 숫자
		
		dfs(startIdx, nums[nowNum], isVisited);
		
	}

}
