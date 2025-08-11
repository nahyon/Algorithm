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
        HashMap<String, TreeSet<Song>> genreSongMap = new HashMap<>();
        
        // 장르(String) , 횟수(Integer) 합
        HashMap<String, Integer> genreCnt = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreSongMap.putIfAbsent(genres[i], new TreeSet<>()); // 장르 : treeset 초기화
            genreSongMap.get(genres[i]).add(new Song(i, plays[i])); // 장르.add(Song)
            
            genreCnt.put(genres[i], genreCnt.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<Map.Entry<String, Integer>> sortedGenreCnt = new ArrayList<>(genreCnt.entrySet());
        sortedGenreCnt.sort((v1, v2) -> Integer.compare(v2.getValue(), v1.getValue())); // 내림차순 정렬
        
        int[] answer = new int[genres.length]; 
        int idx = 0;
        // 1. 속한 노래가 많이 재생된 장르 순서대로    
        for (Map.Entry<String, Integer> genreInfo : sortedGenreCnt) {
            String genre = genreInfo.getKey() ; // key값 = genre
            // 2. 장르 내에서 가장 많이 재생된 노래 & 3. 횟수 같으면 노래번호 오름차순
            // 장르 하나 당 최대 두 곡
            TreeSet<Song> songs = genreSongMap.get(genre);
            int i = 0;
            for (Song song : songs) {
                answer[idx++] = song.num;
                if (++i == 2) break; //2개
            }
        }
        answer = Arrays.copyOfRange(answer, 0, idx);
        return answer;

    }
}