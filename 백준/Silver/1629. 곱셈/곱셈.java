import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static long C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(pow(A, B));

    }

    private static long pow(long A, long exponent) {
        if(exponent == 0) {
            return 1;
        }

        long temp = pow(A, exponent / 2);

        if(exponent % 2 == 1) {
            return (temp * temp % C) * A % C;
        }
        return temp * temp % C;
    }

}
