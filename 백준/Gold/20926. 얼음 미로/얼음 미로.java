import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int W , H;
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static String[][] map;
    private static boolean[][] visited;
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new String[H][W];
        visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            String[] strings = br.readLine().split("");
            map[i] = strings;
            for (int j = 0; j < W; j++) {
                if (map[i][j].equals("T")){
                    pq.add(new Node(i, j, 0));
                    map[i][j] = "0";
                }
            }
        }

        dijkstra();
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);

    }

    private static void dijkstra() {

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int x = curNode.x;
            int y = curNode.y;

            if (visited[x][y]) continue;
            visited[x][y] = true;

            for (int i = 0; i < 4; i++) {
                Node go = isGo(curNode, i);
                if (go == null) continue;
                pq.add(go);
            }
        }
    }

    private static Node isGo(Node node, int direction) {
        int nx = node.x;
        int ny = node.y;
        int weight = node.weight;
        while (true) {
            nx += dx[direction];
            ny += dy[direction];
            if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny].equals("H")) break;
            if (map[nx][ny].equals("R")) return new Node(nx-dx[direction], ny-dy[direction], weight);
            if (map[nx][ny].equals("E")) {
                min = Math.min(min, weight);
                break;
            }

            weight += Integer.parseInt(map[nx][ny]);
        }
        return null;
    }

    private static class Node {
        int x, y, weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

    }
}
