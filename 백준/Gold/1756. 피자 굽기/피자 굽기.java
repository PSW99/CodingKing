import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int D, N;
    private static int[] ovenDm;
    private static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ovenDm = new int[D];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < D; i++){
            ovenDm[i] = Integer.parseInt(st.nextToken());
            if (i > 0) ovenDm[i] = Math.min(ovenDm[i - 1], ovenDm[i]);
        }
        
            
        st = new StringTokenizer(br.readLine());
        answer = D - 1;

        for (int i = 0; i < N; i++) {
            int pizza = Integer.parseInt(st.nextToken());
            getAnswer(pizza);
        }

        if (answer == 0) System.out.println(answer);
        else System.out.println(answer + 1);

    }

    private static void getAnswer(int pizza) {
        boolean isPush = false;

        for (int i = answer; i >= 0; i--) {
            if (pizza <= ovenDm[i]) {
                answer = i;
                ovenDm[i] = 0;
                isPush = true;
                break;
            }
        }

        if (!isPush) answer = 0;

    }
}
