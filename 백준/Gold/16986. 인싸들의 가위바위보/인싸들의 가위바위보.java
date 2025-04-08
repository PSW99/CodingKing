import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[] winCount = new int[3];
    private static int[] gameCount = new int[3];
    private static int[][] handGestures = new int[3][20];
    private static boolean[] isUsed;
    private static int[][] compatibility;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        compatibility = new int[N][N];
        isUsed = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int result = Integer.parseInt(st.nextToken());
                compatibility[i][j] = result;
            }
        }

        for (int i = 1; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 20; j++) {
                handGestures[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        backTracking(0, 0, 1);
        System.out.println(answer);
    }


    private static void backTracking(int start, int front, int back) {
        if (answer == 1) {
            return;
        }
        if (winCount[1] == K || winCount[2] == K) {
            return;
        }

        if (winCount[0] == K) {
            answer = 1;
            return;
        }

        int nonParticipant = 3 - (front + back);

        int frontGesture = handGestures[front][gameCount[front]];
        int backGesture = handGestures[back][gameCount[back]];

        gameCount[front]++;
        gameCount[back]++;

        if (front == 0) {
            for (int j = 0; j < N; j++) {
                if (isUsed[j]) {
                    continue;
                }

                isUsed[j] = true;
                if (compatibility[j][backGesture] == 0) {
                    winCount[back]++;
                    backTracking(start + 1, back, nonParticipant);
                    winCount[back]--;
                } else if (compatibility[j][backGesture] == 1) {
                    winCount[back]++;
                    backTracking(start + 1, back, nonParticipant);
                    winCount[back]--;
                } else {
                    winCount[front]++;
                    backTracking(start + 1, front, nonParticipant);
                    winCount[front]--;
                }
                isUsed[j] = false;
            }

        } else if (back == 0) {
            for (int j = 0; j < N; j++) {
                if (isUsed[j]) {
                    continue;
                }

                isUsed[j] = true;
                if (compatibility[frontGesture][j] == 0) {
                    winCount[back]++;
                    backTracking(start + 1, back, nonParticipant);
                    winCount[back]--;
                } else if (compatibility[frontGesture][j] == 1) {
                    winCount[front]++;
                    backTracking(start + 1, front, nonParticipant);
                    winCount[front]--;
                } else {
                    winCount[front]++;
                    backTracking(start + 1, front, nonParticipant);
                    winCount[front]--;
                }
                isUsed[j] = false;
            }
        } else {
            if (compatibility[frontGesture][backGesture] == 0) {
                winCount[back]++;
                backTracking(start + 1, back, nonParticipant);
                winCount[back]--;
            } else if (compatibility[frontGesture][backGesture] == 1) {
                int winner = Math.max(back, front);
                winCount[winner]++;
                backTracking(start + 1, winner, nonParticipant);
                winCount[winner]--;
            } else {
                winCount[front]++;
                backTracking(start + 1, front, nonParticipant);
                winCount[front]--;
            }
        }
        gameCount[front]--;
        gameCount[back]--;
    }
}
