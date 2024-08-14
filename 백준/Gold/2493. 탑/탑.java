import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Top> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek().height < height) stack.pop();

            if (stack.isEmpty()) sb.append(0).append(" ");
            else sb.append(stack.peek().num).append(" ");

            stack.push(new Top(i + 1, height));
        }

        System.out.println(sb.toString().trim());
    }

    private static class Top {
        int num, height;

        public Top(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }
}