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
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int cheeses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    if (board[i][j] == 0 && !visited[i][j]) queue.add(new Node(i, j));
                }
            }
        }

        int answer = 0;
        while (true) {
            int c1 = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 1) c1++;
                }
            }
            boolean flag = false;
            int c2 = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int count = 0;
                    if (board[i][j] == 1) {
                        flag = true;
                        for (int k = 0; k < 4; k++) {
                            int ni = i + dx[k];
                            int nj = j + dy[k];
                            if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                            if (visited[ni][nj]) count++;
                        }
                    }
                    if (count >= 1){
                        c2++;
                        queue.add(new Node(i, j));
                    }
                }
            }
            if (c1 == c2 && c1 != 0) cheeses = c1;
            if (!flag) break;
            bfs();
            answer++;
        }

        System.out.println(answer - 1);
        System.out.println(cheeses);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;
            board[x][y] = 0;
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || board[nx][ny] == 1) continue;
                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
