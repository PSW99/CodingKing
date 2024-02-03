class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder("");
        
        for(int i = food.length - 1; i >= 1; i--){
            int n = food[i] / 2 * 2 ;
            int index = i;
            for(int j = 1; j<= n / 2; j++){
                sb.insert(0,index);
            }
        }
        
        String A = sb.toString();
        sb.reverse();
        sb.insert(0,"0");
        String B = sb.toString();
        String answer = A.concat(B);
        
        
        
        return answer;
    }
}