import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static PriorityQueue<Joi> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.expense));
    private static HashMap<Integer, int[]> map = new HashMap<>();
    private static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int expense = Integer.parseInt(st.nextToken());
            int move = Integer.parseInt(st.nextToken());
            int[] value = {expense, move};
            map.put(i + 1, value);
        }

        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        System.out.println(dijkstra());

    }

    private static int dijkstra() {
        pq.add(new Joi(1, 0, 0));
        dist[1][0] = 0;
        while (!pq.isEmpty()) {
            Joi cur = pq.poll();
            int start = cur.start;
            int expense = cur.expense;
            int move = cur.move;

            if (start == N) return expense;

            int end = 0, nExpense = 0, nMove = 0;
            if (move != 0) {
                nMove = move - 1;
                nExpense = expense;
                for (int n : graph.get(start)) {
                    end = n;
                    if (dist[end][nMove] <= nExpense) continue;
                    dist[end][nMove] = nExpense;
                    pq.add(new Joi(end, nExpense, nMove));
                }
            }

            int[] value = map.get(start);
            nMove = value[1] - 1;
            nExpense = value[0] + expense;

            for (int n : graph.get(start)) {
                end = n;
                if (dist[end][nMove] <= nExpense) continue;
                dist[end][nMove] = nExpense;
                pq.add(new Joi(end, nExpense, nMove));
            }
        }

        return -1;
    }

    private static class Joi {
        int start, expense, move;

        public Joi(int start, int expense, int move) {
            this.start = start;
            this.expense = expense;
            this.move = move;
        }
    }

}
