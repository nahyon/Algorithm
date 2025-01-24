import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		boolean[] numbers = new boolean[end+1];
		//false인게 소수
		numbers[1] = true;
		
		for (int i = 2 ; i <= end; i++) {
			if (numbers[i]) continue;
			for (int j = 2 ; i*j <= end; j++) { //자기자신은 그대로둠
				numbers[i*j] = true;
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		for (int i = start; i <= end; i++) {
			if (!numbers[i]) sb.append(i).append("\n");
		}
		System.out.println(sb);
		
	}

}
