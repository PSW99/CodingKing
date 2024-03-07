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
    private static boolean[][] visited;
    private static int[] dx = {0, 1, 0, -1, -1, -1, 1, 1}, dy = {-1, 0, 1, 0, 1, -1, 1, -1};
    private static Queue<Node> queue;
    private static int max = Integer.MIN_VALUE;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    bfs(new Node(i, j));
                }
            }
        }
        System.out.println(max);

    }

    private static void bfs(Node node) {
        queue = new LinkedList<>();
        visited = new boolean[N][M];
        queue.add(node);
        visited[node.x][node.y] = true;
        arr = new int[N][M];
        arr[node.x][node.y] = 0;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;

            boolean isFind = false;
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;
                arr[nx][ny] = arr[x][y] + 1;
                if (map[nx][ny] == 1) {
                    max = Math.max(max, arr[nx][ny]);
                    isFind = true;
                    break;
                }
            }
            if (isFind) break;
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
