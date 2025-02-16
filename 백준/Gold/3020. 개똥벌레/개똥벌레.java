import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3020 _ 개똥벌레
// IMOS
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //동굴길이 N. 항상짝수 / 2이상 20만이하
		int H = Integer.parseInt(st.nextToken()); //동굴높이H. 2이상 50만이하
		
		
		// N개의 석순->종유석->석순->... 순서로 높이나타남
		int[] cntImos = new int[H+2]; // 인덱스는 높이. 1m ~ Hm까지 사용함 / 계산을 위해 H+1도 사용함
		
		for (int i = 0 ; i < N ; i++) {
			int length = Integer.parseInt(br.readLine());
			if (i%2==0) { // 짝수일 때 석순
				cntImos[1]++; // (시작점) 지점이 +1
				cntImos[length+1]--; // 끝점 : (1+length-1)까지 자라있음. (끝점+1) 지점이 -1
			} else { // 종유석 : 높이 H부터 H-length만큼 자라남
				cntImos[H+1]--; //시작점
				cntImos[H-length+1]++; //끝점 : (H-length+1)까지 자라있음. (끝점-1)지점이 -1
			}
		}
		
//		for (int i = 1 ; i <= H+1; i++) {
//			System.out.print(cntImos[i]+ " ");
//		}
//		System.out.println();

		
		// IMOS배열 누적합
		int now = 0;
		for (int i = 1 ; i <= H+1; i++) {
			cntImos[i] = cntImos[i-1] + cntImos[i];
		}
		
//		for (int i = 1 ; i <= H+1; i++) {
//			System.out.print(cntImos[i]+ " ");
//		}
//		System.out.println();
		
		
		int minCnt = Integer.MAX_VALUE; //그때 장애물개수 cntImos[i] <- 이거인 높이 몇개있냐 
		int hCnt = Integer.MAX_VALUE; //높이
		for (int i = 1 ; i <= H ; i++) {
			if (cntImos[i] < minCnt) { //새로 업데이트
				hCnt=1;//초기화
				minCnt = cntImos[i];
			} else if (cntImos[i] == minCnt) hCnt++;
		}
		System.out.println(minCnt + " " + hCnt);

	}
}
