import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }

        int max = -1;
        int S = 0;
        int T = 0;
        for (int i = 0; i < N-1; i++) {
            String left = list.get(i);
            for (int j = i + 1; j < N; j++) {
                String right = list.get(j);
                int n = countPrefix(right, left);
                if (n > max ) {
                    max = n;
                    S = i;
                    T = j;
                }
            }
        }

        System.out.println(list.get(S));
        System.out.println(list.get(T));
    }

    private static int countPrefix(String s1, String s2) {
        int count = 0, size = Math.min(s1.length(), s2.length());
        for (int i = 0; i < size; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                count++;
            }else break;
        }
        return count;
    }
}
