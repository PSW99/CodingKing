import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] style = new String[N]; //칭호
        int[] limit = new int[N]; //칭호전투력 상한값


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            style[i] = st.nextToken();
            limit[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<M;i++) {
            int num = Integer.parseInt(br.readLine());
            int left = 0;
            int right = N - 1;
            int mid;

            while (left <= right) {
                mid = (left + right) / 2;

                int titleInt = limit[mid];
                if (titleInt < num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            sb.append(style[left]).append("\n");
        }
        System.out.println(sb);

    }

}

