import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    private static int[][] board = new int[8][8];
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
    private static List<Node> list = new ArrayList<>();
    private static boolean flag = false;
    private static boolean[][] visited = new boolean[8][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == '#') list.add(new Node(i, j));
            }
        }
        queue.add(new Node(7, 0));
        visited[7][0] = true;

        while (true) {
            move();
            if (queue.isEmpty()){
                System.out.println(0);
                break;
            }
            if (flag) {
                System.out.println(1);
                break;
            }
            down();
        }

    }

    private static void move() {
        int s = queue.size();
        while (s-- > 0) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            if (x>0 && board[x - 1][y] == '.')queue.add(new Node(x, y));

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8 || visited[nx][ny] || board[nx][ny] == '#') continue;
                if (nx > 0 && board[nx - 1][ny] == '#') continue;
                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;
                if (nx == 0 && ny == 7){
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }

    }

    private static void down() {
        list.sort(((o1, o2) -> o2.x - o1.x));
        Queue<Node> q = new ArrayDeque<>(list);
        list = new ArrayList<>();
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x, y = cur.y;
            board[x][y] = '.';
            if (x + 1 >= 8) continue;
            board[x + 1][y] = '#';
            list.add(new Node(x + 1, y));
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
