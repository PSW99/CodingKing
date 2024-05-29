import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Member> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Member(age, i, name));
        }

        list.sort(new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                if (o1.age == o2.age) return o1.date - o2.date;
                return o1.age-o2.age;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Member member : list) {
            int age = member.age;
            String name = member.name;
            sb.append(age).append(" ").append(name).append("\n");
        }

        System.out.println(sb);
    }

    private static class Member {
        int age, date;
        String name;


        public Member(int age, int date, String name) {
            this.age = age;
            this.date = date;
            this.name = name;
        }
    }
}