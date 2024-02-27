import java.io.*;
import java.util.*;

public class Main {
    private static int n, m;
    private static List<List<Node>> graph = new ArrayList<>();
    private static int[] dist;
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine()); //정점의 개수(도시 수)
        m = Integer.parseInt(br.readLine()); //간선의 개수(간선의 개수)

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(source).add(new Node(destination, weight));
        }

        for (int i = 1; i < n + 1; i++) {
            floyd(i);
            for (int j = 1; j < n + 1; j++) {
                if (dist[j] == Integer.MAX_VALUE) {
                    sb.append(0).append(" ");
                    continue;
                }
                sb.append(dist[j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void floyd(int source) {
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            List<Node> nodes = graph.get(curNode.v);

            for (Node nextNode : nodes) {
                if (dist[curNode.v] + nextNode.weight < dist[nextNode.v]) {
                    dist[nextNode.v] = dist[curNode.v] + nextNode.weight;
                    pq.add(new Node(nextNode.v, dist[nextNode.v]));
                }
            }
        }
    }

    static class Node {
        int v, weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}
