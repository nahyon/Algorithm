import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

// 백준 7795 _ 먹을 것인가 먹힐 것인가 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 0 ; t < T ; t++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int[] arrA = new int[A];
			int[] arrB = new int[B]; 
			st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < A ; i++) arrA[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < B ; i++) arrB[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arrA); Arrays.sort(arrB);
			int cnt = 0;
			int pIdx = 0;
			for (int i = 0 ; i < A ; i++) {
				int a = arrA[i]; // B에서 a보다 작은 것들의 개수 구하기 
				if (pIdx < B) {
					for (int j = pIdx ; j < B ; j++) { // j는 매번 O(N)이 아니라 pIdx활용해서 이전에 본 건 보지 않음 .
						if (arrB[j] >= a) {
							pIdx = j;
							break; 
						}
						if (j == B-1) pIdx = j+1;
					}
				}
				cnt += pIdx;
			}
	
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);

	}
}
