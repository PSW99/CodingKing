import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int M = Integer.parseInt(br.readLine());
        Stack<String> left = new Stack<>();
        Stack<String> right = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < S.length(); i++) {
            left.push(String.valueOf(S.charAt(i)));
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("P")) {
                String s = st.nextToken();
                left.push(s);
            }
            if (command.equals("L") && !left.isEmpty()) {
                String pop = left.pop();
                right.push(pop);
            }
            if (command.equals("D") && !right.isEmpty()) {
                String pop = right.pop();
                left.push(pop);
            }
            if (command.equals("B") && !left.isEmpty()) {
                left.pop();
            }

        }

        while (!left.isEmpty()) {
            sb.append(left.pop());
        }
        sb.reverse();
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }

        System.out.println(sb);
    }
}
