import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15649 _ N과 M (1)
// 1부터 N까지 자연수 중 중복없이 M개를 고른 수열
// nPm : N개의 숫자 중 M개 고르기 (순서 O, 중복 X) 
public class Main {
	static StringBuilder sb = null;
	static StringTokenizer st = null;
	static int N, M; 
	static boolean[] isSelected;
	static int[] res; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		res = new int[M]; //M개 고르기
		
		isSelected = new boolean[N+1]; //1부터 N까지의 자연수 (N개) 판단 위해 사용
		
		perm(0);
		
		System.out.println(sb);
	}
	
	static void perm(int depth) {
		// 종료조건
		if (depth == M) {
			for (int r : res) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 1부터 N까지의 숫자 하나씩
		for (int i = 1 ; i <= N; i++) {
			if (isSelected[i]) continue; //이미 숫자 i뽑은 경우 (중복X라했으니)
			
			isSelected[i] = true; //숫자 i뽑음
			
			res[depth] = i;
			perm(depth+1); //다음 단게로
			
			//원복
			isSelected[i] = false;  //숫자 i안뽑은걸로
				
		}
	}

}
