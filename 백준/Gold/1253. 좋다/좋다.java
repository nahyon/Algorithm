import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1253 _ 좋다
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		
		int num, cnt = 0;
		int left, right;
		for (int i = 0 ; i < N ; i++) {
			num = arr[i] ;
			
			left = 0; right = N-1;
			while (left < right) {
				if (left == i) {
					left++; continue;
				}
				else if (right == i) {
					right--; continue;
				}
				if (num == arr[left] + arr[right]) {
					cnt++; 
					break; //이 수는 '좋은 수' 더 이상 볼 필요x
				} else if (num < arr[left] + arr[right]) right--;
				else left++;
			}		
		}
		
		System.out.println(cnt);
	}

}
