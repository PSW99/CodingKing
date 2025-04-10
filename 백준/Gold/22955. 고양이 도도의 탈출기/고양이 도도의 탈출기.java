import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static char[][] map;
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.stamina));
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static int[][] visited;
    private static int ex, ey;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new int[N][M];


        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'C') {
                    pq.add(new Node(i, j, 0));
                }
                if (map[i][j] == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }

        dijkstra();

        if (visited[ex][ey] == 0) {
            System.out.println("dodo sad");
        } else {
            System.out.println(visited[ex][ey]);
        }

    }

    private static void dijkstra() {
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.x][cur.y] < cur.stamina) continue;


            for (int i = 0; i < 4; i++) {
                Node next = canGo(cur, i);
                if (next == null) {
                    continue;
                }

                if (visited[next.x][next.y] > next.stamina || visited[next.x][next.y] == 0) {
                    visited[next.x][next.y] = next.stamina;
                    pq.add(next);
                }
            }
        }
    }

    private static Node canGo(Node cur, int dir) {
        int x = cur.x;
        int y = cur.y;
        int stamina = cur.stamina;

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 'D' || map[nx][ny] == 'C') return null;

        if (dir == 0 || dir == 2) {
            if (map[nx][ny] == 'F' || map[nx][ny] == 'E' || map[nx][ny] == 'L') {
                return new Node(nx, ny, stamina + 1);
            } else {
                int fx = nx;
                int fy = ny;
                while (true) {
                    fx += dx[1];
                    fy += dy[1];

                    if (fx < 0 || fx >= N || fy < 0 || fy >= M || map[fx][ny] == 'D' || map[fx][fy] == 'C') return null;

                    if (map[fx][fy] == 'F' || map[fx][fy] == 'E' || map[fx][fy] == 'L') {
                        return new Node(fx, fy, stamina + 11);
                    }
                }
            }

        } else {
            if (dir == 1 && map[nx][ny] == 'L') {
                return new Node(nx, ny, stamina + 5);
            }
            if (dir == 3 && map[x][y] == 'L' && map[nx][ny] != 'X') {
                return new Node(nx, ny, stamina + 5);
            }

        }

        return null;
    }

    private static class Node {
        int x, y, stamina;

        public Node(int x, int y, int stamina) {
            this.x = x;
            this.y = y;
            this.stamina = stamina;
        }
    }
}
