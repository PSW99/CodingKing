import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int gcdA = arrayA[0]; // arrayA 초기피제수,arrayA 최대공약수
        int gcdB = arrayB[0]; // arrayB 초기피제수,arraB 최대공약수
        
        //arrayA의 최대공약수 유크리드호제법
        for(int i = 1; i<arrayA.length; i++){
            int divisor = arrayA[i];
            while(divisor != 0){
                int r = gcdA % divisor;
                gcdA = divisor;
                divisor = r;
            }
        }
        
        //arrayB의 최대공약수 유크리드호제법
        for(int i = 1; i<arrayB.length; i++){
            int divisor = arrayB[i];
            while(divisor != 0){
                int r = gcdB % divisor;
                gcdB = divisor;
                divisor = r;
            }
        }
        
        boolean A = true;
        boolean B = true;
        for(int i = 0; i<arrayA.length; i++){
            if(arrayA[i] % gcdB == 0){
                A = false;
            }
            if(arrayB[i] % gcdA == 0){
                B = false;
            }
        }
        
        if(A && B){
            answer = Math.max(gcdA,gcdB);
        }else if(A){
            answer = gcdB;
        }else if(B){
            answer = gcdA;
        }

                                     
        return answer;
    }
}