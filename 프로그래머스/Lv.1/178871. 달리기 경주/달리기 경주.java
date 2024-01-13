import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        HashMap <String,Integer> call = new HashMap<>(players.length);
        
        for(int i = 0 ; i<players.length; i++){
            call.put(players[i],i);
        }
        
        for(String n:callings){
            int rank = call.get(n);
            String afterPlayer = players[rank - 1];
            
            players[rank - 1] = n;
            players[rank] = afterPlayer;
            
            call.put(n,rank - 1);
            call.put(afterPlayer,rank);
        }
        
    
        return players;
    }
}