import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14719 _ 빗 물 
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		if (W <= 2 ) {
			System.out.println(0);
			return; 
		}
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[W];
		for (int i = 0 ; i < W ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// 가장 왼쪽 제일 높은 것 업데이트
		int startHeight = arr[0]; //첫번째 높이
		int startIdx = 0; 
		int beforeIdx = 1; // 바로 다음 
		for (int i = 1; i <W ;i++) {
			if (startHeight > arr[i]) {
				break; // 작은거 나타나면 바로 break 
			} else {
				startHeight = arr[i];
				startIdx = i; 
				beforeIdx = i+1;
			}
		}
		
		if (startIdx == W-1) {
			System.out.println(0);
			return; 
		}
		//////////////
		
		
		int sum = 0 ; // 정답 
		
		int standardHeight = startHeight; // 기준 키 
		while(standardHeight > 0) { // 0이되면 탈출 
			for(int i = startIdx+1; i < W ; i++ ) {
				int h = arr[i] ; 
				if ( standardHeight <= h) { // ==
//					sum += (i - startIdx - 1) * standardHeight;
					for (int j = startIdx+1; j < i; j++) {
						sum += (standardHeight - arr[j]);
					}
//					System.out.println(i + " "+ startIdx +" / " + standardHeight);
					// 변수 업데이트
					standardHeight = h+1; //아래에서 shandardHeight--걸리기 때문에 +1한 값 저장하기 
					startIdx = i; 
					break; // for 문 break
				}
			} //for문 끝
			standardHeight--;
		}
		
		System.out.println(sum);
		
	}

}
