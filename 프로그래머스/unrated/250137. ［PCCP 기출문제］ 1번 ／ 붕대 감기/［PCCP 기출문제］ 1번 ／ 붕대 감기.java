import java.util.*;
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int ct = 0; // 시전 시간
        int rps = bandage[1]; // 초당 회복량
        int ara = bandage[2]; // 추가 회복량
        int time = 0; // 시간
        int last = attacks[attacks.length-1][0]; //마지막 공격시간
        int ch = health; // 현재 체력
        
        List <int[]> list = new ArrayList();
        
        for(int i=0; i<attacks.length; i++){
            list.add(attacks[i]);
        }
        
        while(true){
            int at = list.get(0)[0];
            int damage = list.get(0)[1];
            time++;
            ct++;
            
            if(time == at){
                ch -= damage;
                if(ch <= 0){
                    ch = -1;
                    break;
                }
                ch -=rps;
                ct = 0;
                list.remove(0);
            }
            
            
            if(ct == bandage[0]){
                ch += ara;
                ct = 0;
            }
                
            ch += rps;
                   
            if(ch>health){
                ch = health;
            }
            
            if(time == last){
                break;
            }
               
        }
        
        
        return ch;
    }
}