import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<List<Node>> graph = new ArrayList<>();
    private static int N;
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    private static boolean[] visited;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(v).add(new Node(u, w));
            graph.get(u).add(new Node(v, w));
        }

        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N + 1];
            dfs(i,0);
        }
        if (N == 1) {
            System.out.println(0);
        }else System.out.println(max);

    }

    private static void dfs(int start, int weight) {
        visited[start] = true;
        List<Node> nodes = graph.get(start);
        for (Node node : nodes) {
            if (!visited[node.v]) {
                visited[node.v] = true;
                dfs(node.v, weight + node.weight);
            }
            max = Math.max(max, weight);
        }


    }

    static class Node {
        int v , weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

}