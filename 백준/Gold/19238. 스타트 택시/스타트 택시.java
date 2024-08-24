import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, initFuel;
    private static int[][] board;
    private static int sx, sy;
    private static Queue<Taxi> queue = new ArrayDeque<>();
    private static HashMap<Integer, Integer> map = new HashMap<>();
    private static boolean[][] visited;
    private static final int wall = -10;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        initFuel = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) board[i][j] = wall;
            }
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        int num = 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            board[sx][sy] = num;
            map.put(sx * N + sy, ex * N + ey);
            num++;
        }
        
        queue.add(new Taxi(sx, sy, initFuel, 0));
        boolean flag = true;
        while (true) {
            Node node = findPassenger();
            if (node == null){
                flag = false;
                break;
            }

            visited = new boolean[N][N];
            int destination = map.get(node.x * N + node.y);
            board[node.x][node.y] = 0;
            visited[node.x][node.y] = true;
            queue.add(new Taxi(node.x, node.y, node.remainFuel, 0));
            if (!findDestination(destination)) {
                flag = false;
                break;
            }

            visited = new boolean[N][N];

            if (tc()) break;

        }

        if (flag) System.out.println(answer);
        else System.out.println(-1);
    }

    private static boolean tc() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > 0) return false;
            }
        }
        return true;
    }

    private static Node findPassenger() {
        List<Node> list = new ArrayList<>(); // 택시에서 가장 가까운 승객들
        while (!queue.isEmpty()) {
            Taxi cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int fuel = cur.fuel;
            int distance = cur.distance;
            if (board[x][y] > 0) list.add(new Node(x, y, distance, fuel));
            if (fuel == 0) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || board[nx][ny] == wall) continue;
                visited[nx][ny] = true;
                queue.add(new Taxi(nx, ny, fuel - 1, distance + 1));
            }
        }

        if (list.isEmpty()) return null;

        list.sort((o1, o2) -> {
            if (o1.distance != o2.distance) return o1.distance - o2.distance;
            else if (o1.x != o2.x) return o1.x - o2.x;
            else return o1.y - o2.y;
        }); // 택시에서 승객까지 최단거리,행,열 순으로 정렬

        return list.get(0);
    }

    private static boolean findDestination(int destination) {
        int initialFUel = queue.peek().fuel;

        while (!queue.isEmpty()) {
            Taxi cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int fuel = cur.fuel;
            int distance = cur.distance;
            if (fuel == 0) continue;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || board[nx][ny] == wall) continue;
                visited[nx][ny] = true;
                if (nx * N + ny == destination) {
                    queue.clear();
                    int num = (initialFUel - (fuel - 1)) * 2 + fuel - 1;
                    queue.add(new Taxi(nx, ny, num, 0));
                    answer = num;
                    return true;
                }
                queue.add(new Taxi(nx, ny, fuel - 1, distance + 1));
            }
        }

        return false;
    }

    private static class Node {
        int x, y, distance, remainFuel;

        public Node(int x, int y, int distance, int remainFuel) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.remainFuel = remainFuel;
        }

    }

    private static class Taxi {
        int x, y, fuel, distance;

        public Taxi(int x, int y, int fuel, int distance) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
            this.distance = distance;
        }

    }
}