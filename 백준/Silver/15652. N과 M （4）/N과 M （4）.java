import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15651 _ N과 M (4)
// 1부터 N까지 자연수 중  M개를 고른 수열  & 같은 수 여러 번 가능 & 비내림차순(==순서 정해져있음)
// nHm : N개의 숫자 중 M개 고르기 (순서 X, 중복 O)
// 중복을 허용하는 조합
public class Main {
	static StringBuilder sb = null;
	static int N, M;
	static int[] res;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		res = new int[M];
		ccomb(0, 1);
		
		System.out.println(sb);

	}
	
	static void ccomb(int depth, int start) {
		if (depth == M) {
			for (int r : res) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			res[depth] = i;
			ccomb(depth+1, i);
		}
	}

}
