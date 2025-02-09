import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //5이상 1000개 미만
		
		TreeSet<Integer> set = new TreeSet<>(); //Set인데 크기 순 정렬 & 순서o
		int[] arr = new int[N];
		for (int i = 0 ; i < N ;i++) {
			int n = Integer.parseInt(br.readLine());
			set.add(n);
			arr[i] = n;
		}
		Arrays.sort(arr);
		
		
		int maxNum = 0;
		while(true) {
			maxNum = set.last();
			set.remove(maxNum);
//			int loIdx = 0; int hiIdx = set.size()-1;
			boolean flag = false;
			for (int loIdx = 0 ; loIdx < set.size() ; loIdx++) {
				for (int hiIdx = set.size()-1; hiIdx >= loIdx; hiIdx--) {
					int sum = arr[loIdx] + arr[hiIdx];
					if (sum > maxNum ) continue; // hiIdx--;하기
					int find = maxNum - sum;
					if (set.contains(find)) {
						flag = true;
						break; //maxNum 확정
					}
				}
			}
			
			if (flag) break;
			//못찾았으면 maxNum다시 갱신해서
		}

		System.out.println(maxNum);
	}

}
