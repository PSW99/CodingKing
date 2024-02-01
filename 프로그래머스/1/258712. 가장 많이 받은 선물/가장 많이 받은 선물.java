import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        HashMap<String,HashMap<String,Integer>> ex = new HashMap(); //주고 받은 선물표
        HashMap<String,Integer> gi = new HashMap(); //선물 지수표
        HashMap<String,Integer> count = new HashMap(); //선물 받은 개수
        
        for(int i = 0; i<friends.length; i++){
            gi.put(friends[i],0);
            count.put(friends[i],0);
            ex.put(friends[i],new HashMap<>());
        } //전체표 키값 초기화
        
        for(int i=0; i<friends.length; i++) {
            Map<String, Integer> takerInfo = ex.get(friends[i]);

            for(int j=0; j<friends.length; j++) {
                if(i == j) {
                    continue;
                }
                takerInfo.put(friends[j], 0);
            }
        } //주고받은 선풀표 value map 초기화
        
        for(int i = 0; i<gifts.length; i++){
            String[] gift = gifts[i].split(" ");
            
            gi.put(gift[0],gi.getOrDefault(gift[0],0) + 1);
            gi.put(gift[1],gi.getOrDefault(gift[1],0) - 1);
            
            HashMap <String,Integer> map = ex.get(gift[0]);
            map.put(gift[1],map.getOrDefault(gift[1],0) + 1);   
        } //선물표들 초기화
        
        for(int i=0; i < friends.length - 1; i++){
            String giver = friends[i];
            for(int j = i+1; j< friends.length; j++){
                String receiver = friends[j];
                if(ex.get(giver).get(receiver) > ex.get(receiver).get(giver)){
                    count.put(giver,count.getOrDefault(giver,0) + 1);
                }else if(ex.get(giver).get(receiver) < ex.get(receiver).get(giver)){
                    count.put(receiver,count.getOrDefault(receiver,0) + 1);
                }else{
                    if(gi.get(giver) > gi.get(receiver)){
                        count.put(giver,count.getOrDefault(giver,0) + 1);
                    }else if(gi.get(giver) < gi.get(receiver)){
                        count.put(receiver,count.getOrDefault(receiver,0) + 1);
                    }else{
                        
                    }
                }
                    
            }
        }
        
        int max = 0;
        for(int i = 0; i<count.size(); i++){
            max = Math.max(max,count.get(friends[i]));
        }
        /**System.out.println(gi);
        System.out.println(ex);
        System.out.println(count);**/
        return max;
    }
}