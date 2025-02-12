import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long  A, B, C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// A를 B번 곱한 수를 C로 나눈 나머지를 출력
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(pow(B));
		
		
	}
	
    public static long pow(long exp) {
        // 0제곱, 1제곱 처리
        if (exp == 0) return 1 % C;  // 혹은 그냥 1이라 해도 무방
        if (exp == 1) return A % C;

        // 먼저 exp/2 재귀호출 결과를 한 번만 구한다.
        long half = pow(exp / 2) % C; 
        long result = (half * half) % C;  // 제곱

        // exp가 홀수이면 한 번 더 A를 곱해준다.
        if (exp % 2 == 1) {
            result = (result * (A % C)) % C;
        }
    return result;
    }
}
