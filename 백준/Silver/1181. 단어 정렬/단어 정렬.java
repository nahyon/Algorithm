import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

// 백준 1181 _ 단어 정렬 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		TreeSet<String> set = new TreeSet<>((a, b) ->  {
			int diff = Integer.compare(a.length(), b.length());
			if (diff == 0) return a.compareTo(b);
			return diff;
		});
		
		for (int i = 0; i < N ;i++) set.add(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (String str : set) sb.append(str).append("\n");
		System.out.println(sb);

	}
}
