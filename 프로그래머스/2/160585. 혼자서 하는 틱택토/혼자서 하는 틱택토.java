class Solution {
    public int solution(String[] board) {
        // 1. O, X 개수 세기
        int oCount = 0, xCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'O') oCount++;
                else if (c == 'X') xCount++;
            }
        }
        
        // 2. 개수 조건: O는 X와 같거나 X보다 1 많아야 함
        if (oCount < xCount || oCount > xCount + 1) return 0;
        
        // 3. 승리 판정
        boolean oWin = checkWin(board, 'O');
        boolean xWin = checkWin(board, 'X');
        
        // 4. 둘 다 이긴 상태는 불가능
        if (oWin && xWin) return 0;
        
        // 5. O가 이겼으면 마지막 수가 O → O = X + 1
        if (oWin && oCount != xCount + 1) return 0;
        
        // 6. X가 이겼으면 마지막 수가 X → O = X
        if (xWin && oCount != xCount) return 0;
        
        return 1;
    }
    
    private boolean checkWin(String[] board, char c) {
        // 가로 3줄
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c 
                && board[i].charAt(1) == c 
                && board[i].charAt(2) == c) return true;
        }
        // 세로 3줄
        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == c 
                && board[1].charAt(j) == c 
                && board[2].charAt(j) == c) return true;
        }
        // 대각선 2줄
        if (board[0].charAt(0) == c 
            && board[1].charAt(1) == c 
            && board[2].charAt(2) == c) return true;
        if (board[0].charAt(2) == c 
            && board[1].charAt(1) == c 
            && board[2].charAt(0) == c) return true;
        
        return false;
    }
}