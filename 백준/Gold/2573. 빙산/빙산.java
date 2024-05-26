import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] board;
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) queue.add(new Node(i, j));
            }
        }

        while (true) {
            bfs();
            answer++;
            if (queue.isEmpty()) {
                System.out.println(0);
                break;
            }
            if (isDivide()) {
                System.out.println(answer);
                break;
            }

        }

    }

    private static void bfs() {
        int[][] arr = new int[N][M];
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int count = 0;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (board[nx][ny] == 0) count++;
            }
            arr[x][y] = board[x][y] - count;

            if (arr[x][y] < 0) arr[x][y] = 0;
            if (arr[x][y] != 0) queue.add(new Node(x, y));
        }
        board = arr;
    }

    private static boolean isDivide() {
        Queue<Node> q = new ArrayDeque<>();
        Node peek = queue.peek();
        boolean[][] visited = new boolean[N][M];
        visited[peek.x][peek.y] = true;
        q.add(peek);
        int count = 1;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || board[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                q.add(new Node(nx, ny));
                count++;
            }
        }

        return count != queue.size();
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
