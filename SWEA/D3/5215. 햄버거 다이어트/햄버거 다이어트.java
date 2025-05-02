import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N, L;
    private static Food[] foods;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());


            answer = Integer.MIN_VALUE;
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            foods = new Food[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int calorie = Integer.parseInt(st.nextToken());

                foods[i] = new Food(score, calorie);
            }

            backTracking(0, 0, 0);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void backTracking(int start, int score, int calorie) {
        if (calorie > L) {
            return;
        }

        answer = Math.max(answer, score);

        for (int i = start; i < N; i++) {
            Food food = foods[i];
            backTracking(i + 1, score + food.score, calorie + food.calorie);
        }
    }

    private static class Food {
        int score, calorie;

        public Food(int score, int calorie) {
            this.score = score;
            this.calorie = calorie;
        }
    }
}
