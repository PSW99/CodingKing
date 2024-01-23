
import java.io.*;
import java.util.*;

public class Main {
    private static int N; //단어의 개수
    private static int M; //외울 단어 최소길이
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static HashMap<String, Integer> map = new HashMap<>();
    private static HashMap<Integer, List<String>> map1 = new LinkedHashMap<>();
    private static List<Integer> list = new ArrayList<>();

    private void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (s.length() >= M) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        map.forEach((key,value)->{
            map1.put(value, map1.getOrDefault(value, new ArrayList<>()));
            map1.get(value).add(key);
        });

    }
    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            // 문자열 길이 비교
            int lengthComparison = Integer.compare(s1.length(), s2.length());

            // 길이가 같으면 사전 순으로 비교
            if (lengthComparison == 0) {
                return s1.compareTo(s2);
            }

            // 길이가 긴 것이 먼저 오도록 정렬
            return -lengthComparison;
        }
    }


    public static void main(String[] args) throws IOException {
        new Main().init();

        map1.forEach((key,value) -> {
            value.sort(new MyComparator());
            list.add(key);
            Collections.sort(list,Collections.reverseOrder());
        });

        for (int i = 0; i < list.size(); i++) {
            List<String> strings = map1.get(list.get(i));
            for (String s : strings) {
                bw.write(s + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

}
