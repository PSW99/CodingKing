import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static List<Edge> graph = new ArrayList<>();
    private static int[] parent;
    private static long answer = 0;
    private static int[] arr;
    private static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            arr[i] = 1;
        }

        long sum = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.add(new Edge(start, end, weight));
            sum += weight;
        }

        graph.sort((o1, o2) -> o2.weight - o1.weight);

        for (Edge edge : graph) {
            int start = edge.start;
            int end = edge.end;
            int weight = edge.weight;
            if (findParent(start) != findParent(end)) {
                int sizeS = getSize(start);
                int sizeE = getSize(end);

                answer = (answer + (long) sizeS * sizeE % MOD * sum % MOD) % MOD;
                union(start, end);
            }
            sum -= weight;
        }

        System.out.println(answer);
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) {
            parent[bp] = ap;
            arr[ap] += arr[bp];
        }
    }

    private static int findParent(int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    private static int getSize(int u) {
        return arr[findParent(u)];
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
