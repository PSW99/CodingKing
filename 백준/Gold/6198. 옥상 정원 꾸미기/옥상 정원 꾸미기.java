import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static int N;
    private static long answer = 0;
    private static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());

            while(!stack.isEmpty()) {
                if(stack.peek() <= height) stack.pop();
                else break;
            }
            
            answer += stack.size(); 
            stack.push(height);
        }

        System.out.println(answer);
    }


}
