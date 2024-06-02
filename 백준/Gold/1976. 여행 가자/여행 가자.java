import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] arr;
    private static List<Edge> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 0; i < N + 1; i++) arr[i] = i;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) continue;
                else {
                    list.add(new Edge(i + 1, j + 1));
                    list.add(new Edge(j + 1, i + 1));
                }
            }
        }

        for (Edge edge : list) {
            int start = edge.start;
            int end = edge.end;
            union(start, end);
        }

        boolean flag = true;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] plan = new int[M];
        for (int i = 0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M - 1; i++) {
            if (findParent(plan[i]) != findParent(plan[i + 1])) {
                flag = false;
                break;
            }
        }

        if (flag) System.out.println("YES");
        else System.out.println("NO");

    }

    private static int findParent(int x) {
        if (arr[x] == x) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) arr[bp] = ap;
    }

    private static class Edge {
        int start, end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
