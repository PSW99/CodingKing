import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    private static int N;
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static int cnt = 0;
    private static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        char[][] nMap = new char[N][N]; //일반인 맵
        char[][] cbMap = new char[N][N];//적록색약 맵
        boolean[][] nVisited = new boolean[N][N];
        boolean[][] cbVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char rgb = s.charAt(j);
                nMap[i][j] = rgb;
                if (rgb == 'R') rgb = 'G';
                cbMap[i][j] = rgb;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (nVisited[i][j]) continue;
                bfs(new Node(i, j), nMap, nVisited);
            }
        }
        sb.append(cnt).append(" ");
        cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cbVisited[i][j]) continue;
                bfs(new Node(i, j), cbMap, cbVisited);
            }
        }

        sb.append(cnt);

        System.out.println(sb);
    }

    private static void bfs(Node node, char[][] map,boolean[][] booleans) {
        queue.add(node);
        booleans[node.x][node.y] = true;
        while (!queue.isEmpty()) {
            Node myNode = queue.poll();
            int x = myNode.x;
            int y = myNode.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || booleans[nx][ny] || map[x][y] != map[nx][ny]) continue;
                queue.add(new Node(nx, ny));
                booleans[nx][ny] = true;
            }
        }
        cnt++;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
