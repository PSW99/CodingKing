import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int T;
    private static List<List<Node>> graph;
    private static int[] dist;
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            graph = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n + 1; j++) graph.add(new ArrayList<>());
            dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.get(v).add(new Node(u, weight));
            }
            dijkstra(c);

            int count = 0;
            int max = Integer.MIN_VALUE;
            for (int j = 1; j < n + 1; j++) {
                if (dist[j] != Integer.MAX_VALUE){
                    count++;
                    max = Math.max(max, dist[j]);
                }
            }
            System.out.println(count + " " + max);
        }
    }

    private static void dijkstra(int start) {
        dist[start] = 0;
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.u] < cur.weight) continue;

            List<Node> nodes = graph.get(cur.u);
            for (Node node : nodes) {
                if (cur.weight + node.weight < dist[node.u]) {
                    dist[node.u] = cur.weight + node.weight;
                    pq.add(new Node(node.u, dist[node.u]));
                }
            }
        }
    }

    private static class Node {
        int u, weight;

        public Node(int u, int weight) {
            this.u = u;
            this.weight = weight;
        }
    }
}
