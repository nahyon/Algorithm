import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2283 _ 구간 자르기
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken()); // N개의 선분 (1이상 1000이하)
		int K = Integer.parseInt(st.nextToken()); // 길이의 총합 K (1이상 1억이하) 
	
		int[][] lineList = new int[N][2];
		int start=0, end=0;
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			// 0 이상 1,000,000 이하의 정수
			lineList[i][0] = Integer.parseInt(st.nextToken()); // 시작
			lineList[i][1] = Integer.parseInt(st.nextToken()); // 끝
			start = Math.min(start,  lineList[i][0]);
			end = Math.	max(end,  lineList[i][1]);
		}
		Arrays.sort(lineList, (o1, o2) -> {return o1[0] - o2[0]; }); //시작점 기준 오름차순 정렬
		
		int left,right;
		left = right = start;
		
		boolean flag = false;
		while (left<=right && right<=end) {
			// left이상 right이하 사이의 합 구하기
			
			int sum = 0;
			// 완탐으로 합 구하기
			for (int i = 0 ; i < N ; i++) {
				// 현재 line : (s, e) 
				// <-가 left~right사이 얼마나 들어오나
				int s = lineList[i][0];
				int e = lineList[i][1];
				
				if (right<=s || e<=left) continue; // 구간 밖
				
				sum += (Math.min(right, e) - Math.max(left,  s));
			}
			
			if (sum == K) {
				flag = true;
				break;
			}
			
			if (sum > K) left++; // 줄이기
			else right++; //늘리기
			
		}
		if (flag) System.out.println(left + " " + right);
		else System.out.println("0 0");
		
		
		
	}
}
