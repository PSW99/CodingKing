import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int F = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();
            int count = 0;
            int[] arr = new int[F * 2];
            int[] size = new int[F * 2];
            for (int j = 0; j < F * 2; j++) {
                arr[j] = j;
                size[j] = 1;
            }

            for (int j = 0; j < F; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!map.containsKey(a)) map.put(a, count++);
                if (!map.containsKey(b)) map.put(b, count++);
                union(map.get(a), map.get(b), arr,size);
                int ap = findParent(map.get(a), arr);
                int bp = findParent(map.get(b), arr);

                if (ap != bp) sb.append(size[ap] + size[bp]).append("\n");
                else sb.append(size[ap]).append("\n");

            }

        }

        System.out.println(sb);
    }

    private static void union(int a, int b,int[] arr,int[] size) {
        int ap = findParent(a,arr);
        int bp = findParent(b, arr);
        if (ap != bp) {
            size[ap] += size[bp];
            arr[bp] = ap;
        }
    }

    private static int findParent(int x,int[] arr) {
        if (x == arr[x]) return arr[x];
        else return arr[x] = findParent(arr[x], arr);
    }

    private static int find(int x, int[] size) {
        return size[x];
    }
}
