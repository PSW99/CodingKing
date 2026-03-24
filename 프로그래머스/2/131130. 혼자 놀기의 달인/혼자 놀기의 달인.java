import java.util.*;

class Solution {
    static boolean[] visited;
    
    public int solution(int[] cards) {
        int answer = 0;
        visited = new boolean[cards.length];
        List<Integer> list = new ArrayList<>();
            
        for(int i = 0; i < cards.length; i++){
            if(visited[i]){
                continue;
            }
            int cnt = 1;
            visited[i] = true;
            int index = i;
            
            while(true){
                int num = cards[index];
                index = num - 1;
                
                if(visited[index]){
                    break;
                }
                cnt++;
                visited[index] = true;
            }
            list.add(cnt);
            
        }
        
        list.sort(Comparator.reverseOrder());
        
        if(list.size() == 0 || list.size() == 1){
            answer = 0;
        } else{
            answer = list.get(0) * list.get(1);
        }
        
        return answer;
    }
}