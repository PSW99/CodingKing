import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, L;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            if (canPass(map[i]))
                count++;
        }

        for (int i = 0; i < N; i++) {
            int[] col = new int[N];
            for (int j = 0; j < N; j++) {
                col[j] = map[j][i];
            }
            if (canPass(col))
                count++;
        }

        System.out.println(count);
    }

    private static boolean canPass(int[] line) {
        boolean[] used = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = line[i + 1] - line[i];

            if (diff == 0)
                continue;

            if (Math.abs(diff) > 1)
                return false;

            if (diff == 1) {
                for (int j = 0; j < L; j++) {
                    int idx = i - j;
                    if (idx < 0 || line[idx] != line[i] || used[idx])
                        return false;
                    used[idx] = true;
                }
            }

            else if (diff == -1) {
                for (int j = 1; j <= L; j++) {
                    int idx = i + j;
                    if (idx >= N || line[idx] != line[i + 1] || used[idx])
                        return false;
                    used[idx] = true;
                }
            }
        }
        return true;
    }
}