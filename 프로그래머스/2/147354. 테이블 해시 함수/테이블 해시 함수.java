import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        int s = 0;
    
        List <Integer> list = new ArrayList<>();
        
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) {
                return o2[0] - o1[0];
            }
            return o1[col - 1] - o2[col - 1];
        });   
        
        for(int i = row_begin - 1; i<=row_end - 1; i++){
            for(int j = 0; j<data[0].length; j++){
                s += data[i][j] % (i + 1);
            }
            list.add(s);
            s = 0;
        }
        
        for(Integer n : list){
            answer = answer ^ n;  
        }
      
        return answer;
    }
}