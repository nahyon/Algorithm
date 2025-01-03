import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2961 _ 도영이가 만든 맛있는 음식
public class Main {
	static int N;
	static int S=0, B=0, ans=Integer.MAX_VALUE;
	static int[][] ingredients;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //재료개수
		
		StringTokenizer st = null;
		ingredients = new int[N][2]; //신맛(S), 쓴맛(B)
		// isSelected = new boolean[N];
		
		// 재료 입력받기
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());	
		}
		
		//
		cook(0, 1, 0);
		
		System.out.println(ans);
	
	}
	
	
	static void cook(int depth, int sumS, int sumB) {
		// 종료조건 : 모든 재료 고려 완료
		if (depth == N) {
			if (sumB == 0) return ; //어떤 재료도 안넣은 경우는 없음
			if (ans > Math.abs(sumS - sumB))	ans = Math.abs(sumS - sumB);
			return;
		}
		
//		isSelected[depth] = true;
		cook(depth+1, sumS * ingredients[depth][0], sumB + ingredients[depth][1]);
//		isSelected[depth] = false; 
		cook(depth+1, sumS, sumB);
		
	}
	
	

}
