import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] map;
    private static boolean[][] visited;
    private static Queue<Node> queue = new LinkedList<>();
    private static int min = Integer.MAX_VALUE;
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int island = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    islandNumbering(new Node(i, j), island);
                    island++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x < 0 || x >= N || y < 0 || y >= N) continue;
                    if (map[x][y] == 0) {
                        makeBridge(new Node(i, j, 0), map[i][j]);
                        break;
                    }
                }
            }
        }
        System.out.println(min - 1);
    }

    private static void islandNumbering(Node node, int island) {
        queue.add(node);
        map[node.x][node.y] = island;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] != 1) continue;
                queue.add(new Node(nx, ny, 0));
                visited[nx][ny] = true;
                map[nx][ny] = island;
            }
        }
    }

    private static void makeBridge(Node node,int islandNum) {
        queue = new LinkedList<>();
        queue.add(node);
        visited = new boolean[N][N];
        visited[node.x][node.y] = true;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;
            int distance = curNode.distance;

            if (map[x][y] != 0 && map[x][y] != islandNum) {
                min = Math.min(min, distance);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == islandNum) continue;
                queue.add(new Node(nx, ny, distance + 1));
                visited[nx][ny] = true;
            }
        }
    }


    static class Node {
        int x, y, distance;


        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
