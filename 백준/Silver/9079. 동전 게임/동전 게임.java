import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int T;
    private static boolean[] visited;
    private static Queue<int[][]> queue;
    private static int answer;
    private static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            int[][] arr = new int[3][3];
            for (int i = 0; i < 3; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    String s = st.nextToken();
                    if (s.equals("H")) arr[i][j] = 1;
                    else arr[i][j] = 0;
                }
            }

            visited = new boolean[512];
            queue = new ArrayDeque<>();
            answer = 0;
            flag = false;

            visited[convertDecimal(arr)] = true;
            queue.add(arr);

            bfs();

            if (flag) {
                System.out.println(answer);
            } else {
                System.out.println(-1);
            }
        }
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[][] cur = queue.poll();
                if (isSame(cur)) {
                    flag = true;
                    return;
                }

                for (int j = 0; j < 3; j++) {
                    int[][] newArr = rowChange(j, cur);
                    int index = convertDecimal(newArr);
                    if (!visited[index]) {
                        queue.add(newArr);
                        visited[index] = true;
                    }

                    newArr = colChange(j, cur);
                    index = convertDecimal(newArr);
                    if (!visited[index]) {
                        queue.add(newArr);
                        visited[index] = true;
                    }
                }

                for (int j = 0; j < 2; j++) {
                    int[][] newArr = crossChange(j, cur);
                    int index = convertDecimal(newArr);
                    if (!visited[index]) {
                        queue.add(newArr);
                        visited[index] = true;
                    }
                }
            }
            answer++;
        }
    }

    private static int[][] colChange(int column, int[][] arr) {
        int[][] clone = new int[3][3];
        for (int i = 0; i < 3; i++) {
            clone[i] = arr[i].clone(); // Make a deep copy of the row
        }
        for (int i = 0; i < 3; i++) {
            clone[i][column] = (arr[i][column] == 1 ? 0 : 1);
        }
        return clone;
    }

    private static int[][] rowChange(int row, int[][] arr) {
        int[][] clone = new int[3][3];
        for (int i = 0; i < 3; i++) {
            clone[i] = arr[i].clone(); // Make a deep copy of the row
        }
        for (int i = 0; i < 3; i++) {
            clone[row][i] = (arr[row][i] == 1 ? 0 : 1);
        }
        return clone;
    }

    private static int[][] crossChange(int dir, int[][] arr) {
        int[][] clone = new int[3][3];
        for (int i = 0; i < 3; i++) {
            clone[i] = arr[i].clone(); // Make a deep copy of the row
        }
        for (int i = 0; i < 3; i++) {
            if (dir == 0) clone[i][i] = (arr[i][i] == 1 ? 0 : 1);
            else clone[i][2 - i] = (arr[i][2 - i] == 1 ? 0 : 1);
        }
        return clone;
    }

    private static int convertDecimal(int[][] arr) {
        int now = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                now = now * 2 + arr[i][j];
            }
        }
        return now;
    }

    private static boolean isSame(int[][] arr) {
        int temp = arr[0][0];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (temp != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
