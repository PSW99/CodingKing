import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[][] compatability;
    private static int[][] gestures = new int[3][20];
    private static boolean[] used;
    private static int[] perm;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        compatability = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                compatability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 20; j++) {
                gestures[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        used = new boolean[N];
        perm = new int[N];
        permute(0);

        System.out.println(answer);

    }

    private static void permute(int depth) {
        if (depth == N) {
            simulate();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[depth] = i;
                permute(depth + 1);
                used[i] = false;
            }
        }
    }

    private static void simulate() {
        int[] winCount = new int[3];
        int[] turn = new int[3];

        int p1 = 0, p2 = 1;

        while (true) {
            if (winCount[0] == K || winCount[1] == K || winCount[2] == K) {
                return;
            }

            if (turn[0] >= N || turn[1] >= N || turn[2] >= N) {
                return;
            }

            int g1 = getGesture(p1, turn[p1]);
            int g2 = getGesture(p2, turn[p2]);

            int result = compatability[g1][g2];

            int winner;
            if (result == 2) {
                winner = p1;
            } else if (result == 1) {
                winner = Math.max(p1, p2);
            } else {
                winner = p2;
            }

            winCount[winner]++;
            turn[p1]++;
            turn[p2]++;

            int next = 3 - p1 - p2;
            p1 = winner;
            p2 = next;

            if (winCount[0] == K) {
                answer = 1;
                return;
            }
        }
    }

    private static int getGesture(int player, int t) {
        if (player == 0) return perm[t];
        return gestures[player][t];
    }
}