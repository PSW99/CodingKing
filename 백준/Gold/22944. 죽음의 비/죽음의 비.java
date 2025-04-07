import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, H, D;
    private static char[][] map;
    private static int[][] visited;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    queue.add(new Node(i, j, H, 0, 0));
                    visited[i][j] = H;
                }
            }
        }

        bfs();
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int hp = cur.hp;
            int dist = cur.distance;
            int umbrella = cur.umbrella;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                int nextHp = hp;
                int nextUmbrella = umbrella;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;

                if (map[nx][ny] == 'E'){
                    answer = Math.min(answer, dist + 1);
                    continue;
                }

                if (map[nx][ny] == 'U') {
                    nextUmbrella = D ;
                }

                if (nextUmbrella > 0) nextUmbrella--;
                else nextHp--;

                if (nextHp <= 0) continue;

                if (visited[nx][ny] < nextHp + nextUmbrella) {
                    visited[nx][ny] = nextHp + nextUmbrella;
                    queue.add(new Node(nx, ny, nextHp, dist + 1, nextUmbrella));
                }
            }
        }
    }

    private static class Node {
        int x, y, hp, distance, umbrella;

        public Node(int x, int y, int hp, int distance, int umbrella) {
            this.x = x;
            this.y = y;
            this.hp = hp;
            this.distance = distance;
            this.umbrella = umbrella;
        }
    }
}