import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int size = sizes.length;
        
        List<Integer> width = new ArrayList<>();
        List<Integer> length = new ArrayList<>();
        
        for(int i = 0; i<size; i++){
            if(sizes[i][0] >=sizes[i][1]){
                width.add(sizes[i][0]);
                length.add(sizes[i][1]);
            }else{
                width.add(sizes[i][1]);
                length.add(sizes[i][0]);
            }
        }
        
        Collections.sort(width,Collections.reverseOrder());
        Collections.sort(length,Collections.reverseOrder());
        
        answer = width.get(0) * length.get(0);
        
        return answer;
    }
}