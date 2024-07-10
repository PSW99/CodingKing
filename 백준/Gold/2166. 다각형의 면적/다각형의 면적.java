import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        double area = calculateArea(points);
        System.out.printf("%.1f\n", area);
    }

    static double calculateArea(Point[] points) {
        int N = points.length;
        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < N; i++) {
            int nextIndex = (i + 1) % N;
            sum1 += (long) points[i].x * points[nextIndex].y;
            sum2 += (long) points[i].y * points[nextIndex].x;
        }

        return Math.abs(sum1 - sum2) / 2.0;
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}