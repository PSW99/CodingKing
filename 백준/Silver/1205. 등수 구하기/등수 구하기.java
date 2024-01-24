import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int score = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        List<Integer> scoreList = new ArrayList<>();
        if (N > 0) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                scoreList.add(Integer.parseInt(st1.nextToken()));
            }
        }
        
        int rank = 0;

        for (int i = 0; i < scoreList.size(); i++) {
            if (scoreList.get(i) >= score) {
                rank++;
            } else {
                break;
            }
        }

        scoreList.add(rank, score);
        int minus = 0;
        if (rank+1 > P) {
            System.out.println(-1);
        } else {
            for (int i = rank-1; i >= 0; i--) {
                if (scoreList.get(i) == score) {
                    minus++;
                } else {
                    break;
                }
            }
            System.out.println(rank+1 - minus);
        }

    }
}
