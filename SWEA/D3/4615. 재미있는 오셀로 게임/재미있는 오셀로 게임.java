import java.io.*;
import java.util.*;

public class Solution {
    static int[][] board;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1, 0, 1,-1,1,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            board = new int[N][N];

            int mid = N / 2;
            board[mid - 1][mid - 1] = 2;
            board[mid][mid] = 2;
            board[mid - 1][mid] = 1;
            board[mid][mid - 1] = 1;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int color = Integer.parseInt(st.nextToken());

                board[x][y] = color;

                // 8방향 확인
                for (int d = 0; d < 8; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    List<int[]> toFlip = new ArrayList<>();

                    while (inRange(nx, ny, N) && board[nx][ny] != 0 && board[nx][ny] != color) {
                        toFlip.add(new int[]{nx, ny});
                        nx += dx[d];
                        ny += dy[d];
                    }

                    if (inRange(nx, ny, N) && board[nx][ny] == color) {
                        for (int[] pos : toFlip) {
                            board[pos[0]][pos[1]] = color;
                        }
                    }
                }
            }

            int black = 0, white = 0;
            for (int[] row : board) {
                for (int cell : row) {
                    if (cell == 1) black++;
                    else if (cell == 2) white++;
                }
            }

            System.out.println("#" + tc + " " + black + " " + white);
        }
    }

    private static boolean inRange(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}