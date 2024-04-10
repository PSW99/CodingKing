import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static Deque<Integer> deque = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("push")) {
                int n = Integer.parseInt(st.nextToken());
                deque.add(n);
            }
            if (command.equals("pop")) {
                if (deque.isEmpty()) sb.append(-1).append("\n");
                else sb.append(deque.removeFirst()).append("\n");
            }
            if (command.equals("size")) {
                sb.append(deque.size()).append("\n");
            }
            if (command.equals("empty")) {
                if (deque.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
            if (command.equals("front")) {
                if (deque.isEmpty()) sb.append(-1).append("\n");
                else sb.append(deque.peekFirst()).append("\n");
            }
            if (command.equals("back")) {
                if (deque.isEmpty()) sb.append(-1).append("\n");
                else sb.append(deque.peekLast()).append("\n");
            }
        }

        System.out.println(sb);
    }
}
