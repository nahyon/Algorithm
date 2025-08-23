import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 뱍쥰 10814 _ 나이순 정렬 
public class Main {
	static class Member implements Comparable<Member> {
		int age; String name; int n;
		public Member(int age, String name, int n) {
			this.age = age; this.name = name; this.n = n;
		}
		@Override 
		public int compareTo(Member o) {
			if (this.age == o.age) return this.n - o.n;
			return this.age - o.age;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Member[] members = new Member[N];
		for (int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			members[i] = new Member(age, name, i);
		}
		
		Arrays.sort(members);

		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < N ; i++) {
			sb.append(members[i].age).append(" ").append(members[i].name).append("\n");
		}
		System.out.println(sb);
		

	}
}
