import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int[] dp = new int[m];
        
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dp, INF);
        
        dp[0] = 0;

        for (int[] item : info) {
            int costA = item[0];
            int costB = item[1];
            
            int[] nextDp = new int[m];
            Arrays.fill(nextDp, INF);

            for (int j = 0; j < m; j++) {
                if (dp[j] == INF) continue;

        
                if (dp[j] + costA < n) {
                    nextDp[j] = Math.min(nextDp[j], dp[j] + costA);
                }

                if (j + costB < m) {
                    nextDp[j + costB] = Math.min(nextDp[j + costB], dp[j]);
                }
            }
            dp = nextDp;
        }

        int answer = INF;
        for (int val : dp) {
            answer = Math.min(answer, val);
        }

        return (answer == INF) ? -1 : answer;
    }
}