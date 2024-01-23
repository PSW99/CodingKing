import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N; //스위치 개수
    private static int[] switches; //스위치
    private static int students; //학생 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        switches = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }
        students = Integer.parseInt(br.readLine());
        for (int i = 0; i < students; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int gender= Integer.parseInt(st1.nextToken());
            int number = Integer.parseInt(st1.nextToken());
            if (gender == 1) {
                for (int j = 1; j <= N / number; j++) {
                    switches[number * j - 1] ^= 1;
                }
            } else {
                int index = number - 1;
                int right = 0;
                int left = 0;
                while (true) {
                    right++;
                    left++;
                    if (index + right > N - 1 || index - left < 0) {
                        break;
                    }
                    if (switches[index - left] == switches[index + right]) {
                        switches[index - left] ^= 1;
                        switches[index + right] ^= 1;
                    } else {
                        break;
                    }
                }
                switches[index] ^= 1;
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(switches[i]);
        }


    }
}
