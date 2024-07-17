import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] arr;
    private static List<Edge> graph = new ArrayList<>();
    private static HashMap<Integer, List<Integer>> map = new HashMap<>();
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            arr[i] = i;
            map.put(i, new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N - i; j++) {
                int weight = Integer.parseInt(st.nextToken());
                graph.add(new Edge(i, j + i + 1, weight));
            }
        }
        
        graph.sort(Comparator.comparingInt(o -> o.weight));
        kruskal();
        StringBuilder sb = new StringBuilder();
        map.forEach((key, value) -> {
            value.sort(Comparator.naturalOrder());
            sb.append(value.size()).append(" ");
            for (int i = 0; i < value.size(); i++) sb.append(value.get(i)).append(" ");
            sb.append("\n");
        });

        System.out.println(sb);
    }

    private static void kruskal() {
        for (Edge edge : graph) {
            if (findParent(edge.start) != findParent(edge.end)) {
                union(edge.start, edge.end);
                map.get(edge.start).add(edge.end);
                map.get(edge.end).add(edge.start);
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

    private static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

    }
}
