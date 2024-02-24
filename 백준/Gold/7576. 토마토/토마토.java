import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int M, N;
    private static int[][] map;
    private static boolean[][] booleans;
    private static Queue<Node> queue = new LinkedList<>();
    private static List<Node> list;
    private static int[][] arr;
    private static int zeroCnt = 0;
    private static int visitCnt = 0;
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        arr = new int[N][M];
        booleans = new boolean[N][M];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) list.add(new Node(i, j));

                if (map[i][j] == 0) zeroCnt++;
             }
        }
        bfs();
        if (zeroCnt != visitCnt) {
            System.out.println(-1);
        } else {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    max = Math.max(max, arr[i][j]);
                }
            }
            System.out.println(max);
        }
    }

    private static void bfs() {
        for (Node node : list) {
            queue.add(node);
            booleans[node.x][node.y] = true;
        }
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            int x = curNode.x;
            int y = curNode.y;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || booleans[nx][ny] || map[nx][ny] != 0) continue;
                queue.add(new Node(nx, ny));
                arr[nx][ny] = arr[x][y] + 1;
                booleans[nx][ny] = true;
                visitCnt++;
            }
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
