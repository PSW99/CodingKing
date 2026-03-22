import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int row, col;
    static int[][] map;
    static boolean[][] visited;
    static Node end;

    public int solution(String[] board) {
        row = board.length;
        col = board[0].length();
        map = new int[row][col];
        visited = new boolean[row][col];
        Queue<Node> queue = new LinkedList<>();

        for(int i = 0; i < row; i++){
            String s = board[i];
            for(int j = 0; j < col; j++){
                if(s.charAt(j) == 'D'){
                    map[i][j] = 1;
                } else if(s.charAt(j) == 'R'){
                    map[i][j] = 2;
                    queue.add(new Node(i, j, 0));
                    visited[i][j] = true; // 시작 위치 방문 처리
                } else if(s.charAt(j) == 'G'){
                    map[i][j] = 3;
                    end = new Node(i, j, 0);
                }
            }
        }

        int min = -1; // 도달할 수 없는 경우의 기본값

        while(!queue.isEmpty()){
            Node start = queue.poll();
            int x = start.x;
            int y = start.y;
            int cnt = start.cnt;

            // 목표 지점 도달 시 최소 이동 횟수 기록 후 종료 (BFS 특성상 최초 도달이 최소 횟수)
            if(x == end.x && y == end.y){
                min = cnt;
                break;
            }

            for(int i = 0; i < 4; i++){
                Node nextNode = move(x, y, cnt, i);
                
                // 이동한 위치가 유효하고, 처음 방문하는 곳이라면 큐에 추가
                if(nextNode != null && !visited[nextNode.x][nextNode.y]){
                    visited[nextNode.x][nextNode.y] = true;
                    queue.add(nextNode);
                }
            }
        }

        return min;
    }

    public Node move(int x, int y, int cnt, int dir){
        int nx = x;
        int ny = y;

        while(true){
            nx += dx[dir];
            ny += dy[dir];

            // 보드 범위를 벗어나면 이전 위치로 되돌리고 이동 중단
            if(nx < 0 || nx >= row || ny < 0 || ny >= col){
                nx -= dx[dir];
                ny -= dy[dir];
                break;
            }

            // 장애물에 부딪히면 이전 위치로 되돌리고 이동 중단
            if(map[nx][ny] == 1){
                nx -= dx[dir];
                ny -= dy[dir];
                break;
            }
        }

        // 제자리인 경우 (바로 앞이 벽이거나 장애물이라 이동하지 못한 경우)
        if(nx == x && ny == y) return null;

        return new Node(nx, ny, cnt + 1);
    }

    public static class Node{
        int x, y, cnt;

        public Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}