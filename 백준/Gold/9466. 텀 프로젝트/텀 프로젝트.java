import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int T;
    private static int[] arr;
    private static boolean[] visited, finished;
    private static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            cnt = 0;
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[n + 1];
            visited = new boolean[n + 1];finished = new boolean[n + 1];
            for (int j = 1; j < n + 1; j++) arr[j] = Integer.parseInt(st.nextToken());

            for (int j = 1; j < n + 1; j++) {
                if (!visited[j]) dfs(j);
            }
            System.out.println(n - cnt);
        }

    }

    private static void dfs(int idx) {
        if (finished[idx]) return;
        if (visited[idx]) {
            finished[idx] = true;
            cnt++;
        }

        visited[idx] = true;
        dfs(arr[idx]);
        finished[idx] = true;
        visited[idx] = false;
    }

}
