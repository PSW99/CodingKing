import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static Stack<Node> stack = new Stack<>();
    private static List<NGE> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty()) {
                if (stack.peek().num < num) {
                    Node pop = stack.pop();
                    list.add(new NGE(pop.index, num));
                } else break;
            }
            stack.push(new Node(num, i));
        }

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            list.add(new NGE(pop.index, -1));
        }

        StringBuilder sb = new StringBuilder();
        list.sort(Comparator.comparingInt(o -> o.num));
        for (NGE nge : list) sb.append(nge.answer).append(" ");
        System.out.println(sb);
    }

    private static class Node {
        int num, index;

        public Node(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    private static class NGE {
        int num, answer;

        public NGE(int num, int answer) {
            this.num = num;
            this.answer = answer;
        }
    }
}
