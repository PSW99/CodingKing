import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static List<Star> stars = new ArrayList<>();
    private static List<Edge> graph = new ArrayList<>();
    private static int[] arr;
    private static double answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 0; i < N + 1; i++) arr[i] = i;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            stars.add(new Star(x, y, i + 1));
        }
        makeEdge();
        kruskal();
        System.out.println(answer);
    }

    private static void kruskal() {
        graph.sort(Comparator.comparingDouble(o -> o.weight));
        for (Edge edge : graph) {
            int start = edge.start;
            int end = edge.end;
            if (findParent(start) != findParent(end)) {
                union(start, end);
                answer += edge.weight;
            }
        }
    }

    private static void makeEdge() {
        for (int i = 0; i < N; i++) {
            Star start = stars.get(i);
            double sx = start.x;
            double sy = start.y;
            int sn = start.num;
            for (int j = i + 1; j < N; j++) {
                Star end = stars.get(j);
                double ex = end.x;
                double ey = end.y;
                int en = end.num;
                double weight = Math.sqrt(Math.pow(ex - sx, 2) + Math.pow(ey - sy, 2));
                graph.add(new Edge(sn, en, weight));
            }
        }
    }

    private static int findParent(int x) {
        if (x == arr[x]) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) arr[bp] = ap;
    }

    private static class Star {
        double x, y;
        int num;

        public Star(double x, double y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    private static class Edge {
        int start, end;
        double weight;

        public Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
