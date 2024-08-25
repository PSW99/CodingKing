import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] board;
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static int[] ratio = {1, 1, 2, 7, 7, 2, 10, 10, 5};
    private static int[] d0x = {-1, 1, -2, -1, 1, 2, -1, 1, 0}, doy = {1, 1, 0, 0, 0, 0, -1, -1, -2};
    private static int[] d1x = {-1, -1, 0, 0, 0, 0, 1, 1, 2}, d1y = {-1, 1, -2, -1, 1, 2, -1, 1, 0};
    private static int[] d2x = {1, -1, 2, 1, -1, -2, 1, -1, 0}, d2y = {-1, -1, 0, 0, 0, 0, 1, 1, 2};
    private static int[] d3x = {1, 1, 0, 0, 0, 0, -1, -1, -2}, d3y = {1, -1, 2, 1, -1, -2, 1, -1, 0};

    private static int tx, ty, direction;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tx = N / 2; // 초기 토네이도 x위치
        ty = N / 2; // 초기 토에니도 y위치
        int moves = 1;
        direction = 0;

        while (true) {
            int num =(moves + 1) / 2;

            for (int i = 0; i < num; i++) {
                tx += dx[direction];
                ty += dy[direction];
                if (tc()) {
                    System.out.println(answer);
                    return;
                }
                tornadoMoving();
            }
            moves++;
            direction = (direction + 1) % 4;
        }

    }

    private static void tornadoMoving() {
        int[] ddx, ddy;
        if (direction == 0) {
            ddx = d0x;
            ddy = doy;
        } else if (direction == 1) {
            ddx = d1x;
            ddy = d1y;
        } else if (direction == 2) {
            ddx = d2x;
            ddy = d2y;
        } else {
            ddx = d3x;
            ddy = d3y;
        }

        int sand = board[tx][ty];
        board[tx][ty] = 0;
        int totalSpread = 0;

        for (int i = 0; i < 9; i++) {
            int nx = tx + ddx[i];
            int ny = ty + ddy[i];
            int sandBlown = (sand * ratio[i]) / 100; // 바람에 날린 모래양

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) answer += sandBlown;
            else board[nx][ny] += sandBlown;

            totalSpread += sandBlown;
        }


        int ax = tx + dx[direction];
        int ay = ty + dy[direction];
        int alpha = sand - totalSpread;
        if (ax < 0 || ax >= N || ay < 0 || ay >= N) answer += alpha;
        else board[ax][ay] += alpha;

    }

    private static boolean tc() {
        return tx < 0 || tx >= N || ty < 0 || ty >= N;
    }
}
