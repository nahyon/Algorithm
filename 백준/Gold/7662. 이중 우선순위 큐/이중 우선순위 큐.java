import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 백준 7662 _ 이중 우선순위 큐
// TreeMap(숫자 여러개 중복가능이라 set안됨)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0 ; t < T ; t++) {
			int N = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>(); //숫자, 나온개수
			for (int i = 0; i < N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int n = Integer.parseInt(st.nextToken());
				if (c == 'I') map.put(n, map.getOrDefault(n, 0)+1);
				else { // 'D'
					if (map.size() == 0) continue;
					int key;
					if (n == -1) { //최솟값 삭제
						key= map.firstKey();
					} else { //최댓값 삭제
						key = map.lastKey();
					}
					if (map.get(key) == 1) map.remove(key);
					else map.put(key,  map.get(key)-1);
				}
			}
			if (map.size() == 0) sb.append("EMPTY").append("\n");
			else sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
		}
		System.out.println(sb);
	
	}

}
