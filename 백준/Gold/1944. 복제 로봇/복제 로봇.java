import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static char[][] board;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private static Queue<Robot> queue = new ArrayDeque<>();
    private static List<Robot> list = new ArrayList<>();
    private static List<Edge> graph = new ArrayList<>();
    private static int[] arr;
    private static boolean[][] visited;
    private static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][N];
        Robot parent = null;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'S') parent = new Robot(i, j, 0);
                if (board[i][j] == 'K') list.add(new Robot(i, j, 0));
            }
        }

        arr = new int[N * N + 1];
        size = new int[N * N + 1];
        for (int i = 0; i < N * N + 1; i++) {
            arr[i] = i;
            size[i] = 1;
        }

        bfs(parent);
        for (Robot robot : list) bfs(robot);

        graph.sort(Comparator.comparingInt(o -> o.weight));
        int answer = 0;
        for (Edge edge : graph) {
            int a = edge.start;
            int b = edge.end;
            if (findParent(a) != findParent(b)) {
                union(a,b);
                answer += edge.weight;
            }
        }

        int a = parent.x * N + parent.y;
        if (size[findParent(a)] != M + 1) System.out.println(-1);
        else System.out.println(answer);


    }


    private static void bfs(Robot start) {
        int a = start.x * N + start.y;
        queue.add(start);
        visited = new boolean[N][N];
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Robot cur = queue.poll();
            int x = cur.x, y = cur.y, distance = cur.distance;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N ) continue;
                if (board[nx][ny] == '1' || visited[nx][ny]) continue;
                int b = nx * N + ny;
                if (board[nx][ny] == 'K') graph.add(new Edge(a, b, distance + 1));
                queue.add(new Robot(nx, ny, distance + 1));
                visited[nx][ny] = true;
            }
        }
    }


    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) {
            arr[bp] = ap;
            size[ap] += size[bp];

        }
    }

    private static int findParent(int x) {
        if (arr[x] == x) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }

    private static class Robot {
        int x, y, distance;

        public Robot(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    private static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
