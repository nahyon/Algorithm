import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
		List<Entry<Long, Integer>> entrySetList = new ArrayList<>(map.entrySet());
		entrySetList.sort((a, b) -> {
			if (a.getValue().equals(b.getValue())) return Long.compare(a.getKey(), b.getKey());
			return Integer.compare(b.getValue(), a.getValue());
		});
		System.out.println(entrySetList.get(0).getKey());
		
	}
}
