import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] arr;
    private static List<Edge> list = new ArrayList<>();
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int island = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    islandNumbering(new Node(i, j), island);
                    island++;
                }
            }
        }

        arr = new int[island];
        for (int i = 0; i < island; i++) arr[i] = i;

        for (int i = 1; i < island; i++) {
            for (int j = i + 1; j < island; j++) {
                makeBridge(i, j);
            }
        }

        kruskal();

        if (answer == 0 || !isConnect())System.out.println(-1);
        else System.out.println(answer);


    }

    private static boolean isConnect() {
        int parent = findParent(1);
        for (int i = 2; i < arr.length; i++) {
            if (parent != findParent(i)) return false;
        }
        return true;
    }

    private static void kruskal() {
        list.sort(Comparator.comparingInt(o -> o.distance));
        for (Edge edge : list) {
            int start = edge.start;
            int end = edge.end;
            int distance = edge.distance;
            if (distance < 2 ) continue;

            if (findParent(start) != findParent(end)) {
                union(start, end);
                answer += distance;
            }
        }
    }

    private static void makeBridge(int start,int end) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == start) {
                    for (int k = 0; k < 4; k++) {
                        int num = calDistance(new Node(i, j), end, k);
                        if (num < 2) continue;
                        min = Math.min(min, num);
                    }
                }
            }
        }
        if (min != Integer.MAX_VALUE) list.add(new Edge(start, end, min));
    }

    private static int calDistance(Node start, int end, int dist) {
        int x = start.x;
        int y = start.y;
        int islandNum = map[x][y];
        int len= -1;
        while (true) {
            int nx = x + dx[dist];
            int ny = y + dy[dist];
            len++;
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == islandNum ) break;
            if (map[nx][ny] != 0 && map[nx][ny] != end) break;
            if (map[nx][ny] == end) return len;

            x = nx;
            y = ny;
        }

        return -1;
    }

    private static void islandNumbering(Node node, int island) {
        queue.add(node);
        map[node.x][node.y] = island;
        visited[node.x][node.y] = true;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] != 1) continue;
                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;
                map[nx][ny] = island;
            }
        }
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        arr[bp] = ap;
    }

    private static int findParent(int x) {
        if (x == arr[x]) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge {
        int start, end, distance;

        public Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }
}
