import java.util.*;
class Solution {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    static Queue<Node> queue = new ArrayDeque<>();
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    
    public int solution(String[] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length();
        board = new char[N][M];
        visited = new boolean[N][M];
        
        Node start = null;
        
        for(int i = 0; i < N; i++){
            String s = maps[i];
            for(int j = 0; j < M; j++){
                board[i][j] = s.charAt(j);
                if(s.charAt(j) == 'S'){
                    start = new Node(i, j, 0);
                    visited[i][j] = true;
                }
            }
        }
        
        queue.add(start);
        
        if(bfs('L') == -1){
            return -1;
        }
        
        answer = bfs('E');
        
        return answer;
    }
    
    public static int bfs(char find){
        
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int dist = cur.dist;
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] != 'X' && !visited[nx][ny]){
                    if(board[nx][ny] == find){
                        queue.clear();
                        queue.add(new Node(nx, ny, dist + 1));
                        visited = new boolean[N][M];
                        visited[nx][ny] = true;
                        return dist + 1;
                    }
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny, dist + 1));
                }
            }
        }
        return -1;
    }
    
    public static class Node{
        int x, y, dist;
        
        public Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}