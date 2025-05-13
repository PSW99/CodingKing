import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    private static int N;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            N = Integer.parseInt(br.readLine());
            board = new char[8][8];
            int answer = 0;
            StringBuilder sb;

            for (int i = 0; i < 8; i++) {
                String input = br.readLine();
                for (int j = 0; j < 8; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j <= 8 - N; j++) {

                    sb = new StringBuilder();

                    for (int k = j; k < j + N; k++) {
                        sb.append(board[i][k]);
                    }

                    String string = sb.toString();
                    String reverse = sb.reverse().toString();

                    if (string.equals(reverse)) {
                        answer++;
                    }
                }
            } // 가로 확인

            for (int i = 0; i <= 8 - N; i++) {
                for (int j = 0; j < 8; j++) {

                    sb = new StringBuilder();

                    for (int k = i; k < i + N; k++) {
                        sb.append(board[k][j]);
                    }

                    String string = sb.toString();
                    String reverse = sb.reverse().toString();

                    if (string.equals(reverse)) {
                        answer++;
                    }
                }
            } // 세로 확인


            System.out.println("#" + test_case + " " + answer);
        }

    }
}
