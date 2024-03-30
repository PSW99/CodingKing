import javax.naming.spi.DirObjectFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] arr;
    private static char[] university;
    private static int answer = 0;
    private static int count = 0;
    private static List<Edge> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        university = new char[N + 1];
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N + 1; i++) arr[i] = i;
        for (int i = 1; i < N + 1; i++) university[i] = st.nextToken().charAt(0);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (university[start] == university[end]) continue;

            graph.add(new Edge(start, end, weight));
        }

        graph.sort(Comparator.comparingInt(o -> o.weight));
        kruskal();
        if (count != N - 1) System.out.println(-1);
        else System.out.println(answer);

    }
    private static void kruskal() {
        for (int i = 0; i < graph.size(); i++) {
            Edge edge = graph.get(i);
            if (findParent(edge.start) != findParent(edge.end)) {
                union(edge.start, edge.end);
                answer += edge.weight;
                count++;
            }
        }
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) arr[bp] = ap;
    }

    private static int findParent(int x) {
        if (x == arr[x]) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }

    private static class Edge {
        int start,end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weigh=" + weight +
                    '}';
        }
    }
}
