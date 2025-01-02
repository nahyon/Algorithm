import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] memory = new int[41][2];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //테스트 케이스
		StringBuilder sb = new StringBuilder();
		
		memory[0] = new int[]{1, 0};
		memory[1] = new int[]{0, 1};
		
		// dp배열 만들기
		for (int i = 2 ; i < 41; i++) {
			memory[i] = makearr(i);
		}
		for (int i = 0 ; i < T; i++ ) {
			int num = Integer.parseInt(br.readLine());
			sb.append(memory[num][0]).append(" ").append(memory[num][1]).append("\n");
		}
		System.out.println(sb);
	}
	
	static int[] makearr(int num) {
		return new int[] {memory[num-1][0] + memory[num-2][0], 
				memory[num-1][1] + memory[num-2][1]};
	}

}
