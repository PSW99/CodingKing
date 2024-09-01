import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];

        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            dfs(i, 1);
            if (answer == 1) break;
        }

        System.out.println(answer);

    }

    private static void dfs(int start, int depth) {
        if (depth == 5) {
            answer = 1;
            return;
        }

        visited[start] = true;
        for (int end : graph.get(start)) {
            if (!visited[end]) {
                dfs(end, depth + 1);

            }
        }
        visited[start] = false;

    }
}

