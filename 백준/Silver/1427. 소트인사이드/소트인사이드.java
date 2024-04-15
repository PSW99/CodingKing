import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) list.add((s.charAt(i) - '0'));

        list.sort(Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (Integer integer : list) sb.append(integer);

        System.out.println(sb);
    }
}
