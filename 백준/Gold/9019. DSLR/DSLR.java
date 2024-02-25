import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static String[] strings;
    private static boolean[] booleans;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            strings = new String[10001];
            booleans = new boolean[10001];
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            bfs(A, B);
            System.out.println(strings[B]);
        }
    }

    private static void bfs(int A, int B) {
        queue = new LinkedList<>();
        queue.add(A);
        booleans[A] = true;
        strings[A] = "";
        while (true) {
            int cur = queue.poll();
            int D = (cur * 2) % 10000;
            calculate(D, cur, "D");

            int S = cur - 1;
            if (S == -1) S = 9999;
            calculate(S, cur, "S");

            int L = getL(cur);
            calculate(L, cur, "L");

            int R = getR(cur);
            calculate(R, cur, "R");

            if (booleans[B]) break;
        }
    }

    private static int getR(int n) {
        int d1 = n / 1000;  // 천의 자리
        int d2 = (n % 1000) / 100;  // 백의 자리
        int d3 = (n % 100) / 10;  // 십의 자리
        int d4 = n % 10;  // 일의 자리

        // 회전 후 결과 계산
        int result = d4 * 1000 + d1 * 100 + d2 * 10 + d3;
        return result;
    }

    private static int getL(int n) {
        int d1 = n / 1000;  // 천의 자리
        int d2 = (n % 1000) / 100;  // 백의 자리
        int d3 = (n % 100) / 10;  // 십의 자리
        int d4 = n % 10;  // 일의 자리

        // 회전 후 결과 계산
        int result = d2 * 1000 + d3 * 100 + d4 * 10 + d1;
        return result;
    }

    private static void calculate(int n, int cur, String s) {
        if (!booleans[n]) {
            booleans[n] = true;
            String a = strings[cur];
            strings[n] = a + s;
            queue.add(n);
        }
    }
}
