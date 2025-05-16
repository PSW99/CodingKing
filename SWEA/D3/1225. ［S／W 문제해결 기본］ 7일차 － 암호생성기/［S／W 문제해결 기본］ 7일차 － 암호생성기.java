import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    private static Deque<Integer> dq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int T = Integer.parseInt(br.readLine());

            dq = new ArrayDeque<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                dq.addLast(Integer.valueOf(st.nextToken()));
            }

            boolean flag = false;
            do {

                for (int i = 1; i <= 5; i++) {
                    int num = dq.removeFirst() - i;
                    if (num <= 0) {
                        num = 0;
                        flag = true;
                    }
                    dq.addLast(num);
                    if (flag) break;
                }

            } while (!flag);


            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ");
            while (!dq.isEmpty()) {
                sb.append(dq.poll()).append(" ");
            }

            System.out.println(sb);
        }
    }
}
