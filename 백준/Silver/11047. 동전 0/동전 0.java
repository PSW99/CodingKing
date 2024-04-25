import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) list.add(Integer.valueOf(br.readLine()));
        int answer = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (list.get(i) <= K) {
                answer += (K / list.get(i));
                K %= list.get(i);
            }
        }
        System.out.println(answer);

    }
}
