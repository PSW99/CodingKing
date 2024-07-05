import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] adj;
    private static int[] parent;
    private static List<Edge> graph = new ArrayList<>();
    private static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new int[N][N];
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
                if (i < j && adj[i][j] != 0) {
                    graph.add(new Edge(i, j, adj[i][j]));
                }
            }
        }
        
        graph.sort(Comparator.comparingInt(o -> o.weight));
        kruskal();
        System.out.println(answer);
    }

    private static void kruskal() {
        for (Edge edge : graph) {
            if (findParent(edge.start) != findParent(edge.end)) {
                union(edge.start, edge.end);
                answer += edge.weight;
            }
        }
    }

    private static void union(int a, int b) {
        int rootA = findParent(a);
        int rootB = findParent(b);
        if (rootA != rootB) parent[rootB] = rootA;
    }

    private static int findParent(int x) {
        if (parent[x] != x) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}