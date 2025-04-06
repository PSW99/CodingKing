import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, P, bossHP;
    private static char[][] map;
    private static HashMap<Character, Integer> playerDamage = new HashMap<>();
    private static List<Node> player = new ArrayList<>();
    private static Player[] arrivalTime;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] != '.' && map[i][j] != 'X'  && map[i][j] != 'B') {
                    player.add(new Node(i, j, 0));
                }
            }
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            String player = st.nextToken();
            int damage = Integer.parseInt(st.nextToken());
            playerDamage.put(player.charAt(0), damage);
        }
        bossHP = Integer.parseInt(br.readLine());

        arrivalTime = new Player[P];
        for (int i = 0; i < P; i++) {
            Node node = player.get(i);
            int time = calTimeToReachBoss(node);
            arrivalTime[i] = new Player(map[node.x][node.y], time);
        }

        System.out.println(calLoot());
    }

    private static int calTimeToReachBoss(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        boolean[][] visited = new boolean[N][M];
        visited[node.x][node.y] = true;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int x = curNode.x;
            int y = curNode.y;
            int time = curNode.time;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 'X') continue;
                if (map[nx][ny] == 'B') {
                    return time + 1;
                }
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, time + 1));
            }

        }

        return 0;
    }

    private static int calLoot() {
        Set<Character> arrivalPlayers = new HashSet<>();

        while (true) {
            if (!arrivalPlayers.isEmpty()) {
                for (char arrivalPlayer : arrivalPlayers) {
                    bossHP -= playerDamage.get(arrivalPlayer);
                }
            }

            if (bossHP < 0) {
                break;
            }

            for (int i = 0; i < P; i++) {
                Player player = arrivalTime[i];
                if (player.arrivalTime > 0) {
                    player.arrivalTime--;
                } else if (player.arrivalTime == 0) {
                    arrivalPlayers.add(player.c);
                }
            }
        }

        return arrivalPlayers.size();
    }

    private static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    private static class Player {
        char c;
        int arrivalTime;

        public Player(char c, int arrivalTime) {
            this.c = c;
            this.arrivalTime = arrivalTime;
        }
    }
}
