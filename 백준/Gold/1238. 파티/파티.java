import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, X;
    private static List<List<Node>> graph = new ArrayList<>();
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    private static int[] dist;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, weight));
        }

        for (int i = 1; i < N + 1; i++) {
            if (i == X) continue;
            int distance = 0;
            dijkstra(i);
            distance += dist[X];
            dijkstra(X);
            distance += dist[i];
            max = Math.max(max, distance);
        }

        System.out.println(max);
    }

    private static void dijkstra(int start) {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.v;
            int weight = curNode.weight;

            if (weight > dist[cur]) continue;

            List<Node> nodes = graph.get(cur);
            for (Node next : nodes) {
                if (dist[cur] + next.weight < dist[next.v]) {
                    dist[next.v] = dist[cur] + next.weight;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }

        }
    }

    private static class Node {
        int v, weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}
