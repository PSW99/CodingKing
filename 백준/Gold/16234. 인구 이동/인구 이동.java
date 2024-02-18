import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, L, R;
    private static int[][] arr;
    private static boolean[][] booleans;
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static Queue<Node> queue = new LinkedList<>();
    private static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        int b = 0;
        while (true) {
            booleans = new boolean[N][N];
            int a = b;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (booleans[i][j]) continue;
                    int cnt = bfs(new Node(i, j), booleans);
                    if (1 < cnt) b++;
                }
            }
            if(a != b) answer++;
            else break;
        }

        System.out.println(answer);
    }

    private static int bfs(Node node, boolean[][] booleans) {
        queue.add(node);
        list = new ArrayList<>();
        int sum = arr[node.x][node.y];
        int[] element1 = {node.x, node.y};
        list.add(element1);
        int count = 1;
        booleans[node.x][node.y] = true;
        while (!queue.isEmpty()) {
            Node myNode = queue.poll();
            int x = myNode.x;
            int y = myNode.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                int n = Math.abs(arr[x][y] - arr[nx][ny]);
                if (n >= L && n <= R && !booleans[nx][ny]) {
                    int[] element2 = {nx, ny};
                    list.add(element2);
                    booleans[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                    sum += arr[nx][ny];
                    count++;
                }
            }
        }

        int move = sum / count;
        for (int i = 0; i < list.size(); i++) {
            int[] coordinate = list.get(i);
            int x = coordinate[0];
            int y = coordinate[1];
            arr[x][y] = move;
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
