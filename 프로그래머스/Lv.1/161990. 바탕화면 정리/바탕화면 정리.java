import java.util.*;
class Solution {
    public int[] solution(String[] wallpaper) {
        
        char[][] arr = new char[wallpaper.length][wallpaper[0].length()];
    
        List<Integer> iNum = new ArrayList<>();
        List<Integer> jNum = new ArrayList<>();
        
        //wallpaper를 2차원 char형 배열로 바꾸기
        for(int i = 0; i<wallpaper.length; i++){
            arr[i] = wallpaper[i].toCharArray();
            //System.out.println(arr[i]);
        }
        
        // #을만나면 인덱스 i,j값을 리스트에 보관
        for(int i = 0; i<wallpaper.length; i++){
            for(int j = 0; j<wallpaper[0].length(); j++){
                if(arr[i][j] == '#'){
                    iNum.add(i);
                    jNum.add(j);
                }
            }
        }
        
        //리스트를 정렬
        Collections.sort(iNum);
        Collections.sort(jNum);
        
        
        int startX = iNum.get(0);
        int startY = jNum.get(0);
        int endX = iNum.get(iNum.size() - 1) + 1;
        int endY = jNum.get(iNum.size() - 1) + 1;
        
        //System.out.println(iNum);
        //System.out.println(jNum);
        
        int[] answer = {startX ,startY,endX,endY};
        
        
        return answer;
    }
}