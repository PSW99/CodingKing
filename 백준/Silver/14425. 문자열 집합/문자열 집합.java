import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static HashMap<String, Integer> map = new HashMap<>();
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) map.put(br.readLine(), 0);

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (map.containsKey(s)) answer++;
        }
        System.out.println(answer);

    }
}
