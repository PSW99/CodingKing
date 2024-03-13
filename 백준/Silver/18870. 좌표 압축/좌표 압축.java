import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static List<Integer> list = new ArrayList<>();
    private static Set<Integer> set = new TreeSet<>();
    private static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            list.add(n);
            set.add(n);
        }
        Iterator<Integer> iter = set.iterator();
        int count = 0;
        while (iter.hasNext()) {
            map.put(iter.next(), count);
            count++;
        }

        for (Integer integer : list) {
            sb.append(map.get(integer)).append(" ");
        }

        System.out.println(sb);
    }
}
