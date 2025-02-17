import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 12919 _ A와 B 2
public class Main {
	static String S,T ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine(); // 목표
		
		System.out.println(dfs(T) ? 1: 0);
		
	}
	
	public static boolean dfs(String current) {
		// 종료조건
		if (current.length() == S.length()) {
			return S.equals(current);
		}
		
		// 맨 뒤 문자열이 A
		if (current.charAt(current.length()-1)=='A') {
			// 1) 맨 끝 A 삭제 
			if (dfs(current.substring(0, current.length()-1))) return true;

		}
        // 2) 뒤집어서 맨 끝이 B면 삭제
        if (current.charAt(0) == 'B') {
            String reversedS = new StringBuffer(current).reverse().toString(); //뒤집기
            if (dfs(reversedS.substring(0, reversedS.length()-1))) return true;
        }

		return false;
		
	}
}
