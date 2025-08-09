import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1759 _ 암호 만들기 
public class Main {
	static int L, C;
	static StringBuilder sb = null;
	static ArrayList<String> mo = null;
	static ArrayList<String> ja = null;
	static char[] alpha;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken()); // 암호 길이 
		C = Integer.parseInt(st.nextToken()); // C개의 문자
		
		st = new StringTokenizer(br.readLine(), " ");

		alpha = new char[C];
		for (int i = 0; i < C ; i++) 
			alpha[i] = st.nextToken().charAt(0); // 문자 하나
		Arrays.sort(alpha);
		sb = new StringBuilder();
		dfs(0, 0, new char[L], 0);

		
		System.out.println(sb);
	}
	
	static void dfs(int depth, int start, char[] password, int cnt) { //cnt는 모음개수
		
		
		if (depth == L ) {// 종료
			if (cnt>=1 && (L-cnt) >=2) sb.append(String.valueOf(password)).append("\n");
			return;
		}
		
		for (int i = start ; i < C ; i++) {		
			char c = alpha[i];
			password[depth] = c;
			if ( c=='a' || c == 'e' || c == 'i' || c=='o' || c == 'u') dfs(depth+1, i+1, password, cnt+1);
			else dfs(depth+1, i+1, password, cnt);
		}
		
	}

}
