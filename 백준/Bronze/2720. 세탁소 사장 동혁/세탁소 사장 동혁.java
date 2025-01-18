import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		int quarter, dime, nickel, penny;
		StringBuilder sb = new StringBuilder();
		for (int t = 0 ; t < TC ; t++) {
			int n = Integer.parseInt(br.readLine());
			quarter = n / 25;
			n%=25;
			dime = n / 10;
			n%= 10;
			nickel = n / 5;
			penny = n%=5;
			sb.append(quarter).append(" ").append(dime).append(" ").append(nickel).append(" ").append(penny).append("\n");
		}
		System.out.println(sb);
	}
}
