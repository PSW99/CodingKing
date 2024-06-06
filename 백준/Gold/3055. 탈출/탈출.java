import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C;
    private static char[][] map;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    private static Queue<Node> wq = new ArrayDeque<>();
    private static Queue<Node> sq = new ArrayDeque<>();
    private static boolean[][] visited;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '*') wq.add(new Node(i, j));
                if (map[i][j] == 'S'){
                    sq.add(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }
        boolean flag = true;
        while (true) {
            fillWater();

            if (move()) break;

            if (sq.isEmpty()){
                flag = false;
                break;
            }
            answer++;
        }
        if (!flag) System.out.println("KAKTUS");
        else System.out.println(answer + 1);

    }

    private static void fillWater() {
        int size = wq.size();
        for (int i = 0; i < size; i++) {
            Node cur = wq.poll();
            int x = cur.x;
            int y = cur.y;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == 'D' || map[nx][ny] == 'X' || map[nx][ny] =='*') continue;
                map[nx][ny] = '*';
                wq.add(new Node(nx, ny));
            }
        }
    }

    private static boolean move() {
        int size = sq.size();
        for (int i = 0; i < size; i++) {
            Node cur = sq.poll();
            int x = cur.x;
            int y = cur.y;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] || map[nx][ny] == '*' || map[nx][ny] == 'X') continue;
                if (map[nx][ny] == 'D') return true;
                map[nx][ny] = 'S';
                visited[nx][ny] = true;
                sq.add(new Node(nx, ny));
            }
        }
        return false;
    }



    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
