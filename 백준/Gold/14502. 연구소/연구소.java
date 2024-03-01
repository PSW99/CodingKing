import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static boolean[] visited;
    private static boolean[][] booleans;
    private static Queue<Node> queue = new LinkedList<>();
    private static int max = Integer.MIN_VALUE;
    private static int[] dx = { 0, 1, 0, -1 }, dy = {-1, 0, 1, 0};
    private static List<int[]> list = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    int[] virus = {i, j};
                    list.add(virus);
                }
            }
        }
        visited = new boolean[N];
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = map[i].clone();
        }
        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int k) {
        if (k == 3) {
            bfs();
            return;
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] ==0){ // 빈칸이면
                    map[i][j] = 1; // 벽세우기
                    dfs(k+1);
                    map[i][j] = 0;
                }
            }
        }
    }
    private static void bfs() {
        booleans = new boolean[N][M];
        int[][] clone = new int[N][M];
        for(int i=0; i<N; i++){ // 깊은 복사
            clone[i] = map[i].clone();
        }
        for (int[] virus : list) {
            queue.add(new Node(virus[0], virus[1]));
            booleans[virus[0]][virus[1]] = true;
        }

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || booleans[nx][ny] || clone[nx][ny] !=0 ) continue;
                clone[nx][ny] = 2;
                queue.add(new Node(nx, ny));
                booleans[nx][ny] = true;
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (clone[i][j] ==0) cnt++;
            }
        }
        max = Math.max(max, cnt);

    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
