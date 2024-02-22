import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                list.add(Integer.valueOf(st.nextToken()));
            }
            map.put(key, list);
        }

        for (int i = 1; i <= N; i++) {
            List<Integer> list = map.get(i);
            int count = 0;
            for (int j = 19; j >=1 ; j--) {
                int standard = list.get(j);
                for (int k = j-1; k >=0 ; k--) {
                    if (standard < list.get(k)) count++;
                }
            }
            System.out.println(i + " " + count);
        }
    }
}
