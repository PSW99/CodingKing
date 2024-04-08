import javax.swing.plaf.LabelUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K;
    private static int S, X, Y;
    private static int[][] map;
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.virus));
    private static List<Node> list;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) pq.add(new Node(i, j, map[i][j]));
            }
        }

        for (int i = 0; i < S; i++) {
            bfs();
            pq.addAll(list);
        }
        System.out.println(map[X - 1][Y - 1]);
    }

    private static void bfs() {
        list = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int x = curNode.x;
            int y = curNode.y;
            int virus = curNode.virus;
            boolean isZero = false;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (map[nx][ny] ==0) {
                    isZero = true;
                    break;
                }
            }

            if (isZero) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] !=0 ) continue;
                    map[nx][ny] = virus;
                    list.add(new Node(nx, ny, virus));
                }
            }

        }
    }

    private static class Node {
        int x, y, virus;

        public Node(int x, int y, int virus) {
            this.x = x;
            this.y = y;
            this.virus = virus;
        }

    }
}
