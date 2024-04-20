import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int R, C, M;
    private static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, 1, -1};
    private static Queue<shark> queue = new ArrayDeque<>();
    private static shark[][] board;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            board[x - 1][y - 1] = new shark(x - 1, y - 1, s, d, z);
        }

        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                if (board[j][i] != null){
                    answer += board[j][i].z;
                    board[j][i] = null;
                    break;
                }
            }
            move();
        }

        System.out.println(answer);
    }

    private static void move() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != null) queue.add(board[i][j]);
            }
        }

        board = new shark[R][C];

        while (!queue.isEmpty()) {
            shark cur = queue.poll();
            int x = cur.x, y = cur.y, s = cur.s, d = cur.d, z = cur.z;

            if(d == 1 || d == 2) s %= (R - 1) * 2;
            else s %= (C - 1) * 2;

            while (s-- > 0) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= R || ny >= C || nx < 0 || ny < 0) {
                    if (d == 1 || d == 3) d += 1;
                    else d -= 1;
                    nx = x + dx[d];
                    ny = y + dy[d];
                }
                x = nx;
                y = ny;
            }

            if (board[x][y] != null) {
                if (board[x][y].z < z) {
                    board[x][y] = new shark(x, y, cur.s, d, z);
                }
            } else board[x][y] = new shark(x, y, cur.s, d, z);

        }

    }

    private static class shark {
        int x, y, s, d, z;

        public shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }

    }
}
