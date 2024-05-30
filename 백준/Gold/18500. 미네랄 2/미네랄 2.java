import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int R, C, N;
    private static char[][] arr;
    private static Queue<Node> queue = new ArrayDeque<>();
    private static boolean[][] visited;
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    private static int mCnt = 0; // 미네랄 갯수
    private static boolean flag = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = s.charAt(j);
                if (arr[i][j] == 'x') mCnt++;
            }
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (flag) {
                flag = false;
                for (int j = 0; j < C; j++) {
                    if (arr[R - n][j] == 'x') {
                        arr[R - n][j] = '.';
                        mCnt--;
                        break;
                    }
                }
            } else {
                flag = true;
                for (int j = C - 1; j >= 0; j--) {
                    if (arr[R - n][j] == 'x') {
                        arr[R - n][j] = '.';
                        mCnt--;
                        break;
                    }
                }
            }

            visited = new boolean[R][C];
            for (int j = 0; j < C; j++) {
                if (arr[R - 1][j] == 'x' && !visited[R - 1][j]) {
                    queue.add(new Node(R - 1, j));
                    visited[R - 1][j] = true;
                }
            }
            if (!isCluster()) move();

        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isCluster() {
        int count = 1;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] || arr[nx][ny] == '.') continue;
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
                count++;
            }
        }
        return mCnt == count;
    }

    private static void move() {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && arr[i][j] == 'x') {
                    list.add(new Node(i, j));
                    arr[i][j] = '.';
                }
            }
        }

        if (list.isEmpty()) return;
        list.sort((o1, o2) -> o2.x - o1.x);
        int min = Integer.MAX_VALUE;

        for (Node node : list) {
            int count = -1;
            int x = node.x;
            int y = node.y;
            while (true) {
                count++;
                x += 1;
                if (x >= R || arr[x][y] == 'x') break;
            }
            min = Math.min(min, count);
        }

        queue.addAll(list);

        while (!queue.isEmpty()) {
            int cnt = 0;
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;

            while (true) {
                x += 1;
                cnt++;

                if (x >= R || arr[x][y] == 'x') {
                    arr[x - 1][y] = 'x';
                    break;
                }
                if (cnt == min) {
                    arr[x][y] = 'x';
                    break;
                }
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
