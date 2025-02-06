import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 20920 _ 영단어 암기는 괴로워
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); // 외울 단어 길이의 기준
		
		HashMap<String, Integer> m = new HashMap<>();
		
		for (int i = 0 ; i < N ;i++){
			String s = br.readLine();
			if (s.length() < M ) continue;
			m.put(s, m.getOrDefault(s, 0) + 1);
		}
		
		
		PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int freqCompare = m.get(b) - m.get(a); // 빈도수 높은 순
            
            if (freqCompare != 0) return freqCompare;
            
            int lengthCompare = b.length() - a.length(); // 길이 긴 순
            if (lengthCompare != 0) return lengthCompare;
            
            return a.compareTo(b); // 알파벳 사전 순
		});
		
		pq.addAll(m.keySet());
		
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) sb.append(pq.poll()).append("\n");
		
		System.out.println(sb);


	}
}
