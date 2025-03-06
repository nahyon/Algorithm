import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 백준 19583 _ 싸이버개강총회
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String startTime = st.nextToken();
		int startHour = Integer.parseInt(startTime.split(":")[0]);
		int startMinute = Integer.parseInt(startTime.split(":")[1]);
		
		String endTime = st.nextToken(); // 총회 끝
		int endHour = Integer.parseInt(endTime.split(":")[0]);
		int endMinute = Integer.parseInt(endTime.split(":")[1]);
		
		String endTIme2 = st.nextToken(); //스트리밍 끝 
        int endHour2 = Integer.parseInt(endTIme2.split(":")[0]);
        int endMinute2 = Integer.parseInt(endTIme2.split(":")[1]);

		/*
		 * startTime "이하"에 들어온 경우 : 입장 
		 * endTime"이상" endTime2"이하" : 퇴장
		 */
		
		
        int cnt= 0;
		//	출석한 애들 <닉네임> 
		HashSet<String> attendees = new HashSet<>();
		
		// startTime이하까지 맵에 저장
		String input;
//		while ((input = br.readLine()) != null) { //입력 종료될 때까지 받음
		while (true) { // 무한 루프
            input = br.readLine();
            if (input == null) break; // EOF 감지
			st = new StringTokenizer(input);
			if (!st.hasMoreTokens()) break; ////////
			
			String time = st.nextToken();
			int hour = Integer.parseInt(time.split(":")[0]);
			int minute = Integer.parseInt(time.split(":")[1]);
			String name = st.nextToken();
			
			// ~ 입장시간 : 출석체크
			if (( (hour < startHour) || 
					(hour == startHour && minute <= startMinute))) {
				attendees.add(name); // 동일한 사람 무시됨
				continue;
			}
			// 출석체크 끝난상태 -----------
			if (attendees.isEmpty()) break;
			
			// ~ 종료시간 직전 
			if (( (hour < endHour) || 
					(hour == endHour && minute < endMinute))) continue;
			// 종료시간 ~ 스트리밍 끝나는 시간
			if (( (hour < endHour2) || 
					(hour == endHour2 && minute <= endMinute2))) {
				if (attendees.contains(name)) {
					cnt++;
					attendees.remove(name); // 동일한 사람 또체크되는거 방지 
				}
			}
		}
		
		
		System.out.println(cnt);
	}
}
