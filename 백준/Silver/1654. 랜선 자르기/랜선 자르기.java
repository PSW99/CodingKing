import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int K = 0; //이미 가지고 있는 랜선의 개수
    static int N = 0; //필요한 랜선의 개수
    static int[] lan; //랜선 종류
    static long current = 0; //현재가지고 있는 랜선 개수
    static long max = 0; //최대 랜선 길이

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        K = Integer.parseInt(input[0]);
        lan = new int[K];
        N = Integer.parseInt(input[1]);

        for (int i = 0; i < K; i++) {
            int data = Integer.parseInt(br.readLine());
            lan[i] = data;
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solution(); //입력 받기

        long left = 1;
        long right = Integer.MAX_VALUE;
        long mid;

        while (left <= right) {
            current = 0;
            mid = (left + right) / 2;
            for (int i = 0; i < K; i++) {
                int length = lan[i];
                current += length / mid;
            }
            if (current < N) right = mid - 1;
            else left = mid + 1;
        }

        System.out.println(right);
    }
}
