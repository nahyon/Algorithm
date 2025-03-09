import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 11505 _ 구간곱구하기 
// 세그먼트 트리 
public class Main {
	static int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 수의 개수 
		int M = Integer.parseInt(st.nextToken()); // 수 변경 일어나는 횟수 
		int K = Integer.parseInt(st.nextToken()); // 구간의 곱을 구하는 횟수
		
		// 트리 정의
		int treeHeight = 0; 
		int length = N; //N개의 수
		// 2^treeHeight >= N 만족하는 treeHeight구하기
		while (length != 0) {
			length /= 2;
			treeHeight++;
		}
		int treeSize = (int) Math.pow(2, treeHeight+1); //2^treeHeight * 2
		
		long[] tree = new long[treeSize+1]; 
		Arrays.fill(tree, 1); // 곱을 위해 1로 채우기 
		
		// N개의 수 입력받기 
		int startIdx = (int) Math.pow(2, treeHeight); // 2^treeHeight
		for (int i = 0 ; i < N ;i++) {
			tree[startIdx + i] = Integer.parseInt(br.readLine());
		}
		
		// 트리 채우기 
		for (int i = treeSize -1 ; i > 1 ; i--) {
			tree[i/2] = (tree[i/2] * tree[i] % MOD);
		}
		
		for (int i = 0 ; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if ( a == 1 ) { // b번째 수를 c로 변경 
				int idx = startIdx+b-1;
//				long diff = c/tree[idx]; // (변경값 / 기존 값)
				update(tree, idx, c);
			} else { 
				long mul = mul(tree, startIdx+b-1,(startIdx+c-1));
				sb.append(mul).append("\n");
			}
		}
		System.out.println(sb);
	}
	/*
	static void changeVal(long[] tree, int idx, long changeVal) {
		// 기존 값 
		long origin = tree[idx];
		// 저장된 값 / 기존 값 * changeVal해야함 
		// if 기존 값 == 0 인 경우, 저장된 값 * changeVal
		if (origin==0) {
			origin = 1; 
			tree[idx] = changeVal; // 자기자신은 바꿔두고 시작 
			idx/=2;
		}
		while (idx > 0) {
			tree[idx] = (tree[idx]/origin*changeVal % 1000000007);
			idx/=2;
		}   
	}
	 */
    static void update(long[] tree, int idx, long newValue) {
        tree[idx] = newValue % MOD; // 값 변경

        while (idx > 1) {
        	idx /= 2;
            tree[idx] = tree[idx * 2] % MOD * tree[idx * 2 + 1] % MOD; // 부모 노드 업데이트
        }
    }
	static long mul(long[] tree, int startIdx, int endIdx) {
		long ret = 1;
		while (startIdx <= endIdx) {
			if (startIdx%2 == 1) {
				ret = ret*tree[startIdx] % MOD;
				startIdx++;
			}
			if (endIdx %2 == 0) {
				ret = ret*tree[endIdx] % MOD;
				endIdx--;
			}
			if (ret == 0) break; //더이상 볼 필요 없음 곱해도 계속 0나옴 
			startIdx /=2 ;
			endIdx /=2 ;
		}
		return ret;
	}

}
