import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[][] map;
    private static Queue<FireBall> queue = new LinkedList<>();
    private static List<FireBall> list;
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1}, dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken()); //질량
            int speed = Integer.parseInt(st.nextToken()); //속도
            int direction = Integer.parseInt(st.nextToken()); //방향
            queue.add(new FireBall(x, y, mass, speed, direction));
            map[x][y] = 1;
        }

        for (int i = 0; i < K; i++) doCommand();

        getAnswer();
        System.out.println(answer);
    }

    private static void doCommand() {
        list = new ArrayList<>();
        while (!queue.isEmpty()) {
            FireBall fireBall = queue.poll();
            int x = fireBall.x;
            int y = fireBall.y;
            int mass = fireBall.mass;
            int speed = fireBall.speed;
            int direction = fireBall.direction;

            int nx = x;
            int ny = y;
            for (int i = 0; i < speed; i++) {
                nx += dx[direction];
                if (nx < 0) nx = N - 1;
                else if (nx >= N) nx = 0;
                ny += dy[direction];
                if (ny < 0) ny = N - 1;
                else if (ny >= N) ny = 0;
            }
            map[x][y]--;
            map[nx][ny]++;
            list.add(new FireBall(nx, ny, mass, speed, direction));
        }

        //System.out.println(list);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 1) {
                    split(i, j);
                } else{
                    for (FireBall fireBall : list) {
                        if (fireBall.x == i && fireBall.y == j) {
                            queue.add(fireBall);
                        }
                    }
                }
            }
        }
    }

    private static void split(int x,int y) {
        int mass = 0;
        int speed = 0;
        int count = 0;
        boolean isOdd = false; //홀수 여부
        boolean isEven = false; //짝수 여부
        for (FireBall fireBall : list) {
            if (fireBall.x == x && fireBall.y == y) {
                mass += fireBall.mass;
                speed += fireBall.speed;
                count++;
                if (fireBall.direction % 2 == 0) {
                    isEven = true;
                } else {
                    isOdd = true;
                }
            }
        }
        mass /= 5;
        if (mass == 0) {
            map[x][y] = 0;
            return;
        }

        speed /= count;
        if (isEven && isOdd) {
            for (int i = 1; i < 8; i += 2) {
                queue.add(new FireBall(x, y, mass, speed, i));
            }
        } else {
            for (int i = 0; i < 7; i += 2) {
                queue.add(new FireBall(x, y, mass, speed, i));
            }
        }
        map[x][y] = 4;
    }

    private static void getAnswer() {
        while (!queue.isEmpty()) {
            answer += queue.poll().mass;
        }

    }
    static class FireBall {
        int x, y;
        int mass, speed, direction;


        public FireBall(int x, int y, int mass, int speed, int direction) {
            this.x = x;
            this.y = y;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }

    }
}
