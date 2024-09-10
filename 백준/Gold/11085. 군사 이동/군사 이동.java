import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int p, w, c, v;
    private static List<Edge> graph = new ArrayList<>();
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        parent = new int[p];
        for (int i = 0; i < p; i++) parent[i] = i;

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            graph.add(new Edge(start, end, width));
        }
        
        graph.sort(((o1, o2) -> o2.width - o1.width));
        System.out.println(kruskal());
    }


    private static int kruskal() {
        for (Edge edge : graph) {
            int start = edge.start;
            int end = edge.end;
            int width = edge.width;

            if (findParent(start) != findParent(end)) {
                union(start, end);
                if (findParent(c) == findParent(v)) {
                    return width;
                }
            }
        }

        return -1;
    }

    private static class Edge{
        int start, end, width;

        public Edge(int start, int end, int width) {
            this.start = start;
            this.end = end;
            this.width = width;
        }
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        parent[bp] = ap;
    }

    private static int findParent(int x) {
        if (x == parent[x]) return parent[x];
        else return parent[x] = findParent(parent[x]);
    }
}
