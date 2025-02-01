import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

// 백준 13414 _ 수강신청
// LinkedHashSetTest : 순서있는 HashSet
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int K = Integer.parseInt(input[0]) ; // 수강가능인원 
		int L = Integer.parseInt(input[1]) ; // 대기인원길이
		
		//Integer x -> String으로 변경. (맨앞자리 0으로시작가능)
		HashSet<String> waitingList = new LinkedHashSet<>();
		for (int i = 0 ; i < L ; i++) {
			String num = br.readLine();
			if (waitingList.contains(num)) {
				waitingList.remove(num);
				waitingList.add(num);
				continue;
			}
			waitingList.add(num);
		}
		
		StringBuilder sb = new StringBuilder();
		Iterator<String> iter = waitingList.iterator();
	     
		int cnt = 0;
	    while(iter.hasNext()) {
	    	sb.append(iter.next()).append("\n");
	    	if (++cnt == K) break;
		}

		System.out.println(sb);
	}

}
