import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String a = " ";
        String b = "*";
        for (int i = 1; i < n + 1; i++) {
            sb.append(a.repeat(n - i));
            sb.append(b.repeat(i)).append("\n");
        }

        System.out.println(sb);

    }
}
