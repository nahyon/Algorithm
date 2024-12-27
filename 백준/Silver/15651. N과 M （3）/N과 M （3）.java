import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15651 _ N과 M (3)
// 1부터 N까지 자연수 중  M개를 고른 수열  & 같은 수 여러 번 가능
// nㅠm : N개의 숫자 중 M개 고르기 (순서 O, 중복 O) 
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
		pperm(0);
		
		System.out.println(sb);
		

	}
	
	static void pperm(int depth) {
		//종료조건 : M개 다 뽑음
		if (depth == M) {
			for (int r : res) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		
		for (int i = 1 ; i <= N ; i++) { //자연수 1부터 N까지에 대해
			res[depth] = i;
			pperm(depth+1);
		}
	}

	
	
}
