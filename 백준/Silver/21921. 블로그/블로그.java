import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {

            if (i < X) {
                sum += arr[i];
            }
            if (i >= X) {
                list.add(sum);
                sum -= arr[i - X];
                sum += arr[i];
            }

            if (i == N - 1) {
                list.add(sum);
            }

        }

        list.sort(Collections.reverseOrder());
        if (list.get(0) == 0) {
            System.out.println("SAD");
        } else {
            int max = list.get(0);
            int answer = 0;
            for (int n : list) {
                if (n == max) {
                    answer++;
                } else {
                    break;
                }
            }

            System.out.println(max);
            System.out.println(answer);
        }

    }
}
