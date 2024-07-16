import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static char[][] maze;
    private static boolean[][][] visited;
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        visited = new boolean[N][M][1 << 6]; // 최대 6개의 열쇠를 사용할 수 있으므로

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = s.charAt(j);
                if (maze[i][j] == '0') {
                    queue.add(new Node(i, j, 0, 0));
                    visited[i][j][0] = true;
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;
            int distance = curNode.distance;
            int bit = curNode.bit;

            if (maze[x][y] == '1') return distance;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nBit = bit;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || maze[nx][ny] == '#') continue;

                // 열쇠 발견
                if ('a' <= maze[nx][ny] && maze[nx][ny] <= 'f') {
                    nBit |= 1 << (maze[nx][ny] - 'a');
                }

                // 문 발견
                if ('A' <= maze[nx][ny] && maze[nx][ny] <= 'F' && (nBit & (1 << (maze[nx][ny] - 'A'))) == 0) {
                    continue;
                }

                if (!visited[nx][ny][nBit]) {
                    visited[nx][ny][nBit] = true;
                    queue.add(new Node(nx, ny, distance + 1, nBit));
                }
            }
        }

        return -1;
    }

    private static class Node {
        int x, y, distance, bit;

        public Node(int x, int y, int distance, int bit) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.bit = bit;
        }
    }
}