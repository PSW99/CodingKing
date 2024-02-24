import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N , M;
    private static int[] dijkstra;
    private static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.length));
    private static List<List<Node>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dijkstra = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b,c));
            list.get(b).add(new Node(a,c));
        }
        Arrays.fill(dijkstra, Integer.MAX_VALUE);
        bfs();
        System.out.println(dijkstra[N]);
    }

    private static void bfs() {
        pq.add(new Node(1, 0));
        dijkstra[1] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            for (Node nextNode : list.get(curNode.barn)) {
                if (dijkstra[nextNode.barn] > dijkstra[curNode.barn] + nextNode.length) {
                    dijkstra[nextNode.barn] = dijkstra[curNode.barn] + nextNode.length;
                    pq.add(new Node(nextNode.barn, dijkstra[nextNode.barn]));
                }
            }
        }


    }

    static class Node {
        int barn , length;

        public Node(int barn, int length) {
            this.barn = barn;
            this.length = length;
        }
    }
}
