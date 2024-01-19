import java.util.*;
class Solution {
    public int[] solution(String[] park, String[] routes) {
        int dx = 0;
        int dy = 0;
        
        char[][] arr = new char[park.length][park[0].length()];
        
        
        for(int i = 0; i<arr.length; i++){
            arr[i] = park[i].toCharArray();
            if(park[i].contains("S")){
                dy = i;
                dx = park[i].indexOf("S");
            }
        }
        
        for(String n : routes){
            String way = n.split(" ")[0];
            int len = Integer.parseInt(n.split(" ")[1]);
            
            int nx = dx;
            int ny = dy;
            
            for(int i = 0; i < len; i++){
                if(way.equals("E")){
                    nx++;
                }
                if(way.equals("W")){
                    nx--;
                }
                if(way.equals("S")){
                    ny++;
                }
                if(way.equals("N")){
                    ny--;
                }
                if(nx >=0 && ny >=0 && ny < arr.length && nx < arr[0].length){
                    if(arr[ny][nx] == 'X'){
                        break;
                    }
                    // 범위내 & 장애물 x
                    if(i == len-1){
                        dx = nx;
                        dy = ny;
                    }
                }
            }    
        }
        
        int[] answer = {dy,dx};
        return answer;
    }
}