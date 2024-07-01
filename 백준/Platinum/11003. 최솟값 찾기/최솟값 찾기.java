import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] D = new int[N];
        PriorityQueue<Element> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.offer(new Element(A[i], i));
            
            while (pq.peek().index <= i - L) pq.poll();
            
            D[i] = pq.peek().value;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(D[i]).append(" ");

        System.out.println(sb);
    }

    static class Element implements Comparable<Element> {
        int value;
        int index;

        Element(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.value, other.value);
        }
    }
}