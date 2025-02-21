import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

// 백준 1477 _ 휴게소 세우기 
public class Main {
	static int[] stops;
	static boolean[] street;
	static int ans = Integer.MAX_VALUE; // 정답최솟값 
	static int N, M, L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken()); // 현재 휴게소 개수 
		M = Integer.parseInt(st.nextToken()); // 더 지으려는 휴게소 개수 
		L = Integer.parseInt(st.nextToken()); // 고속도로 길이 
				
		st = new StringTokenizer(br.readLine());
		stops = new int[N+2];
		if (N!=0) {
			for (int i = 0 ; i < N ; i++) {
				int n = Integer.parseInt(st.nextToken());
//				stops.add(n);
				stops[i] = n;
			}
		}
		stops[N] = 0; // 처음 
		stops[N+1] = L; //마지막(L)에 세워져있다고 지정해두기 
		Arrays.sort(stops);
		
		int left = 1 ; int right = L;
		while (left <= right) {
			int mid = (left + right) / 2 ;
			
			if (getDistance(mid)) { //더 줄이기 
				ans = Math.min(mid,  ans);
				right = mid-1;
			} else left = mid+1;
		}
		System.out.println(ans);
	}
	
	// maxDist 안에 M개 안에 가능한지 여부 
	static boolean getDistance(int maxDist) {
		int cnt = 0 ; // 세우는 휴게소 수 
		for (int i = 0 ; i < N+1; i++ ) {
			int a = stops[i];
			int b = stops[i+1];
			cnt+= (b-a-1) / (maxDist) ;
		}
		return cnt <= M ; //M이하로 세우는거 성공하면 true
	}
}
