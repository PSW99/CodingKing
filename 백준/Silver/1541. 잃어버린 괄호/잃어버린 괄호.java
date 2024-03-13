import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> list = new ArrayList<>();
        String s = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                list.add(sb.toString());
                sb = new StringBuilder();
                list.add(String.valueOf(s.charAt(i)));
                continue;
            }

            sb.append(s.charAt(i));

            if (i == s.length() - 1) list.add(sb.toString());
        }

        int minusIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("-")) {
                minusIndex = i;
                break;
            }
        }

        if (minusIndex > 0) {
            int a = 0;
            for (int i = 0; i < minusIndex; i += 2) {
                a += Integer.parseInt(list.get(i));
            }
            int b = 0;
            for (int i = minusIndex + 1; i < list.size(); i += 2) {
                b += Integer.parseInt(list.get(i));
            }
            System.out.println(a + (-1 * b));
        } else {
            int answer = 0;
            for (int i = 0; i < list.size(); i += 2) {
                answer += Integer.parseInt(list.get(i));
            }
            System.out.println(answer);
        }

    }
}
