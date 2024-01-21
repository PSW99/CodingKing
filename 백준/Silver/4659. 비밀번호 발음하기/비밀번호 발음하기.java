
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int vCount = 0; //모음 카운트
    private static int cCount = 0; //자음 카운트

    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            if (s.equals("end")) {
                break;
            }
            if (s.contains("a") || s.contains("e") || s.contains("i") || s.contains("o") || s.contains("u")) {
                for (int i = 0; i < s.length(); i++) {
                    if (isVowel(s.charAt(i))) {
                        cCount = 0;
                        vCount++;
                    } else {
                        vCount = 0;
                        cCount++;
                    }
                    if (vCount >= 3 || cCount >= 3) {
                        System.out.println("<" + s + "> is not acceptable.");
                        vCount = 0;
                        cCount = 0;
                        break;
                    }
                    if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1) && s.charAt(i) != 'e' && s.charAt(i) != 'o') {
                        System.out.println("<" + s + "> is not acceptable.");
                        vCount = 0;
                        cCount = 0;
                        break;
                    }
                    if (i == s.length() - 1) {
                        System.out.println("<" + s + "> is acceptable.");
                        vCount = 0;
                        cCount = 0;
                    }
                }
            } else {
                System.out.println("<" + s + "> is not acceptable.");
            }
        }

    }

    private boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();

    }
}
