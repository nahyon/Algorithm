import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

// 백준 11652 _ 카드
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
	
		HashMap<Long, Integer> map = new HashMap<>();
		for (int i = 0 ; i < N ; i++) {
			long num = Long.parseLong(br.readLine());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		// value 기준으로 정렬하기
		// 가장 많이 가진거만 알면되니까 순회하면서 찾기
		long ans = 0;
		int cnt = 0;
//		Set<Entry<Long, Integer>> entrySet = map.entrySet();
		for (Entry<Long, Integer> entrySet : map.entrySet()) {
			long key = entrySet.getKey();
			int value = entrySet.getValue();
			if (cnt == value && key < ans) ans = key;
			else if (cnt < value ) {
				ans = key;
				cnt = value;
			}
		}
		System.out.println(ans);	
	}
}
