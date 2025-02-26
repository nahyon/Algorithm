import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1138 _ 한 줄로 서기
// 그리디 
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //사람 수. 10 이하의 자연수
		// 키 :1부터 N까지
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] ans = new int[N+1];
		// 왼쪽에 자기보다 큰 사람 몇 명 있었는지 저장
		for (int i = 1 ; i <= N ; i++) {
			// 키 i인 애가 본인 보다 큰 사람이 왼쪽에 몇 명인지 
			int x = Integer.parseInt(st.nextToken());			

            int count = 0; // 현재 비어 있는 공간 개수
            for (int j = 1; j <= N; j++) {
                if (ans[j] == 0) { // 비어있는 자리 체크
                    if (count == x) { // 원하는 위치 찾으면 배치
                        ans[j] = i;
                        break;
                    }
                    count++;
                }
            }
		}
		
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1 ; i <= N ; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}

}
