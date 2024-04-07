import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            list.add(n);
        }
        list.sort(Comparator.naturalOrder());

        for (int i = 0; i < N; i++) {
            sb.append(list.get(i)).append("\n");
        }

        System.out.println(sb);
    }
}
