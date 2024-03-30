import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] arr;
    private static List<Edge> roads = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int road = Integer.parseInt(st.nextToken());
            roads.add(new Edge(u, v, road));
        }

        roads.sort((Comparator.comparingInt(o -> o.road)));
        int max = kruskal(0);
        roads.sort((o1, o2) -> o2.road - o1.road);
        int min = kruskal(0);
        System.out.println((max * max) - (min * min));
    }

    private static int kruskal(int zero) {
        arr = new int[N + 1];
        for (int i = 0; i < N + 1; i++) arr[i] = i;

        for (int i = 0; i < roads.size(); i++) {
            Edge edge = roads.get(i);
            if (findParent(edge.u) != findParent(edge.v)) {
                union(edge.u, edge.v);
                if (edge.road == 0) zero++;
            }
        }

        return zero;
    }


    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        arr[bp] = ap;
    }

    private static int findParent(int x) {
        if (x == arr[x]) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }

    private static class Edge {
        int u,v, road;


        public Edge(int u, int v, int road) {
            this.u = u;
            this.v = v;
            this.road = road;
        }

        @Override
        public String toString() {
            return "Road{" +
                    "u=" + u +
                    ", v=" + v +
                    ", road=" + road +
                    '}';
        }
    }
}
