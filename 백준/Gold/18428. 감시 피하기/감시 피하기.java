import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static String[][] map;
    private static boolean[] check;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static boolean answer = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new String[N][N];
        check = new boolean[N * N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken();
            }
        }

        backtracking(0, 0);

        if (answer) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void backtracking(int start, int count) {
        if (count == 3) {
            if (isAvoid() == 1) {
                answer = true;
            }
            return;
        }

        for (int i = start; i < N * N; i++) {
            if (map[i / N][i % N].equals("S") || map[i / N][i % N].equals("T")) {
                continue;
            }

            check[i] = true;
            backtracking(i + 1, count + 1);
            check[i] = false;
        }
    }

    private static int isAvoid() {
        String[][] clone = new String[N][N];
        
        for (int i = 0; i < N; i++) {
            clone[i] = map[i].clone();
        }

        for (int i = 0; i < N * N; i++) {
            if (check[i]) {
                clone[i / N][i % N] = "O";
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (clone[i][j].equals("T")) {
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = i;
                        int ny = j;
                        while (true) {
                            nx += dx[dir];
                            ny += dy[dir];

                            if (nx < 0 || nx >= N || ny < 0 || ny >= N || clone[nx][ny].equals("O")) {
                                break;
                            }

                            if (clone[nx][ny].equals("S")) {
                                return 0;
                            }
                        }
                    }
                }
            }
        }

        return 1;
    }
}
