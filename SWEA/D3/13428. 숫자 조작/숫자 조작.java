import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    private static int[] arr;
    private static int max, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String input = br.readLine();
            max = Integer.parseInt(input);
            min = Integer.parseInt(input);
            arr = new int[input.length()];

            for (int i = 0; i < input.length(); i++) {
                arr[i] = input.charAt(i) - '0';
            }

            backTracking(0);

            System.out.println("#" + test_case + " " + min + " " + max);
        }

    }

    private static void backTracking(int cnt) {
        if (cnt == 1) {
            int num = calculateNum(arr);
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (i == 0 && arr[j] == 0) {
                    continue;
                }
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;

                backTracking(cnt + 1);

                arr[j] = arr[i];
                arr[i] = tmp;
            }
        }
    }

    private static int calculateNum(int[] arr) {
        int value = 0;
        for (int digit : arr) {
            value = value * 10 + digit;
        }
        return value;
    }
}
