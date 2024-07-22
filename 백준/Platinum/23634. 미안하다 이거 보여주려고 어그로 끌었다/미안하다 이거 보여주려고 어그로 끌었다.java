import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, answer, count;
    private static int[][] board, group;
    private static boolean[][] visited;
    private static int[] parent, size;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private static Queue<Node> queue = new ArrayDeque<>();
    private static HashMap<Integer, Integer> map = new HashMap<>();
    private static List<Node> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        parent = new int[N * M];
        size = new int[N * M];
        group = new int[N][M];


        for (int i = 0; i < N * M; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j) - '0';
                if (board[i][j] == 0) count++;
            }
        }
        init();

        int region = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0 && !visited[i][j]) {
                    queue.add(new Node(i, j));
                    group[i][j] = region;
                    visited[i][j] = true;
                    list.add(new Node(i, j));
                    grouping(region++);
                }
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    queue.add(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!tc()) {
            bfs();
            answer++;
        }

        if (count == 0) System.out.println(0 + " " + 0);
        else {
            int sum = 0;
            for (Node node : list) {
                int i = node.x * M + node.y;
                sum += size[findParent(i)];
            }
            System.out.println(answer + " " + sum);
        }
    }

    private static boolean tc() {
        for (Node node : list) {
            int n = node.x * M + node.y;
            int region = group[node.x][node.y];
            if (size[findParent(n)] != map.get(region)) return false;
        }
        return true;
    }

    private static void grouping(int region) {
        int count = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            if (board[x][y] == 0) count++;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == 2 || visited[nx][ny]) continue;
                group[nx][ny] = region;
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }
        }

        map.put(region, count);
    }

    private static void bfs() {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] != 1 || visited[nx][ny]) continue;
                board[nx][ny] = 0;
                cnt++;
                int a = x * M + y;
                int b = nx * M + ny;
                visited[nx][ny] = true;
                union(a, b);

                for (int k = 0; k < 4; k++) {
                    int nnx = nx + dx[k];
                    int nny = ny + dy[k];
                    if (nnx >= 0 && nnx < N && nny >= 0 && nny < M && board[nnx][nny] == 0) union(b, nnx * M + nny);
                }
                queue.add(new Node(nx, ny));
                count++;
            }

            int num = map.get(group[x][y]) + cnt;
            map.replace(group[x][y], num);
        }

    }

    private static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] != 0) continue;
                        int a = i * M + j;
                        int b = nx * M + ny;
                        union(a, b);
                    }
                }
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

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
