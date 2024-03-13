import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    private static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            if (number == 0 && pq.isEmpty()) {
                bw.write("0");
                bw.newLine();
            } else if (number == 0 && !pq.isEmpty()) {
                bw.write(String.valueOf(pq.poll()));
                bw.newLine();
            } else pq.add(number);
        }

        bw.close();
    }
}
