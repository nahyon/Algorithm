

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

//백준 1655 _ 가운데를 말해요
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //N개의 숫자
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //최댓값poll
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(); //최솟값poll
		// maxHeap [mid] minHeap
		
		int mid = Integer.parseInt(br.readLine()); //초기값 :맨 처음 숫
		sb.append(mid).append("\n");
		for (int i = 1 ; i < N ; i++) {  //N-1개의 숫자
			//i홀수 : 홀수인상태에서 짝수가 되는 경우 / 짝수 : 짝수인 상태에서 숫자 들어온 경
			int num = Integer.parseInt(br.readLine()); 
			if (num < mid) { // 현재 mid 보다 작은 수
				maxHeap.offer(num);
				if (i%2 == 1) {
					minHeap.offer(mid);
					mid = maxHeap.poll();
				}
			}else if (mid < num ) { // 현재 mid보다 큰
				minHeap.offer(num);
			} else {
				if (i%2 == 1) minHeap.offer(num);
				else {
					if (minHeap.size() < maxHeap.size()) minHeap.offer(num);
					else maxHeap.offer(num);
				}
			}
			
			// i%2 == 0 일 때 mid값 갱신과정 추가
			if (i%2 == 0) {
				if (maxHeap.size() < minHeap.size()) {
					maxHeap.offer(mid);
					mid = minHeap.poll();
				} else if (maxHeap.size() > minHeap.size()) {
					minHeap.offer(mid);
					mid = maxHeap.poll();
				}
			}
			
			sb.append(mid).append("\n"); // 중앙값 mid 프린트
		}
		
		System.out.println(sb);
	}
}
