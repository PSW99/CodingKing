import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = {'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'};
        for (int i = 0; i < chars.length; i++) map.put(chars[i], 0);


        while (true) {
            int count = 0;
            String s = br.readLine();
            if (s.equals("#")) break;
            for (int i = 0; i < s.length(); i++) {
                if (map.containsKey(s.charAt(i))) count++;
            }
            System.out.println(count);
        }
    }
}
