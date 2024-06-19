import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dist;
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    private static List<List<Node>> graph;
    private static int g, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int test = 0; test < T; test++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            dist = new int[n + 1];
            Arrays.fill(dist, (int) 1e9);

            for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                if ((u == g && v == h) || (u == h && v == g)){
                    graph.get(u).add(new Node(v, weight * 2 - 1));
                    graph.get(v).add(new Node(u, weight * 2 - 1));
                    continue;
                }
                graph.get(u).add(new Node(v, weight * 2));
                graph.get(v).add(new Node(u, weight * 2));
            }
            dijkstra(s);
            int[] candidates = new int[t];
            for (int i = 0; i < t; i++) candidates[i] = Integer.parseInt(br.readLine());
            Arrays.sort(candidates);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < t; i++) {
                if (dist[candidates[i]] % 2 == 1) sb.append(candidates[i]).append(" ");
            }
            System.out.println(sb);
        }
    }

    private static void dijkstra(int start) {
        dist[start] = 0;
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curV = cur.v;
            int weight = cur.weight;

            if (dist[curV] < weight) continue;

            for (Node next : graph.get(curV)) {
                if (dist[curV] + next.weight < dist[next.v]) {
                    dist[next.v] = dist[curV] + next.weight;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }

    }

    private static class Node {
        int v , weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}
