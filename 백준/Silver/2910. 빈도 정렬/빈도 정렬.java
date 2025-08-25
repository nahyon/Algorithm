import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

// 백준 2910 _ 빈도 정렬 
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 숫자 N개 (길이)  
		int C = Integer.parseInt(st.nextToken()); // 숫자는 모두 1이상 C 이하 
		
		// 1. 등장 빈도 내림차순 2. 먼저 입력된 것 (**) 
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(); 

		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N ;i++ ) {
			int n = Integer.parseInt(st.nextToken());
			map.put(n, map.getOrDefault(n, 0)+1); //  입력 순서대로 저장됨
		}
		
		List<Entry<Integer, Integer>> entrySetList = new ArrayList<>(map.entrySet());
		entrySetList.sort((a, b) -> b.getValue()- a.getValue());
		
		
		StringBuilder sb = new StringBuilder();
		for (Entry<Integer, Integer> entrySet : entrySetList) {
			for (int i = 0 ; i < entrySet.getValue(); i++) 
				sb.append(entrySet.getKey()).append(" ");
		}
		System.out.println(sb);

	}
}
