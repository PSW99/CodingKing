import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int T;
    private static int N, M;
    private static char[][] board;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new char[N + 2][M + 2];
            int[][] fpm, spm, npm;
            fpm = new int[N + 2][M + 2];
            spm = new int[N + 2][M + 2];
            npm = new int[N + 2][M + 2];

            Node[] prisoners = new Node[2];
            int index = 0;

            for (int i = 1; i <= N; i++) {
                String s = br.readLine();
                for (int j = 1; j <= M; j++) {
                    board[i][j] = s.charAt(j - 1);
                    if (board[i][j] == '$') prisoners[index++] = new Node(i, j, 0);
                }
            }

            bfs(new Node(0, 0, 0), npm);
            bfs(prisoners[0], fpm);
            bfs(prisoners[1], spm);

            sb.append(getAnswer(npm, fpm, spm)).append("\n");
        }

        System.out.println(sb);
    }

    private static int getAnswer(int[][] map1, int[][] map2, int[][] map3) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < N ; i++) {
            for (int j = 0; j < M ; j++) {
                if (board[i][j] != '*') {
                    int sum = map1[i][j] + map2[i][j] + map3[i][j];
                    if (board[i][j] == '#') sum -= 2;
                    min = Math.min(min, sum);
                }
            }
        }

        return min;
    }

    private static void bfs(Node start, int[][] visited) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.count));
        boolean[][] isVisit = new boolean[N + 2][M + 2];
        for (int[] row : visited) Arrays.fill(row, Integer.MAX_VALUE);
        visited[start.x][start.y] = 0;
        isVisit[start.x][start.y] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int door = cur.count;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N + 2 || ny < 0 || ny >= M + 2 || isVisit[nx][ny] || board[nx][ny] == '*') continue;

                int newDoor = door;
                if (board[nx][ny] == '#') newDoor++;

                if (visited[nx][ny] > newDoor) {
                    visited[nx][ny] = newDoor;
                    queue.add(new Node(nx, ny, newDoor));
                }
                isVisit[nx][ny] = true;
            }
        }
    }

    private static class Node {
        int x, y, count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}