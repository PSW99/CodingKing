import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = Integer.MIN_VALUE;
        int[] arr = new int[9];
        for (int i = 0; i < 9; i++) {
            int n = Integer.parseInt(br.readLine());
            max = Math.max(max, n);
            arr[i] = n;
        }

        System.out.println(max);
        for (int i = 0; i < 9; i++) {
            if (arr[i] == max) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}
