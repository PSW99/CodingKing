import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for (int i = 0; i < n + 1; i++) arr[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (operator == 0) {
                union(a, b);
            } else {
                if (findParent(a) == findParent(b)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);

    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        arr[bp] = ap;
    }

    private static int findParent(int x) {
        if (x == arr[x]) {
            return arr[x];
        } else return arr[x] = findParent(arr[x]);
    }
}
