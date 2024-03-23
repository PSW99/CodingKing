import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static Queue<Node> queue = new LinkedList<>();
    private static List<Node> list;
    private static boolean[][] isCloud;
    private static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1}, dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue.add(new Node(N - 1, 0));
        queue.add(new Node(N - 1, 1));
        queue.add(new Node(N - 2, 0));
        queue.add(new Node(N - 2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            move(direction - 1, num);
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void move(int direction,int num) {
        isCloud = new boolean[N][N];
        list = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;
            int nx = x;
            int ny = y;

            for (int i = 0; i < num; i++) {
                nx += dx[direction];
                if (nx < 0) nx = N - 1;
                else if (nx >= N) nx = 0;
                ny += dy[direction];
                if (ny < 0) ny = N - 1;
                else if (ny >= N) ny = 0;
            }
            map[nx][ny]++;
            isCloud[nx][ny] = true;
            list.add(new Node(nx, ny));
        }

        for (Node node : list) {
            int x = node.x;
            int y = node.y;
            int count = 0;
            for (int i = 1; i < 8; i += 2) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N ) continue;
                if (map[nx][ny] > 0) count++;

            }
            map[x][y] += count;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] >= 2 && !isCloud[i][j]) {
                    map[i][j] -= 2;
                    queue.add(new Node(i, j));
                }
            }
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
