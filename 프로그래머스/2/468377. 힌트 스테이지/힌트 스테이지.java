import java.util.*;

class Solution {
    static int n;
    static int[][] cost;
    static int[][] hint;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] cost, int[][] hint) {
        n = cost.length;
        Solution.cost = cost;
        Solution.hint = hint;
        
        int[] hintCount = new int[n + 1];
        
        dfs(0, hintCount, 0);
        
        return answer;
    }
    
    static void dfs(int stage, int[] hintCount, int totalCost) {
        // 가지치기
        if (totalCost >= answer) return;
        
        // 모든 스테이지 클리어
        if (stage == n) {
            answer = Math.min(answer, totalCost);
            return;
        }
        
        // 현재 스테이지에서 사용 가능한 힌트권 수
        int usable = Math.min(hintCount[stage], n - 1);
        int stageCost = cost[stage][usable];
            
        // 마지막 스테이지 - 번들 구매 불가
        if (stage == n - 1) {
            dfs(stage + 1, hintCount, totalCost + stageCost);
            return;
        }
            
        // 번들 안 삼
        dfs(stage + 1, hintCount, totalCost + stageCost);
            
        // 번들 삼
        int bundlePrice = hint[stage][0];
        int[] newHint = hintCount.clone();
        for (int j = 1; j < hint[stage].length; j++) {
            newHint[hint[stage][j] - 1]++;
        }
        
        dfs(stage + 1, newHint, totalCost + stageCost + bundlePrice);
    }
}

