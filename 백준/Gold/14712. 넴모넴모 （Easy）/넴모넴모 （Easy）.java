import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] arr;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        backtracking(0, 0);
        System.out.println(answer);
    }

    private static void backtracking(int count,int start) {
        if (isNemMo()) answer++;
        if (count == N * M) return;
        
        for (int i = start; i < N * M; i++) {
            int x = i / M;
            int y = i % M;
            if (arr[x][y] == 1) continue;
            arr[x][y] = 1;
            backtracking(count + 1, i + 1);
            arr[x][y] = 0;
        }
    }

    private static boolean isNemMo() {
        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < M-1; j++) {
                if (arr[i][j] == 1 && arr[i][j + 1] == 1 && arr[i + 1][j] == 1 && arr[i + 1][j + 1] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
