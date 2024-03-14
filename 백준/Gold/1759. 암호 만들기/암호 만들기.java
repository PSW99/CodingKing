import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int L, C;
    private static List<Character> alphabet = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabet.add(st.nextToken().charAt(0));
        }

        alphabet.sort(Comparator.naturalOrder());

        backtracking(0, 0);
    }

    private static void backtracking(int start, int count) {
        if (count == L) {
            String word = "";
            int gather = 0;
            int consonant = 0;
            for (int i = 0; i < C; i++) {
                if (visited[i]) {
                    char x = alphabet.get(i);
                    word += x;
                    if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
                        gather++;
                    } else consonant++;
                }
            }
            if (gather >= 1 && consonant >= 2) System.out.println(word);
            return;
        }

        for (int i = start; i < C; i++) {
            visited[i] = true;
            backtracking(i + 1, count + 1);
            visited[i] = false;
        }

    }

}
