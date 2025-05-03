import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    private static int N;
    private static boolean answer;
    private static int[][] board;
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            answer = false;
            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    if (input.charAt(j) == '.') {
                        board[i][j] = 0;
                    } else {
                        board[i][j] = 1;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                if (answer) break;
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 1) {
                        for (int d = 0; d < 8; d++) {
                            dfs(i, j, 1, d);
                        }
                    }
                }
            }


            if (answer) {
                System.out.println("#" + test_case + " " + "YES");
            } else {
                System.out.println("#" + test_case + " " + "NO");
            }
        }
    }

    private static void dfs(int x, int y, int cnt, int direction) {
        if (cnt == 5) {
            answer = true;
            return;
        }

        int nx = x + dx[direction];
        int ny = y + dy[direction];

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) return;
        if (board[nx][ny] == 0) return;

        dfs(nx, ny, cnt + 1, direction);
    }
}
