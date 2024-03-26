import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[] friendFee;
    private static int[] arr;
    private static HashMap<Integer, List<Integer>> map = new HashMap<>();
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        friendFee = new int[N + 1];
        arr = new int[N + 1];

        for (int i = 0; i < N + 1; i++) arr[i] = i;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) friendFee[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for (int i = 1; i < N + 1; i++) map.put(findParent(i), new ArrayList<>());
        for (int i = 1; i < N + 1; i++) map.get(findParent(i)).add(i);

        map.forEach((key, value) -> {
            int min = Integer.MAX_VALUE;
            for (int i : value) {
                min = Math.min(min, friendFee[i]);
            };
            answer += min;
        });

        if (answer <= K) System.out.println(answer);
        else System.out.println("Oh no");



    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        arr[bp] = ap;
    }

    private static int findParent(int x) {
        if (x == arr[x]) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }
}
