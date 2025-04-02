import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] minNutrion;
    private static int[][] food;
    private static boolean[] visited;
    private static int minPrice = Integer.MAX_VALUE;
    private static HashMap<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        food = new int[N][5];
        visited = new boolean[N];
        minNutrion = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minNutrion[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                food[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0);

        if (minPrice == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minPrice);
            List<Integer> result = map.get(minPrice);
            Collections.sort(result);
            for (int num : result) {
                System.out.print(num + " ");
            }
        }
    }

    private static void backtracking(int count, int start) {
        if (count >= 1) {
            checkNutrition();
        }

        for (int i = start; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            backtracking(count + 1, i + 1);
            visited[i] = false;
        }
    }

    private static void checkNutrition() {
        List<Integer> foodNumber = new ArrayList<>();
        int protein = 0;
        int fat = 0;
        int carbohydrate = 0;
        int vitamin = 0;
        int price = 0;


        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                protein += food[i][0];
                fat += food[i][1];
                carbohydrate += food[i][2];
                vitamin += food[i][3];
                price += food[i][4];
                foodNumber.add(i+1);
            }
        }

        if (minNutrion[0] > protein) {
            return;
        }

        if (minNutrion[1] > fat) {
            return;
        }

        if (minNutrion[2] > carbohydrate) {
            return;
        }

        if (minNutrion[3] > vitamin) {
            return;
        }


        if (price < minPrice) {
            minPrice = price;
            map.clear(); // 기존 더 비싼 조합 제거
            map.put(minPrice, new ArrayList<>(foodNumber));
        } else if (price == minPrice) {
            List<Integer> existing = map.get(minPrice);
            if (compare(foodNumber, existing) < 0) {
                map.put(minPrice, new ArrayList<>(foodNumber));
            }
        }

    }

    private static int compare(List<Integer> a, List<Integer> b) {
        int size = Math.min(a.size(), b.size());
        for (int i = 0; i < size; i++) {
            if (a.get(i) < b.get(i)) return -1;
            if (a.get(i) > b.get(i) ) return 1;
        }
        return 0;
    }
}
