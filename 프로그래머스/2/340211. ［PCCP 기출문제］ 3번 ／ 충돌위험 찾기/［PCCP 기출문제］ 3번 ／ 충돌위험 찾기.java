import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        List<List<int[]>> robotPaths = new ArrayList<>();
        int maxLen = 0;

        for (int[] route : routes) {
            List<int[]> path = new ArrayList<>();
            
            int startIdx = route[0] - 1;
            int r = points[startIdx][0];
            int c = points[startIdx][1];
            
            path.add(new int[]{r, c});

            for (int i = 1; i < route.length; i++) {
                int nextIdx = route[i] - 1;
                int targetR = points[nextIdx][0];
                int targetC = points[nextIdx][1];

                while (r != targetR) {
                    r += (targetR > r) ? 1 : -1;
                    path.add(new int[]{r, c});
                }
                
                while (c != targetC) {
                    c += (targetC > c) ? 1 : -1;
                    path.add(new int[]{r, c});
                }
            }
            
            robotPaths.add(path);
            maxLen = Math.max(maxLen, path.size());
        }

        for (int t = 0; t < maxLen; t++) {
            Map<Integer, Integer> positionCount = new HashMap<>();
            
            for (List<int[]> path : robotPaths) {
                if (t < path.size()) {
                    int[] pos = path.get(t);
                    int key = pos[0] * 1000 + pos[1];
                    positionCount.put(key, positionCount.getOrDefault(key, 0) + 1);
                }
            }

            for (int count : positionCount.values()) {
                if (count >= 2) {
                    answer++;
                }
            }
        }

        return answer;
    }
}