import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, Q, size;
    private static int[][] board;
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static boolean[][] visited;
    private static int sum = 0;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = (int) Math.pow(2, N);
        board = new int[size][size];
        visited = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        while (Q-- > 0) {
            int L = Integer.parseInt(st.nextToken());
            int divide = (int) Math.pow(2, L);

            for (int i = 0; i < size; i += divide) {
                for (int j = 0; j < size; j += divide) {
                    rotate(i, j, divide);
                }
            }
            melting();
        }
        
        
        calIce();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] > 0 && !visited[i][j]) {
                    queue.add(new Node(i, j));
                    answer = Math.max(answer, bfs());
                }
            }
        }
        
        System.out.println(sum);
        System.out.println(answer);
    }

    private static void calIce() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += board[i][j];
            }
        }
    }

    private static void rotate(int row, int column, int size) {
        int[][] temp = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[j][size - 1 - i] = board[row + i][column + j];
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[row + i][column + j] = temp[i][j];
            }
        }

    }

    private static void melting() {
        int[][] clone = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (board[x][y] == 0) continue;
                int count = 0;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= size || ny < 0 || ny >= size ) continue;
                    if (board[nx][ny] > 0) count++;
                }
                if (count < 3) clone[x][y] = board[x][y] - 1;
                else clone[x][y] = board[x][y];
            }
        }
        board = clone;

    }

    private static int bfs() {
        int count = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= size || ny < 0 || ny >= size || visited[nx][ny] || board[nx][ny] == 0) continue;
                count++;
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }
        }

        return count;
    }


    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
