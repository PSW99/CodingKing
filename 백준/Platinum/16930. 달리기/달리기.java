import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static char[][] board;
    private static int x1, y1, x2, y2;
    private static int[][] visited;
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;

        if (x1 == x2 && y1 == y2) {
            System.out.println(0);
        } else {
            queue.add(new Node(x1, y1, 0));
            visited[x1][y1] = 0;
            bfs();
            System.out.println(visited[x2][y2] == Integer.MAX_VALUE ? -1 : visited[x2][y2]);
        }
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                move(cur, i);
            }
        }
    }

    private static void move(Node node, int direction) {
        int nx = node.x;
        int ny = node.y;
        int distance = node.distance;
        for (int i = 0; i < K; i++) {
            nx += dx[direction];
            ny += dy[direction];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == '#') return;
            if (visited[nx][ny] < distance + 1) break;
            if (visited[nx][ny] > distance + 1) {
                visited[nx][ny] = distance + 1;
                queue.add(new Node(nx, ny, visited[nx][ny]));
            }
        }
    }

    private static class Node {
        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}