import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] arr;
    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        dp = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        for (int i = 3; i <= N; i++) {
            for (int j = 1; j <= N - i + 1; j++) {
                int end = j + i - 1;
                if (arr[j] == arr[end] && dp[j + 1][end - 1]) {
                    dp[j][end] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (dp[s][e]) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }

        System.out.println(sb);
    }
}
