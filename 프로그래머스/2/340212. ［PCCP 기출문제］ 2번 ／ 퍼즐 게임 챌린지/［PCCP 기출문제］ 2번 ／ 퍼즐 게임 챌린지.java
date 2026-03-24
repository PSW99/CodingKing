// 현재 퍼즐의 난이도 diff
// 현재 퍼즐의 소요시간 time_cur
// 이전 퍼즐의 소요시간 time_prev
// 내 숙련도 level
// diff <= level이면 time_cur만큼 시간 사용해서 문제해결
// diff > level이면 diff - level 만큼 틀리고, 틀릴 떄마다 time_cur만큼의 시간 사용 
//   -> 추가로 time_prev 만큼의 시간을 사용해 이전 퍼즐 다시 풀고 와야됨(이전 퍼즐의 난이도에 상관없이 틀리지 않음)
// 제한시간 limit
// return 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값


class Solution {
    static int N;
    
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        N = diffs.length;
        
        int start = 1;
        int end = 100000;
        
        while(start < end){
            int mid = (start + end) / 2;
            long cal = calculateTime(diffs, times, mid);
            
            if(limit < cal) start = mid + 1;
            else end = mid;
        }
        
        answer = start;
        
        return answer;
    }
    
    public long calculateTime(int[] diffs, int[] times, int level){
        long cal = 0;
        
        for(int i =0; i < N; i++){
            int diff = diffs[i];
            int time = times[i];
            
            if(diff <= level){
                cal += time;
            } else {
                cal += (time + times[i-1]) * (diff - level) + time;
            }
        }
        
        return cal;
    }
}