import java.util.*;
class Solution {
    
    static int diff = 0;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        int answer = -1;
        int rl = wires.length - 1;
       
        List<Integer> list = new ArrayList<>();
        int [][] adjArray = new int[n+1][n+1];
        
        for(int i = 0; i<wires.length; i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            
            adjArray[v1][v2] = 1;
            adjArray[v2][v1] = 1;
        }
        
        for(int i = 0; i<wires.length; i++){
            int right = wires[i][0];
            int left = wires[i][1];
            
            adjArray[right][left] = 0;
            adjArray[left][right] = 0;
            
            visited = new boolean[n+1];
            dfs(1,adjArray,visited);
            //System.out.println("right:" + right + "left:" + left);
            //System.out.println("Diff:" + diff);
            int diff2= rl - diff;
            if(diff > diff2){
              list.add(diff - diff2);  
            } else{
                list.add(diff2 - diff);
            }
        
            diff = 0;
            
            adjArray[right][left] = 1;
            adjArray[left][right] = 1;
        }
        
        Collections.sort(list);
        
        answer = list.get(0);
        
        return answer;
    }
    
    public void dfs(int v, int[][] adjArray, boolean[] visited){
        int l = adjArray.length - 1;
        visited[v] = true;
        //System.out.print(v + " ");
        
        for(int i = 1; i <= l; i++) {
			if(adjArray[v][i] == 1 && !visited[i]) {
                diff++;
				dfs(i, adjArray, visited);
			}
		}
        
    }
}