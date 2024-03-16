import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] dist;
    private static List<List<Node>> graph = new ArrayList<>();
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(v).add(new Node(u, weight));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dijkstra(start);
        System.out.println(dist[end]);
    }

    private static void dijkstra(int start) {
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int curV = curNode.v;
            int curWeight = curNode.weight;

            if (curWeight>dist[curV]) continue;

            for (Node nextNode : graph.get(curV)) {
                if (curNode.weight + nextNode.weight < dist[nextNode.v]) {
                    dist[nextNode.v] = curNode.weight + nextNode.weight;
                    pq.offer(new Node(nextNode.v, dist[nextNode.v]));
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
