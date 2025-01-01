import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1259 _ 팰린드롬수
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		while (Integer.parseInt(s) != 0) {
			int len = s.length();
			char[] s2 = s.toCharArray();
			String ans = "yes";
			for (int i = 0 ; i < len/2 ; i++) {
				if (s2[i] != s2[len-i-1]) {
					ans = "no";
					break;
				}
			}
			sb.append(ans).append("\n");
			s = br.readLine();
		}
		
		System.out.println(sb);
	}

}
