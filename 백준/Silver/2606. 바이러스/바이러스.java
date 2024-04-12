import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<List<Integer>> list = new ArrayList<>();
    private static Queue<Integer> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) list.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            list.get(v).add(u);
            list.get(u).add(v);
        }
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            List<Integer> node = list.get(u);
            for (int i : node) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < N + 1; i++)
            if (visited[i]) answer++;

        System.out.println(answer - 1);
    }
}
