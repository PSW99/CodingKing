import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Long.valueOf(st.nextToken()));
        }
        list.sort(Comparator.naturalOrder());
        long min = -1;
        int length = N / 2;

        if (N % 2 == 0) {
            for (int i = 0; i < length; i++) {
                min = Math.max(min, list.get(i) + list.get(N - 1 - i));
            }
        } else {
            if (N == 1) {
                System.out.println(list.get(0));
                return;
            }

            for (int i = 0; i < length; i++) {
                min = Math.max(min, list.get(i) + list.get(N - 2 - i));
            }
            min = Math.max(min, list.get(N - 1));
        }
        System.out.println(min);
        
    }
    
}
