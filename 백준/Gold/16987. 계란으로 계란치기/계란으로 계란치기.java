import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static List<int[]> eggs = new ArrayList<>();
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs.add(new int[]{durability, weight});
        }

        backtracking(0, 0);
        System.out.println(max);

    }

    private static void backtracking(int start, int count) {
        if (count == N - 1 || start == N) {
            max = Math.max(max, count);
            return;
        }

        if (eggs.get(start)[0] <= 0) {
            backtracking(start + 1, count);
            return;
        }

        int curCount = count;
        for (int i = 0; i < N; i++) {
            if (i == start) continue;

            if (eggs.get(i)[0] > 0) {
                eggs.get(start)[0] -= eggs.get(i)[1];
                if (eggs.get(start)[0] <= 0) count++;
                eggs.get(i)[0] -= eggs.get(start)[1];
                if (eggs.get(i)[0] <= 0) count++;
                backtracking(start + 1, count);
                eggs.get(start)[0] += eggs.get(i)[1];
                eggs.get(i)[0] += eggs.get(start)[1];
                count = curCount;
            }
        }

    }
}
