import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        int value;
        
        HashMap<String,Integer> map = new HashMap<>();
        
        for(int i = 0; i <clothes.length; i++){
            if(!map.containsKey(clothes[i][1])){
                map.put(clothes[i][1],1);
            }else{
                value = map.get(clothes[i][1]);
                map.replace(clothes[i][1],value+1);
            }       
        }
        
        answer = getAnswer(map);
        
        return answer - 1;
    }

    private static int getAnswer(Map<String, Integer> map) {
        int answer = 1;
        for (String s : map.keySet()) {
            // 착용안하는 경우의 수 = +1
            answer *= (map.get(s)+1);
        }
        return answer;
    }
    
}