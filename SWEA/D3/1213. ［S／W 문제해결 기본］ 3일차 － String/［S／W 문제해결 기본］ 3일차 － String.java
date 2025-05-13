import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());
            String search = br.readLine();
            String word = br.readLine();

            int count = word.length() - word.replace(search, "").length();

            System.out.println("#" + test_case + " " + count / search.length());

        }
    }
}
