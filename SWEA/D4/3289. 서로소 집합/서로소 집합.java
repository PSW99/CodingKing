import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N, M;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if (a == 0) {
                    union(b, c);
                } else {
                    if (findParent(b) == findParent(c)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }

            System.out.println("#" + test_case + " " + sb);
        }

    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        parent[bp] = ap;
    }

    private static int findParent(int x) {
        if (x == parent[x]) return parent[x];
        else return parent[x] = findParent(parent[x]);
    }
}
