import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 9935 _ 문자열폭발 
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 알파벳 소문자 대문자 , 숫자 0~9로 이루어짐 
		
		char[] arr = br.readLine().toCharArray(); // 문자열 
		char[] bomb = br.readLine().toCharArray(); // 폭발 문자열 

		Stack<Character> stack = new Stack<>();
		
		for (int i = 0 ; i < arr.length; i++) {
			stack.push(arr[i]);
			
			if (stack.size() >= bomb.length) { // 폭발 가능 
				boolean flag = true;
				
				for (int j = 0 ; j < bomb.length; j++) {
					if (stack.get(stack.size() - bomb.length + j) != bomb[j]) {
						flag = false;
						break;
					}
				}
				
				if (flag) {
					for (int j = 0 ; j < bomb.length; j++) stack.pop();
				}
				
			}
		}

		if (stack.isEmpty()) System.out.println("FRULA");
		else {
			StringBuilder sb = new StringBuilder();
			for (char c : stack) {
				sb.append(c);
			}
			System.out.println(sb);
		}
	}
	


}
