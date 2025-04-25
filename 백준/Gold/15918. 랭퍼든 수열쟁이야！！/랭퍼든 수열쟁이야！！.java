import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, x, y;
    private static int answer = 0;
    private static int num;
    private static boolean[] used;
    private static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;

        sequence = new int[2 * n];
        num = y - x - 1;
        used = new boolean[n + 1];
        used[num] = true;
        sequence[x] = sequence[y] = num;

        backtracking(0, 2);

        System.out.println(answer);
    }

    private static void backtracking(int start, int cnt) {
        if (cnt == 2 * n) {
            answer++;
            return;
        }

        for (int i = start; i < 2 * n; i++) {
            if (sequence[i] != 0) continue;

            for (int j = 1; j <= n; j++) {
                if (used[j]) continue;
                if (i + j + 1 >= 2 * n || sequence[i + j + 1] != 0) continue;

                sequence[i] = sequence[i + j + 1] = j;
                used[j] = true;

                backtracking(i + 1, cnt + 2);

                used[j] = false;
                sequence[i] = sequence[i + j + 1] = 0;
            }

            return;
        }
    }
}
