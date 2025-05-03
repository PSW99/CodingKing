import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int N, M, K;
    private static int[] arr;
    private static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            answer = false;
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            
            int index = 0;
            int count = 0;
            int time = 0;

            while (true) {
                if (index >= N) {
                    answer = true;
                    break;
                }
                
                int start = index;

                for (int i = start; i < N; i++) {
                    if (arr[i] == time) {
                        count--;
                        index++;
                    } else {
                        break;
                    }
                }

                if (count < 0) {
                    break;
                }

                time++;
                if (time % M == 0) {
                    count += K;
                }

            }

            if (answer) {
                System.out.println("#" + test_case + " Possible");
            } else {
                System.out.println("#" + test_case + " Impossible");
            }
        }
    }


}
