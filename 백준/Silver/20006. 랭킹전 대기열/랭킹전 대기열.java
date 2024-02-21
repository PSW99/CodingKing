import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Player {
        String name;
        int level;

        public Player(String name, int level) {
            this.name = name;
            this.level = level;
        }
    }

    static class Room {
        int capacity;
        List<Player> players;

        public Room(int capacity) {
            this.capacity = capacity;
            players = new ArrayList<>();
        }

        public boolean isFull() {
            return players.size() == capacity;
        }

        public void addPlayer(Player player) {
            players.add(player);
        }

        public void sortPlayers() {
            players.sort(Comparator.comparing(p -> p.name));
        }

        public void printStatus() {
            if (isFull()) {
                System.out.println("Started!");
            } else {
                System.out.println("Waiting!");
            }
            for (Player player : players) {
                System.out.println(player.level + " " + player.name);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int P = Integer.parseInt(st.nextToken()); // 플레이어의 수
        int M = Integer.parseInt(st.nextToken()); // 방의 정원

        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            boolean isMatched = false;
            for (Room room : rooms) {
                if (!room.isFull() && Math.abs(room.players.get(0).level - level) <= 10) {
                    room.addPlayer(new Player(name, level));
                    isMatched = true;
                    break;
                }
            }

            if (!isMatched) {
                Room room = new Room(M);
                room.addPlayer(new Player(name, level));
                rooms.add(room);
            }
        }

        for (Room room : rooms) {
            room.sortPlayers();
            room.printStatus();
        }
    }
}
