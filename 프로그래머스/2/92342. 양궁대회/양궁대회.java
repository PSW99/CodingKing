// import java.util.*;

// class Solution {
//     static int N;
//     static int[] info;
//     static int[] lion;
//     static List<Result> results = new ArrayList<>();
//     static int max = Integer.MIN_VALUE;

//     public int[] solution(int n, int[] info) {
//         int[] answer = {};
//         N = n;
//         this.info = info;

//         backtracking(0, 0, new int[11]);

//         results.sort(Comparator.comparingInt((Result r) -> r.score).reversed());
        
//         if(results.size() == 0){
//             return new int[]{-1};
//         }
        
//         return results.get(0).arr;
//     }

//     public void backtracking(int start, int cnt, int[] lion){
//         if(cnt >= N){
//             Result result = compare(lion);
//             if(result != null){
//                 results.add(result);
//             }
//             return;
//         }

//         for(int i = start; i < 11; i++) {
//             int[] arr = lion.clone();
//             arr[i]++;
//             if(arr[i] - info[i] > 1) continue;
            
//             backtracking(i + 1, cnt + 1, arr); 
//         }
        
        
//     }

//     public Result compare(int[] arr){
//         int appeachScore = 0;
//         int lionScore = 0;
        
//         for(int i = 0; i < 11; i++){
//             if(info[i] < arr[i]){
//                 lionScore += i;
//             } else{
//                 appeachScore += i;
//             }
//         }
        
        

//         if(lionScore > appeachScore && max < lionScore){
//             return new Result(arr, lionScore);
//         }

//         return null;
//     }


//     public static class Result{
//         int[] arr;
//         int score;

//         public Result(int[] arr, int score){
//             this.arr = arr;
//             this.score = score;
//         }

//         @Override
//         public String toString() {
//             return "Result{arr=" + Arrays.toString(arr) + ", score=" + score + "}";
//         }
//     }

// }

import java.util.*;

class Solution {
    static int N;
    static int[] info;
    static List<Result> results = new ArrayList<>();
    
    public int[] solution(int n, int[] info) {
        N = n;
        this.info = info;
        backtracking(0, 0, new int[11]);
        
        if (results.isEmpty()) return new int[]{-1};
        
        results.sort(Comparator
            .comparingInt((Result r) -> r.score).reversed()
            .thenComparing((a, b) -> {
                for (int i = 10; i >= 0; i--) {
                    if (a.arr[i] != b.arr[i]) return b.arr[i] - a.arr[i];
                }
                return 0;
            })
        );
        
        return results.get(0).arr;
    }
    
    public void backtracking(int start, int cnt, int[] lion) {
        if (cnt == N) {
            Result result = compare(lion);
            if (result != null) results.add(result);
            return;
        }
        
        for (int i = start; i < 11; i++) {
            int[] arr = lion.clone();
            arr[i]++;
            // 라이언이 해당 점수에서 어피치보다 2발 이상 더 쏘는 건 손해 → 가지치기
            if (arr[i] - info[i] > 1) continue;
            
            backtracking(i, cnt + 1, arr);  // i로 재귀 (같은 점수 또 쏠 수 있음)
        }
    }
    
    public Result compare(int[] arr) {
        int apeachScore = 0;
        int lionScore = 0;
        
        for (int i = 0; i < 11; i++) {
            int score = 10 - i;  // 인덱스 → 점수 변환
            
            if (info[i] == 0 && arr[i] == 0) continue;  // 둘 다 0발 스킵
            
            if (info[i] < arr[i]) {
                lionScore += score;
            } else {
                apeachScore += score;
            }
        }
        
        if (lionScore > apeachScore) {
            return new Result(arr, lionScore - apeachScore);  // 점수 차이로 저장
        }
        return null;
    }
    
    public static class Result {
        int[] arr;
        int score;  // 점수 차이 (lion - apeach)
        
        public Result(int[] arr, int score) {
            this.arr = arr;
            this.score = score;
        }
        
        @Override
        public String toString() {
            return "Result{arr=" + Arrays.toString(arr) + ", score=" + score + "}";
        }
    }
}