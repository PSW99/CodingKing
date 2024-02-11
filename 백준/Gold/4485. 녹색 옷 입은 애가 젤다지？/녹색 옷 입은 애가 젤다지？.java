import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
    private static int N;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[][] dist;
    private static int[] dx = { 0, 1, 0, -1 };
    private static int[] dy = { -1, 0, 1, 0 };
    private static int index = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            arr = new int[N][N];
            visited = new boolean[N][N];
            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            bfs(0, 0);
        }
    }

    private static void bfs(int x,int y) {
        queue.add(new Node(x, y, arr[x][y]));
        dist[x][y] = arr[x][y];
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.x == N - 1 && curNode.y == N - 1) {
                System.out.println("Problem "+index+": " + curNode.cost);
                index++;
            }

            if (dist[curNode.x][curNode.y]< curNode.cost) continue;

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new Node(nx, ny, curNode.cost + arr[nx][ny]));
                    }
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
