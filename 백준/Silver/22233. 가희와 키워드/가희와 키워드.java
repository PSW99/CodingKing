import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            map.put(br.readLine(), i);
        }
        for (int i = 0; i < M; i++) {
            String[] split = br.readLine().split(",");
            for (int j = 0; j < split.length; j++) {
                map.remove(split[j]);
            }
            System.out.println(map.size());
        }
    }
}
