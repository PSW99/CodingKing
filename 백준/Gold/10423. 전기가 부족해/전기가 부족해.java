import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static List<Edge> graph = new ArrayList<>(); //케이블
    private static int[] arr;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for (int i = 0; i < N + 1; i++) arr[i] = i;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int index = Integer.parseInt(st.nextToken());
            arr[index] = -1;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.add(new Edge(start, end, weight));
        }

        graph.sort(Comparator.comparingInt( o->o.weight));
        kruskal();
        System.out.println(answer);
    }

    private static void kruskal() {
        for (Edge edge : graph) {
            if (findParent(edge.start) != findParent(edge.end)) {
                union(edge.start, edge.end);
                answer += edge.weight;
                if (isConnect()) {
                    System.out.println(answer);
                    break;
                }
            }
        }
    }

    private static boolean isConnect() {
        for (int j : arr) if (j != -1) return false;
        return true;
    }
    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) {
            if (ap == -1) arr[bp] = ap;
            else if (bp == -1) arr[ap] = bp;
            else arr[bp] = ap;
        }
    }

    private static int findParent(int x) {
        if (arr[x] == -1) return arr[x];
        if (x == arr[x]) return x;
        return arr[x] = findParent(arr[x]);
    }

    private static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

    }
}
