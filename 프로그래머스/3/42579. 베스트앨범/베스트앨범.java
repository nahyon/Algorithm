import java.io.*;
import java.util.*;

class Solution {
    static class Song implements Comparable<Song>{
        int num , plays ; 
        public Song(int num, int plays) {
            this.num = num; this.plays = plays;
        }
        @Override
        public int compareTo (Song o) {
            if (this.plays == o.plays) {
                return this.num - o.num; // 오름차순
            }
            return -(this.plays - o.plays); // 내림차순
            // 2. 장르 내에서 많이 재생된 노래 -> 내림차순
            // 3. 고유 번호가 낮은 노래 -> 오름차순
        }
    }
    
    // genres[i] : 노래i의 장르 / plays[i] : 노래i의 재생횟수
    // answer : 베스트앨범에 들어갈 노래번호 순서대로 리턴
    public int[] solution(String[] genres, int[] plays) {
        
        // 장르 : int[]{노래번호} 저장 / ex {genre : [2, 3, 4], genre : [1, 5]} 
        // 노래 번호는 많이 재생된 순서로 저장
        // 재생된 수 같으면 번호 작은 것부터
        // -> class 사용
        HashMap<String, PriorityQueue<Song>> genreSongMap = new HashMap<>();
        
        // 장르(String) , 횟수(Integer) 합
        HashMap<String, Integer> genreCnt = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreSongMap.putIfAbsent(genres[i], new PriorityQueue<>()); // 장르 : pq 초기화
            genreSongMap.get(genres[i]).add(new Song(i, plays[i])); // 장르.add(Song)
            
            genreCnt.put(genres[i], genreCnt.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        // 총 재생횟수 기준 내림차순으로 장르 정렬 (genreCnt맵) 
        // - key값들만 다 뽑기
        ArrayList<String> genreKeySet = new ArrayList<>(genreCnt.keySet());
        genreKeySet.sort((value1, value2) -> (genreCnt.get(value2) - genreCnt.get(value1)));
        //--------------------------------------
        
        int[] answer = new int[genres.length]; 
        int idx = 0;
        // 1. 속한 노래가 많이 재생된 장르 순서대로
        for (String genre : genreKeySet) { // 장르가 횟수 내림차순으로 나옴
            // String genre = genreCnt.get(key) ; 
            
            // 2. 장르 내에서 가장 많이 재생된 노래 & 3. 횟수 같으면 노래번호 오름차순
            // 장르 하나 당 최대 두 곡
            PriorityQueue<Song> pq = genreSongMap.get(genre);
            int i = 0;
            while (!pq.isEmpty()) {
                answer[idx++] = pq.poll().num;
                if (++i == 2) break; //2개
            }

        }
        answer = Arrays.copyOfRange(answer, 0, idx);
        return answer;
    }
}