import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] paper;
    private static int[] count = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);

        StringBuilder sb = new StringBuilder();
        sb.append(count[0]).append("\n").append(count[1]).append("\n").append(count[2]);
        System.out.println(sb);

    }

    private static void divide(int x, int y, int N) {
        if (isSame(x, y, N)) {
            count[paper[x][y] + 1]++;
            return;
        }

        int size = N / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divide(x + i * size, y + j * size, size);
            }
        }
    }

    private static boolean isSame(int x, int y, int N) {
        int standard = paper[x][y];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (paper[x + i][y + j] != standard) {
                    return false;
                }
            }
        }
        return true;
    }
}
