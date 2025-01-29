import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1421 _ 나무꾼 이다솜
public class Main {
	static int N,C,W;
//	static int costMax;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 나무 개수
		C = Integer.parseInt(st.nextToken()); // 자를 때 드는 비용
		W = Integer.parseInt(st.nextToken()); // 한 단위 가격
		// 통일된 길이 K * 나무개수M(kTreecNT) * W - 통일된 길이로 자른 나무개수mm(자르는횟수)(cutCnt) * C 가 최대가되도록
		
		// 나무N개
		int maxHeight = 0;
		int[] trees = new int[N];
		for (int i = 0 ; i  < N ;i++) {
			trees[i] = Integer.parseInt(br.readLine());
			maxHeight = Math.max(trees[i], maxHeight);
		}
		
		long costMax = 0 ; //초기화
		
		for (int i = 1 ; i <= maxHeight ;i++) {
			costMax = Math.max(calculate(trees, i), costMax);
		}
		
		System.out.println(costMax);
	}
	
	// 길이를 K로 통일시킬 때 비용 계산
	static long calculate(int[] trees, int K) {
//		int cutCnt = 0;
//		int kTreeCnt = 0;
		long money  = 0;
		for (int i = 0 ; i < N ;i++) {
			int cutCnt = 0;
			int kTreeCnt = 0;
			if (trees[i] < K) continue; // 길이 K못만드는 나무
			else if (trees[i] > K) {
				kTreeCnt += trees[i]/K; //길이 K나무개수
				if (trees[i]%K == 0 ) cutCnt += (trees[i]/K-1); //자르는 횟수
				else cutCnt += (trees[i]/K); //자르는 횟수
			}
			else kTreeCnt++; //길이 K나무 개수 ++
			
			if ( K*kTreeCnt*W - cutCnt*C < 0) continue;
			else money +=  (K*kTreeCnt*W - cutCnt*C);
		}
		// cutCnt <= kTreeCnt <= N
//		System.out.println(K+ " " +kTreeCnt + " " + cutCnt + " " + ( K*kTreeCnt*W - cutCnt*C));
		return money;
		
	}

}
