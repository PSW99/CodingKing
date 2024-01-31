import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<Integer, String> map1 = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            map.put(s, i + 1);
            map1.put(i + 1, s);
        }

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (map.containsKey(s)) {
                int n = map.get(s);
                bw.write(String.valueOf(n));
                bw.newLine();
            } else {
                bw.write(map1.get(Integer.parseInt(s)));
                bw.newLine();
            }
        }

        bw.close();
    }
}
