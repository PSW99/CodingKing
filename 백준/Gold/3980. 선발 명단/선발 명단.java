import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int T;
    private static int answer;
    private static boolean[] player;
    private static boolean[] position;
    private static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            player = new boolean[11];
            position = new boolean[11];
            answer = Integer.MIN_VALUE;
            list = new ArrayList<>();
            for (int j = 0; j < 11; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int[] ability = new int[11];
                for (int k = 0; k < 11; k++) {
                    ability[k] = Integer.parseInt(st.nextToken());
                }
                list.add(ability);
            }

            backTracking(0, 0, 0);

            System.out.println(answer);
        }
    }

    private static void backTracking(int start, int count, int ability) {
        if (count == 11) {
            answer = Math.max(ability, answer);
            return;
        }

        for (int i = start; i < 11; i++) {
            int[] arr = list.get(i);
            if (player[i]) {
                continue;
            }
            player[i] = true;

            for (int j = 0; j < 11; j++) {
                if (position[j] || arr[j] == 0) {
                    continue;
                }

                position[j] = true;
                backTracking(i + 1, count + 1, ability + arr[j]);
                position[j] = false;
            }

            player[i] = false;
        }
    }

}
