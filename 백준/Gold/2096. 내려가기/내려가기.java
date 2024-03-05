import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        board = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] minDp = new int[N][3];
        for (int i = 0; i < 3; i++) {
            minDp[0][i] = board[0][i];
        }

        int[][] maxDp = new int[N][3];
        for (int i = 0; i < 3; i++) {
            maxDp[0][i] = board[0][i];
        }

        for (int i = 1; i < N; i++) {
            int minB1 = minDp[i - 1][0];
            int minB2 = minDp[i - 1][1];
            int minB3 = minDp[i - 1][2];
            int maxB1 = maxDp[i - 1][0];
            int maxB2 = maxDp[i - 1][1];
            int maxB3 = maxDp[i - 1][2];
            int cur1 = board[i][0];
            int cur2 = board[i][1];
            int cur3 = board[i][2];
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    minDp[i][j] = Math.min(cur1 + minB1, cur1 + minB2);
                    maxDp[i][j] = Math.max(cur1 + maxB1, cur1 + maxB2);
                } else if (j == 1) {
                    minDp[i][j] = Math.min(cur2 + minB1, Math.min(cur2 + minB2, cur2 + minB3));
                    maxDp[i][j] = Math.max(cur2 + maxB1, Math.max(cur2 + maxB2, cur2 + maxB3));
                } else {
                    minDp[i][j] = Math.min(cur3 + minB2, cur3 + minB3);
                    maxDp[i][j] = Math.max(cur3 + maxB2, cur3 + maxB3);
                }
            }
        }

        int max = Math.max(maxDp[N - 1][0], Math.max(maxDp[N - 1][1], maxDp[N - 1][2]));
        int min = Math.min(minDp[N - 1][0], Math.min(minDp[N - 1][1], minDp[N - 1][2]));
        sb.append(max).append(" ").append(min);
        System.out.println(sb);
    }
}
