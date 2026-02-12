class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int n = players.length;
        
        int[] activeServers = new int[n]; 
        
        for (int i = 0; i < n; i++) {
            int required = players[i] / m;
            
            int current = activeServers[i];
            
            if (current < required) {
                int needed = required - current;
                answer += needed;
                
             
                for (int j = i; j < i + k; j++) {
                    if (j >= n) break;
                    activeServers[j] += needed;
                }
            }
        }
        
        return answer;
    }
}