import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();

        int oneCnt = 0;
        int zeroCnt = 0;
        for (int i = 0; i < S.length(); i++) {
           if (S.charAt(i) == '1') oneCnt++;
           else zeroCnt++;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zeroCnt/2; i++) {
            sb.append("0");
        }
        for (int i = 0; i < oneCnt / 2; i++) {
            sb.append("1");
        }

        System.out.println(sb);
    }
}
