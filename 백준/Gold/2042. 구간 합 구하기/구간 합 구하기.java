import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2042 _ 구간합구하기 
// 세그먼트 트리 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 수의 개수 
		int M = Integer.parseInt(st.nextToken()); // 수 변경 일어나는 횟수 
		int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수
		
		int treeHeight = 0 ; 
		int treeSize; // 2^treeHeight * 2  
		
		// N <= 2^treeHeight 만족하는 treeHeight 찾기
		int length = N; 
		while (length != 0) {
			length /= 2;
			treeHeight++;
		}
		treeSize = (int) Math.pow(2, treeHeight+1);
		
		// 트리 1차원 배열 정의 
		long[] tree = new long[treeSize+1];
		
		// N개의 수
		int startIdx = (int) Math.pow(2, treeHeight); // 2^treeHeight
		for (int i = 0 ; i < N ;i++) {
			tree[startIdx+i] = Long.parseLong(br.readLine());
		}
		
		// 트리 완성하기 
		int i = treeSize;
		while (i != 1) {
			tree[i/2] += tree[i];
			i--;
		}
		
		
		// 구간 합 K번 구하기 
		for (i = 0 ; i < M+K ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if ( a == 1) {
				// b번 째 수를 c로 변경
				int idx = startIdx + b - 1; // 1번째 수 = startIdx
				long diff = c - tree[idx];
				// 나 ~ 부모들  변경 
				while(idx > 0) {
					tree[idx] += diff;
					idx /= 2;
				}
			} else {
				// b번째 수 ~ c번째 수 까지 합
				int leftIdx = startIdx + b - 1;
				int rightIdx = startIdx + (int) c - 1;
				
				long sum = 0;

				while (leftIdx <= rightIdx) {
					if (leftIdx % 2 == 1) { // 따로 독립적으로 빼야함 
						sum += tree[leftIdx]; 
						leftIdx += 1;
					}
					if ( rightIdx % 2 == 0) { // 따로 독립적으로 빼야함 
						sum += tree[rightIdx];
						rightIdx -= 1;
					}
					leftIdx /= 2;
					rightIdx /= 2;
					
				}
				sb.append(sum).append("\n"); 
			}
		}
		
		System.out.println(sb);
		
	}
}
