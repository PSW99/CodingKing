import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dist;
    private static Queue<Node> queue = new LinkedList<>();
    private static int time = 0;

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        bfs(N, K);
        System.out.println(time);
        System.out.println(count);
    }

    private static void bfs(int start, int end) {
        queue.add(new Node(start, 0));
        dist = new int[100001];
        dist[start] = 1;
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int curX = curNode.x;

            if (curX == end) {
                if (count == 0) {
                    time = curNode.time;
                }
                if (time == curNode.time) {
                    count++;
                }
                continue;
            }

            if (curX - 1 >= 0) {
                if (dist[curX - 1] == 0 || dist[curX - 1] == dist[curX] + 1) {
                    queue.add(new Node(curX - 1, curNode.time + 1));
                    dist[curX - 1] = dist[curX] + 1;
                }
            }

            if (curX + 1 < 100001) {
                if (dist[curX + 1] == 0 || dist[curX + 1] == dist[curX] + 1) {
                    queue.add(new Node(curX + 1, curNode.time + 1));
                    dist[curX + 1] = dist[curX] + 1;
                }
            }

            if (curX * 2 < 100001) {
                if (dist[curX * 2] == 0 || dist[curX * 2] == dist[curX] + 1) {
                    queue.add(new Node(curX * 2, curNode.time + 1));
                    dist[curX * 2] = dist[curX] + 1;
                }
            }
        }
    }

    static class Node {
        int x, time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}



