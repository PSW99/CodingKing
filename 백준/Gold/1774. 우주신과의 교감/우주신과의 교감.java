import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static List<God> list = new ArrayList<>();
    private static List<Edge> graph = new ArrayList<>();
    private static int[] arr;
    private static double answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for (int i = 0; i < N; i++) arr[i] = i;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new God(x, y));
        }

        for (int i = 0; i < N - 1; i++) {
            God a = list.get(i);
            int x1 = a.x;
            int y1 = a.y;
            for (int j = i + 1; j < N; j++) {
                God b = list.get(j);
                int x2 = b.x;
                int y2 = b.y;
                double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                graph.add(new Edge(i + 1, j + 1, distance));
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        graph.sort(Comparator.comparingDouble(o -> o.weight));
        kruskal();
       
        System.out.printf("%.2f", answer);

    }
    private static void kruskal() {
        for (int i = 0; i < graph.size(); i++) {
           Edge edge = graph.get(i);
            if (findParent(edge.start) != findParent(edge.end)) {
                union(edge.start, edge.end);
                answer += edge.weight;
            }
        }
    }
    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) arr[bp] = ap;
    }

    private static int findParent(int x) {
        if (arr[x] == x) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }

    private static class God {
        int x, y;

        public God(int x, int y) {
            this.x = x;
            this.y = y;
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
