import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static int min = Integer.MAX_VALUE;
    private static int a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N - 1; i++) {
            if (binarySearch(i, i + 1, N - 1)) break;
        }
        sb.append(a).append(" ").append(b);
        System.out.println(sb);
    }


    private static boolean binarySearch(int num, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = arr[num] + arr[mid];

            if (sum == 0){
                a = arr[num];
                b = arr[mid];
                return true;
            }

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                a = arr[num];
                b = arr[mid];
            }

            if (sum < 0) left = mid + 1;
            else right = mid - 1;
        }

        return false;
    }
}
