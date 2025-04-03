import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, HP;
    private static int answer = Integer.MAX_VALUE;
    private static List<Skill> skills = new ArrayList<>();
    private static int[] cooldowns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        HP = Integer.parseInt(st.nextToken());
        cooldowns = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int wait = Integer.parseInt(st.nextToken());
            int damage = Integer.parseInt(st.nextToken());

            skills.add(new Skill(damage));
            cooldowns[i] = wait;
        }

        backtracking(0, HP, new int[N]);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void backtracking(int time, int hp, int[] currentCooldown) {
        if (hp <= 0) {
            answer = Math.min(answer, time);
            return;
        }

        boolean used = false;

        for (int i = 0; i < N; i++) {
            if (currentCooldown[i] == 0) {
                used = true;

                int[] nextCooldown = currentCooldown.clone();
                nextCooldown[i] = cooldowns[i];

                for (int j = 0; j < N; j++) {
                    if (nextCooldown[j] > 0) nextCooldown[j]--;
                }

                int nextHp = hp - skills.get(i).damage;

                backtracking(time + 1, nextHp, nextCooldown);
            }
        }

        if (!used) {
            int[] nextCooldown = currentCooldown.clone();
            for (int i = 0; i < N; i++) {
                if (nextCooldown[i] > 0) nextCooldown[i]--;
            }
            backtracking(time + 1, hp, nextCooldown);
        }
    }

    static class Skill {
        int damage;

        public Skill(int damage) {
            this.damage = damage;
        }
    }
}