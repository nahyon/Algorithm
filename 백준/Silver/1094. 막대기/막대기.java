import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1094 _ 막대기
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		
		if (X==64) { //아래 단계로 안가도 됨
			System.out.println(1);
			return;
		}
			
		int pipe = 64; //현재 파이프 제일 짧은거
		int rest = 0, cnt = 0;
		while (rest != X) { //X될 때까지
			pipe /= 2;
			if (rest + pipe <= X) {
				cnt++;
				rest += pipe;
			}
		}
		System.out.println(cnt);	
	}

}
