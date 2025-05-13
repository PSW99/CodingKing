import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static int N;
    private static int[] boxes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            N = Integer.parseInt(br.readLine());
            boxes = new int[100];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                boxes[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(boxes);

            for (int i = 0; i < N; i++) {
                boxes[99] = boxes[99] - 1;
                boxes[0] = boxes[0] + 1;
                Arrays.sort(boxes);
            }

            int answer = boxes[99] - boxes[0];
            System.out.println("#" + test_case + " " + answer);
        }
    }
}
