import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준 2805 _ 나무자르기
//이분탐색
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] trees = new int[N]; int right = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ;i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, trees[i]);
		}
		
//		Arrays.sort(trees);
//		int left = 0; int right = trees[N-1];
		int left = 0;
		
		int maxHeight = 0;
		while(left<=right) {
			int targetHeight = (left+right) / 2;
			
			// 가져가는 길이
			long targetLength = 0;
			for (int treeHeight : trees) {
				if (treeHeight > targetHeight )
					targetLength += (treeHeight - targetHeight);
			}
			
			if ( targetLength >= M ) { // M이상 만족하면, 최대한 절단기높이 올려야함 -> left를 우측으로
				maxHeight = Math.max(targetHeight, maxHeight);
				left = targetHeight + 1;
			} 
			else right = targetHeight - 1;
		}
		
		System.out.println(maxHeight);
	}

}
