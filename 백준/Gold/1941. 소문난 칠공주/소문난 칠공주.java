import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    private static char[][] board = new char[5][5];
    private static boolean[] check = new boolean[25];
    private static boolean[][] visited;
    private static Queue<Node> queue;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        combination(0, 0, 0);
        System.out.println(answer);
    }

    private static void combination(int count, int start, int s) {
        if (count == 7) {
            if (s >= 4) {
                queue = new ArrayDeque<>();
                visited = new boolean[5][5];
                for (int i = 0; i < 25; i++) {
                    if (check[i]) {
                        queue.add(new Node(i / 5, i % 5));
                        visited[i / 5][i % 5] = true;
                        break;
                    }
                }

                int cnt = 1;

                while (!queue.isEmpty()) {
                    Node curNode = queue.poll();
                    int x = curNode.x;
                    int y = curNode.y;

                    if (cnt == 7) {
                        answer++;
                        break;
                    }

                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny]) continue;
                        if (!check[nx * 5 + ny]) continue;
                        visited[nx][ny] = true;
                        queue.add(new Node(nx, ny));
                        cnt++;
                    }
                }

            }
            return;

        }

        for (int i = start; i < 25; i++) {
            check[i] = true;
            if (board[i / 5][i % 5] == 'S') combination(count + 1, i + 1, s + 1);
            else combination(count + 1, i + 1, s);
            check[i] = false;
        }
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
