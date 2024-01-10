import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        int xCount = 0;
        int notX = 0;
        
        List <Character> list = new ArrayList<>();
        
        for(int i = 0; i<s.length(); i++){
            list.add(s.charAt(i));
        }
        
        for(int i = 0; i<list.size(); i++){
            char x = list.get(i);
            System.out.println(x);
            for(int j = i; j<list.size(); j++){
                if(x == list.get(j)){
                    xCount += 1;
                }else{
                    notX += 1;
                }
                if( xCount == notX ){
                    answer += 1;
                    xCount = 0;
                    notX = 0;
                    i = j;
                    break;
                }else if(xCount != notX && i == s.length() - 1){
                    answer += 1;
                }
            }
            
        }
        
        //System.out.println(list);
        
        return answer;
    }
}