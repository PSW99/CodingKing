import java.util.*;
class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        //score배열 리스트로 변환
        List<Integer> list = new ArrayList<>(score.length);
        for(int n : score){
            list.add(n);
        }
        
        //리스트 소팅
        Collections.sort(list);
        
        //System.out.println(list);
        while(list.size() % m != 0){
            int index = 0;
            list.remove(index);
        }
        
        for(int i = 0; i<list.size(); i += m){
            answer += list.get(i) * m * 1;
        }
        
        //System.out.println(list);
    
        
        return answer;
    }
}