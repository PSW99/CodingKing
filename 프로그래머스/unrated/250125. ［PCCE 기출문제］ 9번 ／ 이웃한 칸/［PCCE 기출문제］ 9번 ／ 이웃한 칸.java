class Solution {
    public int solution(String[][] board, int h, int w) {
        
        int count = 0; // board[h][w]와 이웃한 칸(상, 하, 좌, 우)들 중 같은 색의 수
        String color = board[h][w]; // board[h][w]의 색
        
        // 상, 하, 좌, 우
        int [][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        // directions 순회 (상 -> 하 -> 좌 -> 우)
        for (int [] dir : directions){

            // 이동할 인덱스 생성
            int mov_h = h + dir[0];
            int mov_w = w + dir[1];
            // 이동한 인덱스가 유효할 
            if ((mov_h >= 0) && (mov_h < board.length) && (mov_w >= 0) && (mov_w < board[0].length)){

                // 이동한 칸의 색과 board[h][w]의 색이 같을 시 count += 1
                if (color.equals(board[mov_h][mov_w])){
                    count += 1;
                }
            } 
        }
        return count;
    }
}
