import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        
        Arrays.sort(targets, (o1, o2) ->{
            return o1[1] - o2[1];
        });
    
        int missileLoc = targets[0][1];
        for(int i = 1; i<targets.length; i++){          
            if(missileLoc <= targets[i][0]){
                answer++;
                missileLoc = targets[i][1];
            }
            
        }
        
        return answer;
    }
}