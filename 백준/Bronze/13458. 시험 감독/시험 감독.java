import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] classes = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i++) {
			classes[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken()); //총감독 1
		int C = Integer.parseInt(st.nextToken()); // 부감독 여러명 ㄱㄴ 감당가능C명
		
		long cnt = 0;
		
		for (int i = 0 ; i < N ; i++) {
			classes[i] -= B; //총감독관
			cnt++;
			if (classes[i] <= 0) continue;
			//부감독관
			cnt += (classes[i] + (C-1)) / C ;
		}
		System.out.println(cnt);
		
	}

}
