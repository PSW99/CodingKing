import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int R, C;
    private static char[][] map;
    private static int[] arr;
    private static boolean[][] visited;
    private static int answer = 0;
    private static int[] L = new int[2];
    private static List<Node> list;
    private static Queue<Node> meltingQueue = new LinkedList<>();
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        arr = new int[R * C];

        int index = 0;
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++){
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'L') {
                    map[i][j] = '.';
                    L[index] = i * C + j;
                    index++;
                }
            }
        }

        for (int i = 0; i < R * C; i++) arr[i] = i;

        visited = new boolean[R][C];
        Queue<Node> bfsQueue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.' && !visited[i][j]) {
                    visited[i][j] = true;
                    unionBfs(new Node(i, j), bfsQueue);
                }
            }
        }

        int L1 = L[0];
        int L2 = L[1];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        if (ni < 0 || ni >= R || nj < 0 || nj >= C) continue;
                        if (map[ni][nj] == 'X') {
                            meltingQueue.add(new Node(i, j));
                            break;
                        }
                    }
                }
            }
        }

        while (findParent(L1) != findParent(L2)) {
            list = new ArrayList<>();
            Queue<Node> nextQueue = new LinkedList<>();
            while (!meltingQueue.isEmpty()) {
                Node curNode = meltingQueue.poll();
                int x = curNode.x;
                int y = curNode.y;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == '.') continue;
                    if (map[nx][ny] == 'X') {
                        map[nx][ny] = '.';
                        nextQueue.add(new Node(nx, ny));
                        union(new Node(x, y), new Node(nx, ny));

                        for (int j = 0; j < 4; j++) {
                            int nnx = nx + dx[j];
                            int nny = ny + dy[j];
                            if (nnx >= 0 && nnx < R && nny >= 0 && nny < C && map[nnx][nny] == '.') {
                                union(new Node(nx, ny), new Node(nnx, nny));
                            }
                        }
                    }
                }
            }
            meltingQueue = nextQueue;
            answer++;
        }

        System.out.println(answer);
    }

    private static void unionBfs(Node node, Queue<Node> bfsQueue) {
        bfsQueue.add(node);
        while (!bfsQueue.isEmpty()) {
            Node curNode = bfsQueue.poll();
            int x = curNode.x;
            int y = curNode.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] || map[nx][ny] == 'X') continue;
                visited[nx][ny] = true;
                Node nNode = new Node(nx, ny);
                bfsQueue.add(nNode);
                union(node, nNode);
            }
        }
    }

    private static void union(Node a, Node b) {
        int ap = findParent(a.x * C + a.y);
        int bp = findParent(b.x * C + b.y);
        if (ap != bp) arr[bp] = ap;
    }

    private static int findParent(int n) {
        if (n == arr[n]) return arr[n];
        else return arr[n] = findParent(arr[n]);
    }

    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
