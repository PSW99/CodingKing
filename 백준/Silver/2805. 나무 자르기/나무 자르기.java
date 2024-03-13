import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, tree[i]);
        }

        int left = 0;
        int right = max;

        while (left + 1 < right) {
            int mid = (left + right) / 2;

            if (isTake(mid))left = mid;
            else right = mid;

        }

        System.out.println(left);
    }

    private static boolean isTake(int length) {
        long sum = 0;

        for (int i = 0; i < N; i++) {
            long treeSize = tree[i];
            if (treeSize > length) {
                sum += treeSize - length;
            }
        }

        return sum >= M;
    }
}
