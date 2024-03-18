import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K;
    private static boolean[] visited;
    private static Queue<Node> queue = new LinkedList<>();
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];

        bfs(N);
        System.out.println(min);
    }

    private static void bfs(int start) {
        queue.add(new Node(start, 0));
        visited[start] = true;
        while (!queue.isEmpty()) {

            Node curnode = queue.poll();
            if (curnode.x == K) {
                min = Math.min(min, curnode.time);
            }
            if (curnode.x * 2 < 100001 && !visited[curnode.x * 2]) {
                queue.add(new Node(curnode.x * 2, curnode.time));
                visited[curnode.x * 2] = true;
            }
            if (curnode.x - 1 >= 0 && !visited[curnode.x - 1]) {
                queue.add(new Node(curnode.x - 1, curnode.time + 1));
                visited[curnode.x - 1] = true;
            }
            if (curnode.x + 1 < 100001 && !visited[curnode.x + 1]) {
                queue.add(new Node(curnode.x + 1, curnode.time + 1));
                visited[curnode.x + 1] = true;
            }


        }
    }

    static class Node {
        int x , time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
