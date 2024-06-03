import javax.naming.PartialResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int T;
    private static Deque<Integer> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            deque = new ArrayDeque<>();
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());

            String s = br.readLine();

            for (String string : s.substring(1, s.length() - 1).split(","))
                if (!string.isEmpty())
                    deque.add(Integer.valueOf(string));

            boolean flag = false;
            boolean tFlag = flag;
            for (int j = 0; j < p.length(); j++) {
                char c = p.charAt(j);
                if (c == 'R') {
                    flag = !flag;
                    continue;
                }
                if (c == 'D') {
                    if (deque.isEmpty()) {
                        tFlag = true;
                        break;
                    }
                    if (!flag) deque.removeFirst();
                    else deque.removeLast();
                }
            }

            if (tFlag) {
                System.out.println("error");
                continue;
            }
            int[] arr = new int[deque.size()];
            int index = 0;
            if (flag) while (!deque.isEmpty()) arr[index++] = deque.removeLast();
            else while (!deque.isEmpty()) arr[index++] = deque.removeFirst();
            System.out.println(Arrays.toString(arr).replaceAll(" ", ""));

        }
    }
}
