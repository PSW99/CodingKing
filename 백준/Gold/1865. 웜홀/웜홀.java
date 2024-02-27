import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<Edge> graph;
    private static int[] dist;
    private static int N, M, W;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int i = 0; i < TC; i++) {
            graph = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //정점개수
            dist = new int[N + 1];
            M = Integer.parseInt(st.nextToken()); //무방향 간선 개수
            W = Integer.parseInt(st.nextToken()); ///방향 간선 개수
            Arrays.fill(dist, 100000001);
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                graph.add(new Edge(v,u,w));
                graph.add(new Edge(u,v,w));
            } //무방향 간선 -> 도로 간선
            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph.add(new Edge(v, u, -w));
            } //방향 간선(음수) -> 웜홀

            if (bellmanFord()) {
                System.out.println("YES");
            }else System.out.println("NO");

        }


    }

    private static boolean bellmanFord() {
        dist[1] = 0;

        for (int i = 1; i <= N; i++) {
            for (Edge edge : graph) {
                int source = edge.source;
                int destination = edge.destination;
                int weight = edge.weight;
                if (dist[source] + weight < dist[destination]) {
                    dist[destination] = dist[source] + weight;
                }
            }
        }
        for (Edge edge : graph) {
            int source = edge.source;
            int destination = edge.destination;
            int weight = edge.weight;
            if (dist[source] + weight < dist[destination]) {
                return true;
            }
        } // 음의 사이클 탐지
        return false;
    }

    static class Edge {
        int source,destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
}
