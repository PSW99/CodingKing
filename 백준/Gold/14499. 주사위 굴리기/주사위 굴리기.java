import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, x, y, K;
    private static int[] dice = {0, 0, 0, 0, 0, 0};
    private static int[][] map;
    private static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0}; //동 서 북 남
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int direction = Integer.parseInt(st.nextToken()) - 1;
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if (map[nx][ny] != 0){
                move(direction, map[nx][ny],false);
                map[nx][ny] = 0;
            } else{
                move(direction, map[nx][ny],true);
                map[nx][ny] = dice[1];
            }

            x = nx;
            y = ny;

            sb.append(dice[4]).append("\n");
        }
        System.out.println(sb);
    }

    private static void move(int direction, int number,boolean isZero) {
        int zero = dice[0];
        int one = dice[1];
        int two = dice[2];
        int three = dice[3];
        int four = dice[4];
        int five = dice[5];
        switch (direction) {
            case 0:
                dice = new int[]{zero, five, one, three, two, four};
                break;
            case 1:
                dice = new int[]{zero, two, four, three, five, one};
                break;
            case 2:
                dice = new int[]{one, three, two, four, zero, five};
                break;
            case 3:
                dice = new int[]{four, zero, two, one, three, five};
                break;
        }
        if (!isZero) dice[1] = number;

    }
}
