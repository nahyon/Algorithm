import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
	
		int[] arr = new int[K];
		
	  	// 구구단값을 배열에 저장
        for (int i = 1; i <= K; i++) {
            arr[i-1] = N * i;
        }

        int[] arrReverse = new int[K];

        for (int i = 1; i <= K; i++) {
            String s = String.valueOf(arr[i - 1]);// 저장된 구구단 값 String으로 변경
            char[] sChars = new char[s.length()];	// 한 글자씩 떼 내서 역순으로 재 배열 해주기 위해 sChars[] 선언

            int index = 0;

            for (int j = s.length() - 1; j >= 0; j--) {
                sChars[index] = s.charAt(j); // sChars[]에 구구단 값 뒤에서 부터 추출하여 한 글자씩 저장
                index++;
            }

            String reverseNumString = "";	// sChars[]에 저장된 값 하나의 문자열로 합쳐주기 위해 String 변수 선언

            for (int j = 0; j < sChars.length; j++) {
                reverseNumString += sChars[j];
            }
            arrReverse[i - 1] = Integer.parseInt(reverseNumString);
        }

        int max = 0;

        for (int i = 0; i < arrReverse.length; i++) {	// 값 비교 통해서 최대값 출력
            if (max < arrReverse[i]) {
                max = arrReverse[i];
            }
        }
        System.out.println(max);
	}

}
