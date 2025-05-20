import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            int floor = 0;
            for (int i = 1; i <= N; i++) {
                floor += i;
                if (floor == P) {
                    floor--;
                }
            }

            System.out.println(floor);
        }

    }
}
