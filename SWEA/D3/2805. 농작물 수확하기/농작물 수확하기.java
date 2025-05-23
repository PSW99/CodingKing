import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    private static int N;
    private static int[][] farm;
    private static boolean[][] visited;
    private static Queue<Node> queue;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            farm = new int[N][N];
            visited = new boolean[N][N];
            queue = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    farm[i][j] = input.charAt(j) - '0';
                }
            }
            int limitDepth = N / 2;

            if (N == 1) {
                System.out.println("#" + test_case + " " + farm[N - 1][N - 1]);
            } else {
                int answer = farm[N / 2][N / 2];
                visited[N / 2][N / 2] = true;
                queue.add(new Node(N / 2, N / 2, 0));

                while (!queue.isEmpty()) {
                    Node cur = queue.poll();
                    int x = cur.x;
                    int y = cur.y;
                    int depth = cur.depth;
                    if (depth >= limitDepth) continue;

                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

                        answer += farm[nx][ny];
                        visited[nx][ny] = true;
                        queue.add(new Node(nx, ny, depth + 1));
                    }
                }

                System.out.println("#" + test_case + " " + answer);
            }


        }
    }

    private static class Node {
        int x, y, depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
