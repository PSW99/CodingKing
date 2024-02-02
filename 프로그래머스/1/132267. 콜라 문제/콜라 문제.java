class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        int result = 0; // 답
        int remain = 0; //나머지
    
        while(true){
            result += n/a * b;
            answer = n/a * b;
            remain = n%a;
            n = answer + remain;
            remain = 0;
            answer = 0;
            System.out.println(n);
            if(n < a){
                break;
            }
        }
    
        return result;
    }
}