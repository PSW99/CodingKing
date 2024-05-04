import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] arr;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) arr[i] = i;

        backtracking(1, 0);


    }

    private static void backtracking(int start,int count) {
        if (count == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < N + 1; i++) {
                if (visited[i]) {
                    sb.append(arr[i]).append(" ");
                }
            }
            System.out.println(sb);
            return;
        }

        for (int i = start; i < N + 1; i++) {
            visited[i] = true;
            backtracking(i + 1, count + 1);
            visited[i] = false;
        }
    }
}
