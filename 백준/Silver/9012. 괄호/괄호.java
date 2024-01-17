import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int count;
    private static String[] stArr;
    private static final char op = '('; //여는 괄호
    private static int opCount = 0; //여는 괄혼 카운트

    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = Integer.parseInt(br.readLine());
        stArr = new String[count];
        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stArr[i] = st.nextToken();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().init();
        for (int i = 0; i < count; i++) {
            String s= stArr[i];
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == op) {
                    opCount++;
                } else {
                    opCount--;
                    if (opCount < 0) {
                        System.out.println("NO");
                        break;
                    } //여는괄호 수 가 마이너스면 VPS가 아니다.
                }
            }
            if (opCount == 0) {  // 여는괄호 수 가 0이면 VPS 이다.
                System.out.println("YES");
            } else if (opCount >= 1) {
                System.out.println("NO");
            } //여는괄호 수 가 1보다 크거나 같으면 VPS가 아니다.
            opCount = 0;
        }
    }

}
