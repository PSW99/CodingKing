import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] arr;
    private static boolean[] visited;
    private static Queue<Integer> queue = new LinkedList<>();
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        queue.add(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int node : graph.get(v))
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                    arr[node] = v;
                }
        }


        for (int i = 2; i < N + 1; i++) {
            System.out.println(arr[i]);
        }


    }
}
