import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static List<Point> point = new ArrayList<>();
    private static List<Edge> graph = new ArrayList<>();
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 0; i < N + 1; i++) arr[i] = i;

        int num = 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            point.add(new Point(x, y, z, num));
            num++;
        }

        point.sort(Comparator.comparingInt(o -> o.x));
        makeEdge();
        point.sort(Comparator.comparingInt(o -> o.y));
        makeEdge();
        point.sort(Comparator.comparingInt(o -> o.z));
        makeEdge();
        kruskal();
        System.out.println(answer);
    }

    private static void makeEdge() {
        for (int i = 0; i < point.size() - 1; i++) {
            Point start = point.get(i);
            int sx = start.x;
            int sy = start.y;
            int sz = start.z;
            int sn = start.num;
            Point end = point.get(i + 1);
            int ex = end.x;
            int ey = end.y;
            int ez = end.z;
            int en = end.num;
            int temp = Math.min(Math.abs(sx - ex), Math.abs(sy - ey));
            int weight = Math.min(temp, Math.abs(sz - ez));
            graph.add(new Edge(sn, en, weight));
        }
    }

    private static void kruskal() {
        graph.sort(Comparator.comparingInt(o -> o.weight));
        for (Edge edge : graph) {
            int start = edge.start;
            int end = edge.end;
            int weight = edge.weight;
            if (findParent(start) != findParent(end)) {
                union(start, end);
                answer += weight;
            }
        }
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) arr[bp] = ap;
    }

    private static int findParent(int x) {
        if (x == arr[x]) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }

    private static class Edge {
        int start,end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    private static class Point {
        int x, y, z, num;

        public Point(int x, int y, int z, int num) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.num = num;
        }
    }
}
