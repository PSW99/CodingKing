import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] map;
    private static int sharkSize = 2;
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static Queue<Node> queue = new ArrayDeque<>();
    private static Node shark;
    private static int eatFish = 0;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    shark = new Node(i, j, 0);
                }
            }
        }

        while (true) {
            boolean flag = bfs(shark);
            if (flag) break;
        }
        
       System.out.println(answer);
    }

    private static boolean bfs(Node start) {
        List<Node> list = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;
            int distance = curNode.distance;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] > sharkSize) continue;
                if (map[nx][ny] < sharkSize && map[nx][ny] !=0 ) list.add(new Node(nx, ny, distance + 1));
                queue.add(new Node(nx, ny, distance + 1));
                visited[nx][ny] = true;
            }
        }
        if (list.isEmpty()) return true;
        list.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.distance != o2.distance) return o1.distance - o2.distance;
                else if (o1.x != o2.x) return o1.x - o2.x;
                else return o1.y - o2.y;
            }
        });

        Node node = list.get(0);
        map[node.x][node.y] = 0;
        answer += node.distance;
        eatFish++;
        if (sharkSize == eatFish){
            sharkSize++;
            eatFish = 0;
        }
        shark = new Node(node.x, node.y, 0);

        return false;
    }

    static class Node {
        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
        
    }
}
