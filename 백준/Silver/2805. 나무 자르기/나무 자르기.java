import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2805 _ 나무자르기
// 이분탐색
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] trees = new int[N];
		int treeMaxHeight = 0;
		int treeMinHeight = 1000000000;
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ;i++) {
			trees[i] = Integer.parseInt(st.nextToken());
//			treeMaxHeight = Math.max(treeMaxHeight, trees[i]);
//			treeMinHeight = Math.min(treeMinHeight, trees[i]);
		}
		Arrays.sort(trees);
		
//		int midHeight = (treeMaxHeight+treeMinHeight)/2;
		
		int start = 0 ; int end = trees[N-1];
		int midIdx ; //이때가 기준 키 =  trees[startIdx]
		int height = 0;
		
//		int rest = 0;
//		int ans = trees[N-1];
		while(start<=end) {
			height = (start+end)/2;
//			System.out.println("--" + height);
			long rest = 0;
			
			for (int i = 0 ; i < N ;i++) { // 
				if (trees[i] > height) rest += (trees[i] - height);
			}
			///////
//			System.out.println(rest + " " + M);
			if (rest >= M) { //근데 커지느순간하면 그 때가 height의 최소라는걸 어케 보장함? 좀 더 height를 줄일수도있잖
//				ans = Math.max(ans, height);
				start = height+1;
			}
			else if (rest < M) { // 더 앞으로
				end = height-1;
			}
//			else if (rest > M) { // 더 뒤로 
//				start = height+1;				
//			}
			
		}
		System.out.println(end);

	}

}
