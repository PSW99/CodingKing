import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, r, c;;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        System.out.println(divide(N, r, c));
    }

    public static int divide(int n, int r, int c) {
        if (n == 0) return 0;

        int mid = (int) Math.pow(2, n - 1); // 2^(n-1)
        int size = mid * mid;

        if (r < mid && c < mid) return divide(n - 1, r, c);
        else if (r < mid) return size + divide(n - 1, r, c - mid);
        else if (c < mid) return 2 * size + divide(n - 1, r - mid, c);
        else return 3 * size + divide(n - 1, r - mid, c - mid);

    }
}
