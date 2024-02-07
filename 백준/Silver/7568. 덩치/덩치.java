import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            int rank = 1;	// rank 는 1 부터 시작

            for(int j = 0; j < N; j++) {
                if(i == j) continue;	// 같은 사람은 비교할 필요가 없음

		        /*
		        i 번째 사람과 j 번째 사람을 비교하여 i 가 j 보다
		        덩치가 작을 경우 rank 값을 1 증가시킨다
		        */
                if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
                    rank++;
                }
            }

            // i 의 랭크값을 출력
            System.out.print(rank + " ");

        }

    }
}
