import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static Queue<Node> queue = new LinkedList<>();
    private static boolean[] booleans = new boolean[101];
    private static HashMap<Integer, Integer> ladder;
    private static HashMap<Integer, Integer> snake;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ladder = new HashMap<>();
        snake = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladder.put(x, y);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            snake.put(x, y);
        }
        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        queue.add(new Node(1, 0));
        booleans[1] = true;

        while (true) {
            Node curNode = queue.poll();
            int curX = curNode.x;
            int curCnt = curNode.cnt;
            if (curX == 100) {
                answer = curCnt;
                break;
            }

            for (int i = 1; i < 7; i++) {
                int nx = curX + i;
                if (nx > 100 || booleans[nx]) continue;
                booleans[nx] = true;
                if (ladder.containsKey(nx)) {
                    queue.add(new Node(ladder.get(nx), curCnt + 1));
                    booleans[ladder.get(nx)] = true;
                    continue;
                }
                if (snake.containsKey(nx)) {
                    queue.add(new Node(snake.get(nx), curCnt + 1));
                    booleans[snake.get(nx)] = true;
                    continue;
                }
                queue.add(new Node(nx, curCnt + 1));
            }
        }
    }

    static class Node {
        int x , cnt;

        public Node(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }
}
