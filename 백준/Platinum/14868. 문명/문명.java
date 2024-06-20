import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static Node[][] map;
    private static int[] parent;
    private static int[] size;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int answer = 0;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new Node[N][N];
        parent = new int[N * N];
        size = new int[N * N];
        for (int i = 0; i < N * N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int num = 0;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int pos = x * N + y;
            map[x][y] = new Node(x, y);
            queue.add(new Node(x, y));
            count++;
            num = pos;
        }

        init();

        while (size[findParent(num)] != count) {
            bfs();
            answer++;
        }
        System.out.println(answer);
    }

    private static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != null) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == null) continue;
                        int a = i * N + j;
                        int b = nx * N + ny;
                        union(a, b);
                    }
                }
            }
        }
    }

    private static void bfs() {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != null) continue;

                map[nx][ny] = new Node(nx, ny);
                int a = x * N + y;
                int b = nx * N + ny;
                union(a, b);

                for (int k = 0; k < 4; k++) {
                    int nnx = nx + dx[k];
                    int nny = ny + dy[k];
                    if (nnx >= 0 && nnx < N && nny >= 0 && nny < N && map[nnx][nny] != null) {
                        union(b, nnx * N + nny);
                    }
                }

                queue.add(new Node(nx, ny));
                count++;
            }
        }
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) {
            parent[bp] = ap;
            size[ap] += size[bp];
        }
    }

    private static int findParent(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = findParent(parent[x]);
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
