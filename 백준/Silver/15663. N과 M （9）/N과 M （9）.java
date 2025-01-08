
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 15663 _ N과 M (9)
// N개의 자연수 중 M개를 고른 수열
public class Main {
	static StringBuilder sb = null;
	static int N, M; 
	static int[] arr, res;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); //정렬
		
		sb = new StringBuilder();
		
		res = new int[M];
		isSelected = new boolean[N];
		dfs(0);
		System.out.println(sb);
		
	}
	
	   static int before;
	   static void dfs(int depth) {
	      //종료조건
	      if (depth == M) {
	         for (int r : res) {
	            sb.append(r).append(" ");
	         }
	         sb.append("\n");
	         return;
	      }

	      before = -1;
	      
	      for (int idx = 0; idx < N; idx++) {
	         
	         if (isSelected[idx]) continue; //이미 선택된 것은 X
//	         if (idx > 0 && arr[idx] == arr[idx-1]) continue;
	         if (arr[idx] == before) continue;
	         
	         isSelected[idx] = true;
	         res[depth] = arr[idx];
	         dfs(depth+1);
	         isSelected[idx] = false; //원복
	         before = arr[idx];
	      }
	      
	   }

}

/*
// 예제 1
3 1
4 4 2

2
4

// 예제 2
4 2
9 7 9 1

1 7
1 9
7 1
7 9
9 1
9 7
9 9

// 예제 3
4 4
1 1 1 1

1 1 1 1

*/