import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] booleans = new boolean[31];
        for (int i = 0; i < 28; i++) {
            int index = Integer.parseInt(br.readLine());
            booleans[index] = true;
        }

        for (int i = 1; i < 31; i++) {
            if (!booleans[i]) {
                max = Math.max(max, i);
                min = Math.min(min, i);
            }
        }
        System.out.println(min);
        System.out.println(max);


    }
}
