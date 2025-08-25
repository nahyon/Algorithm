import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		String[] arr = new String[str.length()];
		
		arr[0] = str; 
		for (int i = 1 ; i < arr.length  ; i++) {
			arr[i] = str.substring(i, str.length());
		}
		
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i <arr.length ; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb);
	
	}
}
