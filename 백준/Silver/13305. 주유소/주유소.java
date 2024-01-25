import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //도시개수
        long[] roads = new long[N - 1]; //도시간 도로 길이
        long[] oilPrice = new long[N]; //주유소 리터당 가격
        long answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            roads[i] = Integer.parseInt(st.nextToken());
        }
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            oilPrice[i] = Integer.parseInt(st1.nextToken());
        }

        long minPrice = oilPrice[0];

        for (int i = 0; i < N - 1; i++) {
            if (oilPrice[i] < minPrice) {
                minPrice = oilPrice[i];
            }
            answer += minPrice * roads[i];
        }

        System.out.println(answer);
    }
}
