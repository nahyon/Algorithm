import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    static class Info implements Comparable<Info> {
        int m, p;
        
        public Info(int m, int p) {
            this.m = m;
            this.p = p;
        }

        @Override
        public int compareTo(Info o) {
            return this.m - o.m; // 무게 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수

        // 보석 리스트 (무게 오름차순 정렬)
        List<Info> diamonds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 무게
            int p = Integer.parseInt(st.nextToken()); // 가격
            diamonds.add(new Info(m, p));
        }
        Collections.sort(diamonds); // 보석을 무게 오름차순 정렬

        // 가방 리스트 (무게 오름차순 정렬)
        List<Integer> bags = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags); // 가방을 무게 오름차순 정렬

        long maxProfit = 0;
        PriorityQueue<Integer> availableDiamonds = new PriorityQueue<>(Collections.reverseOrder()); // 가격 내림차순
        int idx = 0; // 보석 리스트 인덱스

        // 가방을 하나씩 확인하며 보석 선택
        for (int w : bags) {
            // 현재 가방(w)에 담을 수 있는 보석을 모두 `PriorityQueue`에 추가
            while (idx < N && diamonds.get(idx).m <= w) {
                availableDiamonds.offer(diamonds.get(idx).p);
                idx++;
            }

            // 가장 비싼 보석을 선택
            if (!availableDiamonds.isEmpty()) {
                maxProfit += availableDiamonds.poll();
            }
        }

        System.out.println(maxProfit);
    }
}