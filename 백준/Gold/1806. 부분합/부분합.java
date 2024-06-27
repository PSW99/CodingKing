import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, S;
    private static int[] arr;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        findMinDistance();
        if (min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }

    private static void findMinDistance() {
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < N) {
            sum += arr[right];

            while (sum >= S) {
                min = Math.min(min, right - left + 1);
                sum -= arr[left];
                left++;
            }
            right++;
        }
    }
}