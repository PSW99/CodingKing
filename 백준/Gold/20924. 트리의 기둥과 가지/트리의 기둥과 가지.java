import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, R;
    private static boolean[] visited;
    private static List<List<Node>> tree = new ArrayList<>();
    private static int pillar, branch = Integer.MIN_VALUE;
    private static int gigaNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) tree.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree.get(parent).add(new Node(child, weight));
            tree.get(child).add(new Node(parent, weight));
        }

        findPillar(R, 0);
        findBranch(gigaNode, 0);
        if (branch == Integer.MIN_VALUE) branch = 0;
        System.out.println(pillar + " " + branch);

    }

    private static void findPillar(int start, int sum) {
        if (tree.get(start).size() > 2 || (tree.get(start).size() == 1 && start != R) || (tree.get(start).size() == 2 && start == R)) {
            pillar = sum;
            gigaNode = start;
            return;
        }

        for (int i = 0; i < tree.get(start).size(); i++) {
            Node node = tree.get(start).get(i);
            if (!visited[node.child]) {
                visited[node.child] = true;
                findPillar(node.child, sum + node.weight);
            }
        }
    }

    private static void findBranch(int start, int sum) {
        if (tree.get(start).size() == 1) {
            branch = Math.max(branch, sum);
            return;
        }

        for (int i = 0; i < tree.get(start).size(); i++) {
            Node node = tree.get(start).get(i);
            if (!visited[node.child]) {
                visited[start] = true;
                sum += node.weight;
                findBranch(node.child, sum);
                sum -= node.weight;
                visited[start] = false;
            }
        }
    }

    private static class Node {
        int child, weight;

        public Node(int child, int weight) {
            this.child = child;
            this.weight = weight;
        }
    }
}
