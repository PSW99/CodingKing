import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, P;
    private static int[] cost;
    private static int[] arr;
    private static List<Edge> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        cost = new int[N + 1];
        arr = new int[N + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            cost[i] = Integer.parseInt(br.readLine());
            min = Math.min(cost[i], min);
            arr[i] = i;
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            int sum = 0;
            sum = (weight * 2 + cost[start] + cost[end]);
            graph.add(new Edge(start, end, sum));
            graph.add(new Edge(end, start, sum));
        }

        int answer = 0;
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

        System.out.println(answer + min);

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

    private static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
