import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        while (true) {
            if (map[x][y] == 0) {
                count++;
                map[x][y] = 2;
            }
            boolean isClean = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == 0) isClean = false;
            }

            if (!isClean) {
                if (direction == 0) direction = 3;
                else direction -= 1;

                int nx = x + dx[direction];
                int ny = y + dy[direction];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] == 0) {
                        x = nx;
                        y = ny;
                    }
                }

            } else {
                int nx = x + (dx[direction] * -1);
                int ny = y + (dy[direction] * -1);
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] != 1) {
                        x = nx;
                        y = ny;
                    }else break;
                }
            }
        }
        System.out.println(count);
    }

}
