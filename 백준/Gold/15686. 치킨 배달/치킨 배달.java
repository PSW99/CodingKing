import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static boolean[] visited;
    private static List<Node> home = new ArrayList<>();
    private static List<Node> chickenHome = new ArrayList<>();
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) home.add(new Node(i, j));
                else if (map[i][j] ==2) chickenHome.add(new Node(i, j));
            }
        }

        visited = new boolean[chickenHome.size()];
        backTracking(0,0);
        System.out.println(min);
    }

    private static void backTracking(int start,int count) {
        if (count == M) {
            calMinDistance();
            return;
        }

        for (int i = start; i < chickenHome.size(); i++) {
            visited[i] = true;
            backTracking( i + 1, count + 1);
            visited[i] = false;
        }
    }

    private static void calMinDistance() {
        int res = 0;

        for (Node node : home) {
            int temp = Integer.MAX_VALUE;
            for (int j = 0; j < chickenHome.size(); j++) {
                if (visited[j]) {
                    int dist = Math.abs(node.x - chickenHome.get(j).x)
                            + Math.abs(node.y - chickenHome.get(j).y);
                    temp = Math.min(temp, dist);
                }
            }
            res += temp;
        }
        min = Math.min(min, res);
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
