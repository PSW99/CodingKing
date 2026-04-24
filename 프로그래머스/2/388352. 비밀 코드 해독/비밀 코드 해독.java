import java.util.*;

class Solution {
    int n;
    int[][] q;
    int[] ans;
    int answer = 0;
    int[] candidate = new int[5];  // 현재 만들고 있는 후보 코드
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        combine(1, 0);
        return answer;
    }
    
    void combine(int start, int picked) {
        if (picked == 5) {
            if (isValid()) answer++;
            return;
        }
        
        // 가지치기: 남은 숫자로 5개 못 채우면 컷
        if (n - start + 1 < 5 - picked) return;
        
        for (int i = start; i <= n; i++) {
            candidate[picked] = i;
            combine(i + 1, picked + 1);
        }
    }
    
    boolean isValid() {
        for (int i = 0; i < q.length; i++) {
            int matchCount = 0;
            int p1 = 0, p2 = 0;
            while (p1 < 5 && p2 < 5) {
                if (candidate[p1] == q[i][p2]) {
                    matchCount++;
                    p1++;
                    p2++;
                } else if (candidate[p1] < q[i][p2]) {
                    p1++;
                } else {
                    p2++;
                }
            }
            if (matchCount != ans[i]) return false;
        }
        return true;
    }
}