import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, E;
    private static List<List<Node>> graph = new ArrayList<>();
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    private static int[] dist;
    private static int v1, v2;
    private static int INF = 200000000;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, weight));
            graph.get(v).add(new Node(u, weight));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken()); //반드시 지나야하는 정점 v1
        v2 = Integer.parseInt(st.nextToken()); //반드시 지나야하는 정점 v2
        
        int min = Math.min(solve1(), solve2());
        if (min >= INF) System.out.println(-1);
        else System.out.println(min);
    }

    private static int solve1() {
        answer = 0;
        dijkstra(1);
        answer += dist[v2];
        dijkstra(v2);
        answer += dist[v1];
        dijkstra(v1);
        answer += dist[N];
        return answer;
    }

    private static int solve2() {
        answer = 0;
        dijkstra(1);
        answer += dist[v1];
        dijkstra(v1);
        answer += dist[v2];
        dijkstra(v2);
        answer += dist[N];
        return answer;
    }

    private static void dijkstra(int start) {
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
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
                    pq.add(new Node(nextNode.v, dist[nextNode.v]));
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
