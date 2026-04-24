import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int max = 0;
        for(int[] e : edges){
            max = Math.max(max, Math.max(e[0], e[1]));
        }
        
        int[] inDegree = new int[max + 1];
        int[] outDegree = new int[max + 1];
        
        for(int[] e : edges) {
            outDegree[e[0]]++;
            inDegree[e[1]]++;
        }
        
        int createdVertex = 0;
        int eightCount = 0;
        int stickCount = 0;
        
        for(int i = 1; i <= max; i++){
            if(inDegree[i] == 0 && outDegree[i] >= 2){
                createdVertex = i;
            } else if(inDegree[i] >= 2 && outDegree[i] >= 2){
                eightCount++;
            } else if(inDegree[i] >= 1 && outDegree[i] == 0){
                stickCount++;
            }
        }
        
        int donutCount = outDegree[createdVertex] - stickCount - eightCount;
        
        return new int[] {createdVertex, donutCount, stickCount, eightCount};
    }
}