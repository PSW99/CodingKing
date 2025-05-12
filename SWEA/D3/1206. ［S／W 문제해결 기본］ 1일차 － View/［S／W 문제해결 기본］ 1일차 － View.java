import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int[][] buildings;
    private static int answer;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            N = Integer.parseInt(br.readLine());
            buildings = new int[255][N];
            arr = new int[N];
            answer = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int height = Integer.parseInt(st.nextToken());
                arr[i] = height;

                for (int j = 0; j < height; j++) {
                    buildings[j][i] = 1;
                }
            }

            for (int i = 0; i < N; i++) {
                if (arr[i] == 0) continue;

                for (int j = arr[i] - 1; j >= 0; j--) {
                    if (isViewRight(j, i)) {
                        answer++;
                    } else {
                        break;
                    }
                }
            }

            System.out.println("#" + test_case + " " + answer);

        }
    }


    private static boolean isViewRight(int x, int y) {
        int ny = y;
        int num = 0;

        for (int i = 0; i < 2; i++) {
            ny = ny - 1;
            if (ny < 0 || ny >= N) return false;
            if (buildings[x][ny] == 0) {
                num++;
            }
        }

        if (num < 2) return false;

        ny = y;
        num = 0;

        for (int i = 0; i < 2; i++) {
            ny = ny + 1;
            if (ny < 0 || ny >= N) return false;
            if (buildings[x][ny] == 0) {
                num++;
            }
        }

        return num >= 2;
    }
}
