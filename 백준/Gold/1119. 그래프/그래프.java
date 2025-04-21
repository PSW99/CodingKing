import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static int N;
    private static int[] arr;
    private static List<Edge> list = new ArrayList<>();
    private static Set<Integer> parents = new HashSet<>();
    private static boolean canConnect = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 0; i < N + 1; i++) arr[i] = i;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            boolean b = false;
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == 'Y') {
                    union(i + 1, j + 1);
                    list.add(new Edge(i + 1, j + 1));
                    b = true;
                }
            }
            if (!b) {
                canConnect = false;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            parents.add(findParent(i));
        }

        if (N == 1) {
            System.out.println(0);
        } else if (list.size() / 2 < N - 1 || !canConnect) {
            System.out.println(-1);
        } else {
            System.out.println(parents.size() - 1);
        }

    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) arr[bp] = ap;
    }

    private static int findParent(int x) {
        if (arr[x] == x) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }

    private static class Edge {
        int start, end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
