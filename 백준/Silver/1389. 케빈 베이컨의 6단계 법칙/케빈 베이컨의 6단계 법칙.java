import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] dist;
    private static int[] sum;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static Queue<Integer> queue = new ArrayDeque<>();
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sum = new int[N + 1];
        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        for (int i = 1; i < N + 1; i++) dijkstra(i);

        for (int i = 1; i < N + 1; i++) {
            if (sum[i] == min) {
                System.out.println(i);
                break;
            }
        }

    }

    private static void dijkstra(int start) {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        queue.add(start);
        dist[start] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            List<Integer> list = graph.get(cur);
            for (int next : list) {
                if (dist[cur] + 1 < dist[next]) {
                    dist[next] = dist[cur] + 1;
                    queue.add(next);
                }
            }
        }
        int a = 0;
        for (int i = 1; i < N + 1; i++) a += dist[i];

        min = Math.min(min, a);
        sum[start] = a;

    }


}
