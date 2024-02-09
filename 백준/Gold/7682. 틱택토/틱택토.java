import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static char[][] arr;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String str = br.readLine();
            if(str.equals("end"))	break;

            arr = new char[3][3];

            for(int i=0; i<9; i++) {
                arr[i/3][i%3] = str.charAt(i);
            }

            if(tictactoe()) {
                System.out.println("valid");
            }else {
                System.out.println("invalid");
            }

        }

    }
    private static boolean tictactoe() {
        int xCnt = 0;
        int oCnt = 0;

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(arr[i][j] == 'X') {
                    xCnt++;
                }else if(arr[i][j] == 'O') {
                    oCnt++;
                }
            }
        }

        if(xCnt<oCnt) {
            return false;
        }

        int sum = xCnt + oCnt;

        // 놓을 말이 없는 경우
        // 무조건 X가 이기거나 무승부여야 한다.
        if(sum==9 && xCnt==oCnt+1) {
            // O 말이 이기거나 둘다 이기면 return false
            if(isConnect('O') && !isConnect('X')) {
                return false;
            }else if(isConnect('O') && isConnect('X')) {
                return false;
            }
            return true;
        }

        // 아직 말이 남은 경우
        // X가 이긴경우 : X의 개수가 하나 더 많아야한다.
        // O가 이긴경우 : O와 X의 개수가 같아야한다.
        else {
            if(xCnt == oCnt+1) {
                if(isConnect('X') && !isConnect('O')) {
                    return true;
                }else {
                    return false;
                }
            }
            else if(xCnt==oCnt) {
                if(isConnect('O') && !isConnect('X')) {
                    return true;
                }else {
                    return false;
                }
            }
        }

        return false;
    }

    private static boolean isConnect(char c) {
        // 가로
        for(int i=0; i<3; i++) {
            if(arr[i][0] == c && arr[i][1] == c && arr[i][2] == c) {
                return true;
            }
        }
        // 세로
        for(int i=0; i<3; i++) {
            if(arr[0][i] == c && arr[1][i] == c && arr[2][i] == c) {
                return true;
            }
        }
        // 대각선
        if(arr[0][0] == c && arr[1][1] == c && arr[2][2] == c) {
            return true;
        }

        if(arr[2][0] == c && arr[1][1] == c && arr[0][2] == c) {
            return true;
        }

        return false;
    }

}