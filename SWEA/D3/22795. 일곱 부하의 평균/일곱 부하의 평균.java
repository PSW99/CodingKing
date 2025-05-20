import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int sum = 0;
            int max = Integer.MIN_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                int height = Integer.parseInt(st.nextToken());
                max = Math.max(max, height);
                sum += height;
            }

            max++;
            while (true) {
                if ((sum + max) % 7 == 0) {
                    break;
                }
                max++;
            }

            System.out.println(max);

        }
    }
}
