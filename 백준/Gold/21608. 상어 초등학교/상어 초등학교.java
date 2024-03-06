import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] arr;
    private static Queue<Integer> queue = new LinkedList<>();
    private static HashMap<Integer, List<Integer>> students = new HashMap<>(); //학생번호,학생이 좋아하는 학생번호
    private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken()); //학생
            queue.add(key);
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                list.add(Integer.valueOf(st.nextToken()));
            }
            students.put(key, list);
        }

        seatSelection();
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                List<Integer> list = students.get(arr[i][j]);
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    int student = arr[nx][ny];
                    for (int n : list) {
                        if (student == n) count++;
                    }
                }
                switch(count) {
                    case 1: answer += 1; break;
                    case 2: answer += 10; break;
                    case 3: answer += 100; break;
                    case 4: answer += 1000; break;
                }
            }
        }
        System.out.println(answer);

    }

    private static void seatSelection() {
        while (!queue.isEmpty()) {
            int sNumber = queue.poll(); //학생 번호
            List<Integer> likeStudents = students.get(sNumber); //자리에 앉아야할 학생이 좋아하는 학생번호
            int s1 = likeStudents.get(0);
            int s2 = likeStudents.get(1);
            int s3 = likeStudents.get(2);
            int s4 = likeStudents.get(3);

            List<int[]> list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int likeCount = 0;
                    int emptyCount = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                        int current = arr[nx][ny];
                        if(current==s1||current==s2||current==s3||current==s4) likeCount++;
                        if (current ==0) emptyCount++;
                    }
                    list.add(new int[]{likeCount, emptyCount, i, j});
                }
            }

            list.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] n1, int[] n2) {
                    if (n1[0] != n2[0]) return Integer.compare(n2[0], n1[0]); // 좋아하는 학생의 수가 많은 순서로 정렬
                    if (n1[1] != n2[1]) return Integer.compare(n2[1], n1[1]); // 빈 자리의 수가 많은 순서로 정렬
                    if (n1[2] != n2[2]) return Integer.compare(n1[2], n2[2]); // 행 기준으로 정렬
                    return Integer.compare(n1[3], n2[3]); // 열 기준으로 정렬
                }
            });

            for (int[] ints : list) {
                int x = ints[2];
                int y = ints[3];
                if (arr[x][y] == 0) {
                    arr[x][y] = sNumber;
                    break;
                }
            }

        }

    }

}
