import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 15650 _ N과 M (2)
//1부터 N까지 자연수 중 중복없이 M개를 고른 수열 (순서의미X)
//nCm : N개의 숫자 중 M개 고르기 (순서 X, 중복 X) 
public class Main {
	static StringBuilder sb ;
	static int N, M;
	static int[] res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		
		res = new int[M]; //M개 뽑은 정답 배열
		
		comb(0, 1);
		
		System.out.println(sb);
	}
	
	static void comb(int depth, int start) { //depth번째 뽑는다
		// 숫자 start부터 고려해서 숫자 뽑는 것
		// start 앞 숫자들은 이미 전에 고려했다는 의미로 생각
		
		//종료조건 : M개 다 뽑음
		if (depth == M) {
			for (int r : res) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start ; i <= N; i++) {
			// start부터 마지막숫자N까지 본다
			
			res[depth] = i; //숫자 i뽑음 (start아님! 실수하지마)
			comb(depth+1, i+1); //i+1숫자부터 또 뽑으러 간다 (depth+1, start+1)아님!!!
		}

	}

}
