import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static int[] blueCards;
    private static int[] arr;
    private static List<Integer> list = new ArrayList<>(); //뽑은 카드 목록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        blueCards = new int[M];
        arr = new int[N + 1];
        for (int i = 0; i < N + 1; i++) arr[i] = i;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) blueCards[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(blueCards);
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            int drawnCard = Integer.parseInt(st.nextToken());
            int find = binarySearch(drawnCard) + 1;
            if (list.isEmpty()) {
                list.add(find);
                sb.append(blueCards[find]).append("\n");
            } else {
                int a = list.get(0);
                if (findParent(a) != findParent(find)) {
                    sb.append(blueCards[find]).append("\n");
                    union(a, find);
                } else {
                    for (int j = find + 1; j < M; j++) {
                        if (findParent(a) != findParent(j)) {
                            sb.append(blueCards[j]).append("\n");
                            union(a, j);
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(sb);
    }

    private static int binarySearch(int n) {
        int right = M - 1;
        int left = 0;
        int mid;
        while (left <= right) {
            mid = (right + left) / 2;
            if (n == blueCards[mid]) {
                return mid;
            } else if (n < blueCards[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;

    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        if (ap != bp) arr[bp] = ap;
    }

    private static int findParent(int x) {
        if (arr[x] == x) return arr[x];
        else return arr[x] = findParent(arr[x]);
    }
}
