import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int x1, y1, x2, y2;
    private static char[][] arr;
    private static boolean[][] booleans;
    private static int[] dx = { 0, 1, 0, -1 }, dy = {-1, 0, 1, 0};
    private static int[][] dijkstra;
    private static PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));

    private static void extracted() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;
        arr = new char[N][M];
        booleans = new boolean[N][M];
        dijkstra = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        extracted();
        System.out.println(bfs(new Node(x1, y1,0)));

    }

    private static int bfs(Node node) {
        queue.add(node);
        booleans[node.x][node.y] = true;
        dijkstra[x1][y1] = 0;
        while (!queue.isEmpty()) {
            Node myNode = queue.poll();
            int x = myNode.x;
            int y = myNode.y;
            int time = myNode.time;
            if (x == x2 && y == y2) return time;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (!booleans[nx][ny]) {
                    booleans[nx][ny] = true;
                    if (arr[nx][ny] == '0') {
                        queue.add(new Node(nx, ny, time));
                        dijkstra[nx][ny] = time + 1;
                    } else {
                        queue.add(new Node(nx, ny, time + 1));
                        dijkstra[nx][ny] = time;
                    }
                }
            }
        }
        return -1;
    }

    static class Node {
        int x;
        int y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
