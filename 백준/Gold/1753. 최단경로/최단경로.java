import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int V, E, K;
    private static List<List<Node>> graph = new ArrayList<>();
    private static int[] dijkstra;
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        dijkstra = new int[V + 1];

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        Arrays.fill(dijkstra,Integer.MAX_VALUE);
        bfs(K);

    }

    private static void bfs(int k) {
        pq.add(new Node(k, 0));
        dijkstra[k] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            for (Node nextNode : graph.get(curNode.v)) {
                if (dijkstra[nextNode.v] > dijkstra[curNode.v] + nextNode.cost) {
                    dijkstra[nextNode.v] = dijkstra[curNode.v] + nextNode.cost;
                    pq.offer(new Node(nextNode.v, dijkstra[nextNode.v]));
                }
            }
        }

        for (int i = 1; i < dijkstra.length; i++) {
            if (dijkstra[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }
            System.out.println(dijkstra[i]);
        }

    }
    static class Node {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
