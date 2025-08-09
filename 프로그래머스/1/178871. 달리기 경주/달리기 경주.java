import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> playerToRank = new HashMap<>();
        String[] rankToPlayer = new String[players.length]; // 인덱스0 = 등수1
        
        // 처음 {player : 등수} 초기화
        // 등수 0부터 시작한다고 생각
        for (int i = 0 ; i < players.length; i++) {
            rankToPlayer[i] =  players[i]; // 인덱스i = i등 = players[i] 
            playerToRank.put(players[i], i);
        }

        for (String p : callings) { // 현재 선수 p
            int pRank = playerToRank.get(p); // 현재 선수 p 등수
            
            // 앞서있던 선수 p2 : 등수 pRank-1
            String p2 = rankToPlayer[pRank-1] ; // 앞서있던 선수
            
            // p와 p2 등수 바꾸기
            rankToPlayer[pRank-1] = p;
            rankToPlayer[pRank] = p2; 
            
            playerToRank.put(p, pRank-1);
            playerToRank.put(p2, pRank);
        }

        return rankToPlayer;
    }
}