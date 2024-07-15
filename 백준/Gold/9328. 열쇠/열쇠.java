import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int T;
    private static int N, M;
    private static String key;
    private static boolean[] hasKey;
    private static char[][] board;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new char[N + 2][M + 2];
            hasKey = new boolean[26];
            queue = new ArrayDeque<>();
            count = 0;

            for (int i = 1; i <= N; i++) {
                String s = br.readLine();
                for (int j = 1; j <= M; j++) {
                    board[i][j] = s.charAt(j - 1);
                }
            }

            key = br.readLine();
            for (int i = 0; i < key.length(); i++) {
                if (key.equals("0")) break;
                hasKey[key.charAt(i) - 97] = true;
            }

            visited = new boolean[N + 2][M + 2];
            visited[0][0] = true;
            queue.add(new Node(0, 0));
            bfs();
            System.out.println(count);
        }

    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N + 2 || ny < 0 || ny >= M + 2 || visited[nx][ny] || board[nx][ny] == '*') continue;

                //문서발견
                if (board[nx][ny] == '$') {
                    count++;
                    board[nx][ny] = '.';
                }
                //문
                if (65 <= board[nx][ny] && board[nx][ny] <= 90) {
                    int key = board[nx][ny] - 65;
                    if (hasKey[key]) board[nx][ny] = '.';
                    else continue;
                }

                //열쇠 발견
                if (97 <= board[nx][ny] && board[nx][ny] <= 122) {
                    int key = board[nx][ny] - 97;
                    hasKey[key] = true;
                    board[nx][ny] = '.';
                    visited = new boolean[N + 2][M + 2];
                    queue.clear();
                }

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));

            }
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
