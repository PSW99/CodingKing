import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static int N;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    if (s.charAt(j) == '*') {
                        board[i][j] = -1; // 지뢰
                    } else {
                        board[i][j] = 0; // 빈칸
                    }
                }
            }

            preprocess();

            int answer = 0;

            // 0칸(주변 지뢰 0개)부터 BFS
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 0 && !visited[i][j]) {
                        bfs(i, j);
                        answer++;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] >= 1 && !visited[i][j]) {
                        visited[i][j] = true;
                        answer++;
                    }
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void preprocess() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (board[x][y] == -1) continue;

                int cnt = 0;
                for (int d = 0; d < 8; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (board[nx][ny] == -1) cnt++;
                }
                board[x][y] = cnt;
            }
        }
    }

    private static void bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int cx = cur.x;
            int cy = cur.y;

            if (board[cx][cy] != 0) continue;

            for (int d = 0; d < 8; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (board[nx][ny] == -1) continue;

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