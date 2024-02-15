import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Integer.valueOf(st.nextToken()));
        }
        list.sort(Comparator.naturalOrder());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (binarySearch(i)) answer++;
        }

        System.out.println(answer);
    }

    private static boolean binarySearch(int i) {
        int number = list.get(i);
        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            int lN = list.get(left);
            int rN = list.get(right);


            if (lN + rN < number) {
                left++;
            } else if (lN + rN > number) {
                right--;
            } else {
                if (left == i) left++;
                else if (right == i) right--;
                else return true;
            }

        }
        return false;
    }
}
