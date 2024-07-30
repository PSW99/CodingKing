import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, R, Q;
    private static int[] parent, size;
    private static List<List<Integer>> tree = new ArrayList<>();
    private static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        size = new int[N + 1];
        for (int i = 0; i < N + 1; i++){
            tree.add(new ArrayList<>());
            list.add(new ArrayList<>());
        }


        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(u).add(v);
            list.get(v).add(u);
        }

        makeTree(R, -1);
        countSubtreeNodes(R);
        StringBuilder sb = new StringBuilder();

        while (Q-- > 0) {
            int q = Integer.parseInt(br.readLine());
            sb.append(size[q]).append("\n");
        }
        System.out.println(sb);
    }

    private static void makeTree(int curNode, int p) {
        for (int node : list.get(curNode)) {
            if (node != p) {
                tree.get(curNode).add(node);
                parent[node] = curNode;
                makeTree(node, curNode);
            }
        }
    }

    private static void countSubtreeNodes(int curNode) {
        size[curNode] = 1;
        for (int node : tree.get(curNode)) {
            countSubtreeNodes(node);
            size[curNode] += size[node];
        }
    }


}
