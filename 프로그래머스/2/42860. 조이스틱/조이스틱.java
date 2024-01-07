class Solution {
    public int solution(String name) {
        int answer = 0;
        int index;
        int move = name.length() - 1;

        for(int i = 0; i < name.length(); i++){
            int x = name.charAt(i);
            int z = x-65;
            if(z > 13){
                z= 26 - z;
                answer += z;
            }else{answer += z;}
            
            index = i + 1;
            while(index < name.length() && name.charAt(index) == 'A'){
                index ++;
                System.out.println(index);
            }
            
            move = Math.min(move, i * 2 + name.length() - index);
            move = Math.min(move, (name.length() - index) * 2 + i);
            
        }
        
       
        return answer + move;
    }
}