import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;
    private static Queue<Node> queue = new ArrayDeque<>();
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int answer = 0;
    private static List<Block> blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean flag = false;
            visited = new boolean[N][N];
            blocks = new ArrayList<>();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] <= 0 || visited[i][j]) continue;
                    int blocks = findBlock(new Node(i, j));
                    max = Math.max(blocks, max);
                    if (blocks >= 2) flag = true;

                }
            }

            if (!flag) break;

            blocks.sort(new Comparator<Block>() {
                @Override
                public int compare(Block o1, Block o2) {
                    if (o1.count != o2.count) return o2.count - o1.count; // 큰 순서대로
                    if (o1.rainbow != o2.rainbow) return o2.rainbow - o1.rainbow; // 무지개 블록 수가 많은 순서대로
                    if (o1.standard.x != o2.standard.x) return o2.standard.x - o1.standard.x; // 기준 블록의 행 번호가 큰 순서대로
                    return o2.standard.y - o1.standard.y; // 기준 블록의 열 번호가 큰 순서대로
                }
            });

            List<Node> list = blocks.get(0).list;
            for (Node node : list) board[node.x][node.y] = -2;
            answer += max * max;

            falling();

            rotating();

            falling();
        }

        System.out.println(answer);
    }

    private static int findBlock(Node start) {
        boolean[][] isVisit = new boolean[N][N];
        List<Node> list = new ArrayList<>();
        list.add(start);
        int color = board[start.x][start.y];
        queue.add(start);
        visited[start.x][start.y] = true;
        isVisit[start.x][start.y] = true;
        int count = 1;
        int rainbow = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || isVisit[nx][ny]) continue;
                if (board[nx][ny] == color || board[nx][ny] == 0) {
                    queue.add(new Node(nx, ny));
                    isVisit[nx][ny] = true;
                    list.add(new Node(nx, ny));
                    count++;
                    if (board[nx][ny] == color) visited[nx][ny] = true;
                    if (board[nx][ny] == 0) rainbow++;
                }
            }
        }

        if (count >= 2) blocks.add(new Block(start, count, rainbow, list));

        return count;
    }

    private static void falling() {
        for (int col = 0; col < N; col++) {
            for (int row = N - 1; row >= 0; row--) {
                if (board[row][col] > -1) { 
                    int currentRow = row;
                    while (currentRow + 1 < N && board[currentRow + 1][col] == -2) {
                        board[currentRow + 1][col] = board[currentRow][col];
                        board[currentRow][col] = -2;
                        currentRow++;
                    }
                }
            }
        }
    }

    private static void rotating() {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[N - j - 1][i] = board[i][j];
            }
        }
        board = newBoard;
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Block {
        Node standard;
        int count, rainbow;
        List<Node> list;

        public Block(Node standard, int count, int rainbow, List<Node> list) {
            this.standard = standard;
            this.count = count;
            this.rainbow = rainbow;
            this.list = list;
        }
    }

}
