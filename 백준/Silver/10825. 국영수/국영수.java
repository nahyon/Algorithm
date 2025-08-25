import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 10825 _ 국영수 
public class Main {
	static class Student implements Comparable<Student> {
		String name; int kor, eng, math ;
		public Student(String name, int kor, int eng, int math) {
			this.name = name; this.kor = kor; this.eng= eng; this.math = math;
		}
		@Override
		public int compareTo(Student o) {
//			국어 점수가 감소하는 순서로 - 내림차순 
//			국어 점수가 같으면 영어 점수가 증가하는 순서로 - 오름차순 
//			국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로 - 내림차순 
//			모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.)
			int koDiff = Integer.compare(o.kor, this.kor); // 내림차순 
			if (koDiff != 0) return koDiff;
			
			int enDiff = Integer.compare(this.eng, o.eng);
			if (enDiff != 0) return enDiff;
			
			int mathDiff = Integer.compare(o.math, this.math);
			if (mathDiff != 0 ) return mathDiff;
			
			return this.name.compareTo(o.name); // 사전순 
			
		}
		
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Student[] students = new Student[N];
		for (int i = 0 ; i < N ; i++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int kor = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			students[i] = new Student(name, kor, eng, math);
		}
		Arrays.sort(students);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < N ; i++) {
			sb.append(students[i].name).append("\n");
		}
		System.out.println(sb);
	

	}

}
