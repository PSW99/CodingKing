
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] visited = new int[N + 1];
        visited[N] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
         while (queue.isEmpty() == false) {
            int n = queue.remove();
             if (n == 1) {
                 N = visited[n] - 1;
             }
             if (n - 1 >= 0 && visited[n - 1] == 0) {
                 visited[n - 1] = visited[n] + 1;
                 queue.add(n - 1);
             }
             if (n % 3 == 0 &&  visited[n / 3] == 0) {
                 visited[n / 3] = visited[n] + 1;
                 queue.add(n / 3);
             }
             if (n % 2 == 0 &&  visited[n / 2] == 0) {
                 visited[n / 2] = visited[n] + 1;
                 queue.add(n / 2);
             }
        }
        System.out.println(N);
    }
}
