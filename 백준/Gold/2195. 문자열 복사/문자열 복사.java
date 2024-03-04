import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String P = br.readLine();
        
        int startIndex = 0;
        int count = 0;
        for (int i = 0; i < P.length(); i++) {
            if (S.contains(P.substring(startIndex, i + 1))) continue;
            count++;
            startIndex = i;
        }

        System.out.println(count + 1);
    }
}
