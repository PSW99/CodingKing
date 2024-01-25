import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //도시개수
        int[] roads = new int[N - 1]; //도시간 도로 길이
        int[] oilPrice = new int[N -1]; //주유소 리터당 가격
        int answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            roads[i] = Integer.parseInt(st.nextToken());
        }
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            oilPrice[i] = Integer.parseInt(st1.nextToken());
            minPrice = Math.min(minPrice, oilPrice[i]);
        }

        int distance = 0;
        int price = 0;
        for (int i = 0; i < N - 1; i++) {
            distance = roads[i];
            price = oilPrice[i];
            answer += distance * price;
            if (price == minPrice && i + 1 < N - 1) {
                int sum = 0;
                for (int j = i + 1; j < N - 1; j++) {
                    sum += roads[j];
                }
                answer += sum * minPrice;
                System.out.println(answer);
                break;
            }
        }
    }
}
