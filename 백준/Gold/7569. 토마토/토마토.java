import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, H;
    private static int[] dx = {0, 1, 0, -1, 0, 0}, dy = {-1, 0, 1, 0, 0, 0}, dz = {0, 0, 0, 0, 1, -1};
    private static Queue<Node> queue = new LinkedList<>();
    private static int[][][] map;
    private static boolean[][][] booleans;
    private static List<Node> list = new ArrayList<>();
    private static int[][][] arr;
    private static int zeroCnt = 0 , cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];
        booleans = new boolean[H][N][M];
        arr = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 0) zeroCnt++;
                    if (map[i][j][k] == 1) list.add(new Node(i, j, k));
                }
            }
        }
        bfs();

        if (zeroCnt != cnt) {
            System.out.println(-1);
        } else {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        max = Math.max(max, arr[i][j][k]);
                    }
                }
            }
            System.out.println(max);
        }

    }

    private static void bfs() {
        for (Node node : list) {
            queue.add(new Node(node.z, node.x, node.y));
            booleans[node.z][node.x][node.y] = true;
        }
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;
            int z = curNode.z;

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || nz <0 || nz >=H || booleans[nz][nx][ny] || map[nz][nx][ny] != 0) continue;
                queue.add(new Node(nz, nx, ny));
                booleans[nz][nx][ny] = true;
                arr[nz][nx][ny] = arr[z][x][y] + 1;
                cnt++;
            }
        }

    }

    static class Node {
        int z,x,y;

        public Node(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}
