import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double answer = 0;
        int n = Integer.parseInt(br.readLine());
        int max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            max = Math.max(max, num);
            arr[i] = num;
        }

        for (int i = 0; i < n; i++) {
            int num = arr[i];
            answer += (double) num / max * 100;
        }


        System.out.println(answer / n);

    }
}