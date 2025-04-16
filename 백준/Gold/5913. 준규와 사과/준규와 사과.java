import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int K;
    private static int[][] map = new int[5][5];
    private static boolean[][] visited = new boolean[5][5];
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1;
        }

        visited[0][0] = true;

        backTracking(0, 0, 1);

        System.out.println(answer);
    }

    private static void backTracking(int x, int y, int count) {
        if (x == 4 && y == 4) {
            if (count == 25 - K) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny] || map[nx][ny] == 1) {
                continue;
            }

            visited[nx][ny] = true;
            backTracking(nx, ny, count + 1);
            visited[nx][ny] = false;
        }
    }
}
