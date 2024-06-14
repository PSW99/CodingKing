import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static Queue<Node> queue = new ArrayDeque<>();
    private static HashMap<Integer, Integer> hashMap = new HashMap<>();
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static int index = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
                if (map[i][j] == 1) map[i][j] = -1;
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1 || visited[i][j]) continue;
                visited[i][j] = true;
                queue.add(new Node(i, j));
                bfs();
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int count = 1;

                if (map[i][j] != -1){
                    sb.append(0);
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == -1) continue;
                    set.add(map[nx][ny]);
                }
                for (int key : set) count += hashMap.get(key);
                set.clear();
                sb.append(count % 10);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        int count = 1;
        List<Node> list = new ArrayList<>();
        list.add(queue.peek());
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == -1) continue;
                visited[nx][ny] = true;
                Node node = new Node(nx, ny);
                queue.add(node);
                list.add(node);
                count++;
            }
        }
        hashMap.put(index, count);
        for (Node node : list) map[node.x][node.y] = index;
        index++;
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
