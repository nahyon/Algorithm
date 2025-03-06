import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 백준 19583 _ 싸이버개강총회
public class Main {
	static StringTokenizer st = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {	
		st = new StringTokenizer(br.readLine());
		
		String startTime = st.nextToken();
		String endTime = st.nextToken(); // 총회 끝
		String endTime2 = st.nextToken(); //스트리밍 끝 

		/*
		 * startTime "이하"에 들어온 경우 : 입장 
		 * endTime"이상" endTime2"이하" : 퇴장
		 */
		
		
		//	출석한 애들 <닉네임> 
		HashSet<String> attendees = new HashSet<>();
		
//		int cnt = solve1(attendees, startTime, endTime, endTime2);
		int cnt = solve2(attendees, startTime, endTime, endTime2);
		
		System.out.println(cnt);
	}
	static int solve2(HashSet<String> attendees, String startTime, String endTime, String endTime2) throws IOException{
		int cnt = 0;
		String input;
		while ((input = br.readLine()) != null) { //입력 종료될 때까지 받음 (EOF감지)
			st = new StringTokenizer(input);
			if (!st.hasMoreTokens()) break; ////////
			
			String time = st.nextToken();
			String name = st.nextToken();
			
			// ~ 입장시간 : 출석체크
			if ( time.compareTo(startTime) <= 0 ) {
				attendees.add(name); // 동일한 사람 무시됨
				continue;
			}
			// 출석체크 끝난상태 -----------
			if (attendees.isEmpty()) break;
			
			// ~ 종료시간 직전 
			if ( time.compareTo(endTime) < 0) continue; // 입력 계속 받으러감 
			// 종료시간 ~ 스트리밍 끝나는 시간
			if (time.compareTo(endTime)>=0 && time.compareTo(endTime2)<=0) {
				if (attendees.contains(name)) {
					cnt++;
					attendees.remove(name); // 동일한 사람 또체크되는거 방지 
				}
			} else break; // 더 이상 입력받을 필요없음
		}
		
		return cnt;
	}
	static int solve1(HashSet<String> attendees, String startTime, String endTime, String endTime2) throws IOException{
		int cnt = 0;
		
		int startHour = Integer.parseInt(startTime.split(":")[0]);
		int startMinute = Integer.parseInt(startTime.split(":")[1]);
		
		int endHour = Integer.parseInt(endTime.split(":")[0]);
		int endMinute = Integer.parseInt(endTime.split(":")[1]);
		
        int endHour2 = Integer.parseInt(endTime2.split(":")[0]);
        int endMinute2 = Integer.parseInt(endTime2.split(":")[1]);
		// startTime이하까지 맵에 저장
		String input;
		while ((input = br.readLine()) != null) { //입력 종료될 때까지 받음 (EOF감지)
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
			} else break; // 더 이상 입력받을 필요없음
		}
		
		return cnt;
	}
}
