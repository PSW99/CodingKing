import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static long[] arr;
    private static long a, b, c;
    private static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                if (binarySearch(i, j, j + 1, N - 1)) break;
            }
        }

        sb.append(a).append(" ").append(b).append(" ").append(c);
        System.out.println(sb);
    }

    private static boolean binarySearch(int i, int j ,int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            long sum = arr[i] + arr[j] + arr[mid];
            if (sum == 0) {
                a = arr[i];
                b = arr[j];
                c = arr[mid];
                return true;
            }

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                a = arr[i];
                b = arr[j];
                c = arr[mid];
            }

            if (sum < 0) left = mid + 1;
            else right = mid - 1;

        }

        return false;
    }
}
