import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] dist;
    private static int[] sum;
    private static Queue<Integer> queue = new ArrayDeque<>();
    private static List<List<Integer>> graph = new ArrayList<>();
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sum = new int[N + 1];
        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (start == -1 && end == -1) break;
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        for (int i = 1; i < N + 1; i++) dijkstra(i);

        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            if (sum[i] == min) {
                sb.append(i).append(" ");
                count++;
            }
        }
        System.out.println(min + " " + count);
        System.out.println(sb);
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
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            max = Math.max(max, dist[i]);
        }
        sum[start] = max;
        min = Math.min(min, max);

    }
}
