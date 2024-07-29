import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static Queue<Integer> queue = new ArrayDeque<>();
    private static int[] inDegree;
    private static List<Integer> top = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
        }

        ts();
        StringBuilder sb = new StringBuilder();
        for (int i : top) sb.append(i).append(" ");
        System.out.println(sb);

    }

    private static void ts() {
        inDegree = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int v : graph.get(i)) {
                inDegree[v]++;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            top.add(node);
            for (int v : graph.get(node)) {
                inDegree[v]--;
                if (inDegree[v] == 0) queue.add(v);
            }

        }

    }
}
