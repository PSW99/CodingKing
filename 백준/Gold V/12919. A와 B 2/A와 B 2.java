import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        dfs(S, T);
        if (list.contains(S)) {
            System.out.println(1);
        }else System.out.println(0);

    }

    private static void dfs(String s, String t) {
        if (s.length() == t.length()) {
            return;
        }

        if (t.charAt(0) == 'B') {
            String substring = t.substring(1);
            StringBuilder sb = new StringBuilder(substring);
            String string = sb.reverse().toString();
            list.add(string);
            dfs(s, string);
        }

        if (t.charAt(t.length() - 1) == 'A') {
            list.add(t.substring(0, t.length() - 1));
            dfs(s, t.substring(0, t.length() - 1));
        }

    }
}
