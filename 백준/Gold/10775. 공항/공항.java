import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int G, P;
    private static int[] gates;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        gates = new int[G + 1];
        for (int i = 0; i < G + 1; i++) gates[i] = i;

        int count = 0;
        for (int i = 0; i < P; i++) {
            int airplane = Integer.parseInt(br.readLine());
            if (findParent(airplane) < 1) break;
            union(gates[airplane] - 1,gates[airplane]);
            count++;
        }

        System.out.println(count);
    }

    private static void union(int a, int b) {
        int ap = findParent(a);
        int bp = findParent(b);
        gates[bp] = ap;
    }

    private static int findParent(int x) {
        if (x == gates[x]) return gates[x];
        else return gates[x] = findParent(gates[x]);
    }
}
