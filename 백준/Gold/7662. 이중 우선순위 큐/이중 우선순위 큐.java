import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();

            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cal = st.nextToken();
                int n = Integer.parseInt(st.nextToken());
                if (cal.equals("I")) {
                    map.put(n, map.getOrDefault(n, 0) + 1);
                }
                if (cal.equals("D") && n == -1) {
                    if (!map.isEmpty()) {
                        int key = map.firstKey();
                        if (map.get(key) == 1)
                            map.remove(key);
                        else
                            map.put(key, map.get(key) - 1);
                    }
                } // 최솟값 삭제
                if (cal.equals("D") && n == 1) {
                    if (!map.isEmpty()) {
                        int key = map.lastKey();
                        if (map.get(key) == 1)
                            map.remove(key);
                        else
                            map.put(key, map.get(key) - 1);
                    }
                } // 최댓값 삭제
            }

            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }
    }
}
