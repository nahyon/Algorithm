import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1717 _ 집합의 표현
// Union-find
public class Main {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 0, 1, 2, 3, .. ,N
		int M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for (int i = 0 ; i <= N ; i++) {
			parent[i] = i; // 자기자신으로 초기화
		}
		
		for (int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
		
			if (a == 0) { // 합집합 
                if ((b==c) || (find(b)==find(c))) continue;
				union(b, c);
			} else {
				if (find(b)== find(c)) sb.append("yes").append("\n");
				else sb.append("no").append("\n");
			}
		}
		System.out.println(sb);

	}
	static int find(int x) {
		if (parent[x] == x) return x;
		
		parent[x] = find(parent[x]);
		return parent[x];
	}
	static void union (int a, int b) {
		a = find(a);
		b = find(b);
		
		parent[a] = b;
	}
}
