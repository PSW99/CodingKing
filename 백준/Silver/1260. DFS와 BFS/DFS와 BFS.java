import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N,M, V;
    private static boolean[] visited;
    private static StringBuilder sb;
    private static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            graph.get(v).add(u);
            graph.get(u).add(v);
        }

        visited = new boolean[N + 1];
        visited[V] = true;

        dfs(V);
        System.out.println(sb);
        bfs(V);
    }

    private static void bfs(int start) {
        boolean[] booleans = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        booleans[start] = true;
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int curV = queue.poll();
            sb.append(curV).append(" ");

            List<Integer> list = graph.get(curV);
            list.sort(Comparator.naturalOrder());
            for (int i : list) {
                if (!booleans[i]) {
                    queue.add(i);
                    booleans[i] = true;
                }
            }
        }

        System.out.println(sb);
    }

    private static void dfs(int v) {
        sb.append(v).append(" ");

        List<Integer> list = graph.get(v);
        list.sort(Comparator.naturalOrder());

        for (int u : list) {
            if (!visited[u]) {
                visited[u] = true;
                dfs(u);
            }
        }

    }

}
