import java.util.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        HashMap <String,Integer> map = new HashMap<>(name.length);
        
        for(int i = 0; i<name.length; i++){
            map.put(name[i],yearning[i]);
        }
        
        for(int j = 0; j<photo.length; j++){
            int result = 0;
            for(int k = 0; k<photo[j].length; k++){
                result += map.getOrDefault(photo[j][k],0);
            }
            answer [j] = result;
        }
        
        System.out.println(map);
        
        return answer;
    }
}