import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static StringBuilder swab(StringBuilder st, int left, int right, int leftIndex, int rightIndex) {
        char temp = (char) left;
        st.setCharAt(leftIndex, (char) right);
        st.setCharAt(rightIndex, temp);

        return st;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean b = false;
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder max = new StringBuilder(); //max
        StringBuilder min = new StringBuilder(); //min
        for (int i = 9; i>=9-N ; i--) {
            String s = String.valueOf(i);
            max.append(s);
        }
        for (int i = 0; i <= N; i++) {
            String s = String.valueOf(i);
            min.append(s);
        }
        for (int i = 0; i < N; i++) {
            String s = st.nextToken();
            max.insert(2 * i + 1,s);
            min.insert(2 * i + 1,s);
        }

        do {
            b = false;
            for (int i = 0; i < max.length() - 2; i += 2) {
                int maxLeft = max.charAt(i);
                int maxRight = max.charAt(i + 2);
                if (max.charAt(i + 1) == '>') {
                    if (!(maxLeft > maxRight)) {
                        max = swab(max, maxLeft, maxRight, i, i + 2);
                        b = true;
                    }
                } else {
                    if (!(maxLeft < maxRight)) {
                        max = swab(max, maxLeft, maxRight, i, i + 2);
                        b = true;
                    }
                }

                int minLeft = min.charAt(i);
                int minRight = min.charAt(i + 2);
                if (min.charAt(i + 1) == '>') {
                    if (!(minLeft > minRight)) {
                        min = swab(min, minLeft, minRight, i, i + 2);
                        b = true;
                    }
                } else {
                    if (!(minLeft < minRight)) {
                        min = swab(min, minLeft, minRight, i, i + 2);
                        b = true;
                    }
                }
            }
        } while (b);

        for (int i = 1; i < max.length(); i++) {
            max.deleteCharAt(i);
            min.deleteCharAt(i);
        }

        System.out.println(max);
        System.out.println(min);
    }

}
