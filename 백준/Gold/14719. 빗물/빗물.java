import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken()); //가로
        int width = Integer.parseInt(st.nextToken()); //세로
        int[][] block = new int[length][width];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < width; i++) {
            int n = Integer.parseInt(st1.nextToken());
            for (int j = length - 1; j >= length - n; j--) {
                block[j][i] = 1;
            }
            for (int j = 0; j < length - n; j++) {
                block[j][i] = 0;
            }
        }

        int water = 0;
        boolean right = false;
        int L = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (j<width-1 && block[i][j] == 1 && block[i][j + 1] == 0 && !right) {
                    L = j;
                    right = true;
                    continue;
                }
                if (block[i][j] == 1 && right) {
                    right = false;
                    water += (j - L - 1);
                    if (j < width - 1 && block[i][j + 1] == 0) {
                        right = true;
                        L = j;
                    }
                }

            }
            right = false;
            L = 0;
        }
        System.out.println(water);
    }
}
