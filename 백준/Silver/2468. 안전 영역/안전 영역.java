import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] arr;
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, arr[i][j]);
            }
        }

        int answer = 1;
        for (int i = 0; i < max; i++) {
            answer = Math.max(answer, bfs(i));
        }
        System.out.println(answer);
    }

    private static int bfs(int n) {
        boolean[][] booleans = new boolean[N][N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] > n && !booleans[i][j]) {
                    queue.add(new Node(i, j));
                    booleans[i][j] = true;
                    while (!queue.isEmpty()) {
                        Node myNode = queue.poll();
                        int x = myNode.x;
                        int y = myNode.y;
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (nx < 0 || nx >= N || ny < 0 || ny >= N || arr[nx][ny] <= n || booleans[nx][ny]) continue;
                            queue.add(new Node(nx, ny));
                            booleans[nx][ny] = true;
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
