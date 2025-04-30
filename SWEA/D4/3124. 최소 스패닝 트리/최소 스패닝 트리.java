import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    private static int V, E;
    private static int[] parent;
    private static List<Edge> graph;
    private static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            answer = 0;
            graph = new ArrayList<>();
            parent = new int[V + 1];

            for (int i = 0; i < V + 1; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                graph.add(new Edge(start, end, weight));
            }

            graph.sort(Comparator.comparingInt(o -> o.weight));
            kruskal();

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void kruskal() {
        for (int i = 0; i < E; i++) {
            Edge edge = graph.get(i);

            if (findParent(edge.start) != findParent(edge.end)) {
                union(edge.start, edge.end);
                answer += edge.weight;
            }
        }
    }

    private static int findParent(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = findParent(parent[x]);
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);

        if (bp != ap) parent[bp] = ap;
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
