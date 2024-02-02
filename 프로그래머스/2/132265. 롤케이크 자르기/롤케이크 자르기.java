import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Set<Integer> rSet = new HashSet<>();
        HashMap<Integer,Integer> lMap = new HashMap<>();
        
        for(Integer n :topping){
            if(!lMap.containsKey(n)){
                lMap.put(n,1);
            }else{
                lMap.put(n, lMap.get(n) + 1);
            }
        }
        
        for(Integer n : topping){
            rSet.add(n);
            if(lMap.containsKey(n)){
                int a= lMap.get(n) - 1;
                lMap.put(n, a);
                if (a == 0) lMap.remove(n);
                if(rSet.size() == lMap.size()){
                    answer ++;
                }
            }
        }
        
        //System.out.println(lMap);
        
        /*for(int i = 0; i<topping.length; i++){
            for(int j = 0; j<=i; j++){
                rSet.add(topping[j]);
            }
            for(int j = 1+i; j<topping.length; j++){
                lSet.add(topping[j]);
            }
            if(rSet.size() == lSet.size()){
                //System.out.println("rSet" + rSet);
                //System.out.println("lSet" + lSet);
                answer ++;
            }
            rSet.clear();
            lSet.clear();
        }*/
        
        return answer;
    }
}
