import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static char[][] arr;
    private static Queue<Node> queue = new LinkedList<>();
    private static int[] dx = { 0, 1, 0, -1 }, dy = {-1, 0, 1, 0};
    private static int N , M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        int[][] dijkstra;
        boolean[][] booleans;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
            }
        }
        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'L') {
                    max = Math.max(max, bfs(new Node(i, j)));
                }
            }
        }
        System.out.println(max);
    }

    private static int bfs(Node node) {
        boolean[][] booleans = new boolean[N][M];
        int[][] dijkstra = new int[N][M];
        int max = 0;
        queue.add(node);
        booleans[node.x][node.y] = true;
        while (!queue.isEmpty()) {
            Node myNode = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = myNode.x + dx[i];
                int ny = myNode.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M){
                    if (arr[nx][ny] == 'L' && !booleans[nx][ny]) {
                        queue.add(new Node(nx, ny));
                        dijkstra[nx][ny] = dijkstra[myNode.x][myNode.y] + 1;
                        max = Math.max(max, dijkstra[nx][ny]);
                        booleans[nx][ny] = true;
                    }
                }
            }
        }
        return max;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
