import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static List<Integer> cryptograms;
    private static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int test_case = 1; test_case <= 10; test_case++) {
            N = Integer.parseInt(br.readLine());
            cryptograms = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cryptograms.add(Integer.valueOf(st.nextToken()));
            }

            size = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < size; i++) {
                String command = st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    int[] arr = new int[y];
                    for (int j = 0; j < y; j++) {
                        arr[j] = Integer.parseInt(st.nextToken());
                    }

                    for (int j = y - 1; j >= 0; j--) {
                        cryptograms.add(x, arr[j]);
                    }
                } else {
                    for (int j = 0; j < y; j++) {
                        cryptograms.remove(x);
                    }
                }

            }

            StringBuilder sb = new StringBuilder();

            sb.append("#").append(test_case).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(cryptograms.get(i)).append(" ");
            }

            System.out.println(sb);
        }
    }
}
