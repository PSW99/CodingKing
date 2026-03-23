import java.util.*;

class Solution {
    static char[][] map;
    static int row, col;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        row = storage.length;
        col = storage[0].length();
        map = new char[row + 2][col + 2];

        // 전체 '.'으로 초기화
        for (int i = 0; i < row + 2; i++) {
            Arrays.fill(map[i], '.');
        }

        // 컨테이너 정보 채우기 (1,1)부터
        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                map[i][j] = storage[i - 1].charAt(j - 1);
            }
        }

        for (int i = 0; i < requests.length; i++) {
            char request = requests[i].charAt(0);

            if (requests[i].length() == 1) {
                // 지게차: 외부 연결된 빈칸에 인접한 컨테이너만 제거
                boolean[][] reachable = new boolean[row + 2][col + 2];
                Queue<int[]> q = new ArrayDeque<>();

                // 테두리 '.' 전부 BFS 시작점
                for (int r = 0; r < row + 2; r++) {
                    for (int c = 0; c < col + 2; c++) {
                        if ((r == 0 || r == row + 1 || c == 0 || c == col + 1) && map[r][c] == '.') {
                            reachable[r][c] = true;
                            q.offer(new int[]{r, c});
                        }
                    }
                }

                // BFS로 외부 연결된 빈칸 탐색
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    for (int d = 0; d < 4; d++) {
                        int nx = cur[0] + dx[d];
                        int ny = cur[1] + dy[d];
                        if (nx < 0 || ny < 0 || nx >= row + 2 || ny >= col + 2) continue;
                        if (reachable[nx][ny] || map[nx][ny] != '.') continue;
                        reachable[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                }

                // reachable 빈칸에 인접한 해당 컨테이너 제거
                for (int r = 1; r < row + 1; r++) {
                    for (int c = 1; c < col + 1; c++) {
                        if (map[r][c] == request) {
                            for (int d = 0; d < 4; d++) {
                                int nx = r + dx[d];
                                int ny = c + dy[d];
                                if (reachable[nx][ny]) {
                                    map[r][c] = '.';
                                    break;
                                }
                            }
                        }
                    }
                }

            } else {
                // 크레인: 해당 종류 전부 제거
                for (int r = 1; r < row + 1; r++) {
                    for (int c = 1; c < col + 1; c++) {
                        if (map[r][c] == request) {
                            map[r][c] = '.';
                        }
                    }
                }
            }
        }

        // 남은 컨테이너 카운트
        for (int r = 1; r < row + 1; r++) {
            for (int c = 1; c < col + 1; c++) {
                if (map[r][c] != '.') answer++;
            }
        }

        return answer;
    }
}