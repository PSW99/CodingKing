import java.util.*;

class Solution {
    // 상하좌우 이동을 위한 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        
        // 각 칸이 몇 번 덩어리에 속하는지 기록할 배열
        int[][] oilIdMap = new int[n][m];
        // 덩어리 번호(ID)와 그 크기를 저장할 맵
        Map<Integer, Integer> oilSize = new HashMap<>();
        int chunkId = 1;
        
        // 1. 격자 전체를 순회하며 BFS로 석유 덩어리 파악
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 석유가 있고 아직 어떤 덩어리에도 속하지 않은 칸이라면 탐색 시작
                if (land[i][j] == 1 && oilIdMap[i][j] == 0) {
                    int size = bfs(land, oilIdMap, i, j, chunkId, n, m);
                    oilSize.put(chunkId, size);
                    chunkId++; // 다음 덩어리를 위해 ID 증가
                }
            }
        }
        
        // 2. 각 열(Column)에 시추관을 뚫었을 때의 최대 석유량 계산
        int maxOil = 0;
        for (int j = 0; j < m; j++) {
            // 중복된 덩어리를 여러 번 더하지 않기 위해 Set 사용
            Set<Integer> currentColumnOils = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (oilIdMap[i][j] > 0) {
                    currentColumnOils.add(oilIdMap[i][j]);
                }
            }
            
            // Set에 모인 덩어리들의 크기를 모두 합산
            int currentTotal = 0;
            for (int id : currentColumnOils) {
                currentTotal += oilSize.get(id);
            }
            
            // 최댓값 갱신
            maxOil = Math.max(maxOil, currentTotal);
        }
        
        return maxOil;
    }

    private int bfs(int[][] land, int[][] oilIdMap, int x, int y, int chunkId, int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        
        oilIdMap[x][y] = chunkId; // 방문 처리 및 덩어리 ID 부여
        int size = 1; // 덩어리 크기 카운트 시작
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            
            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                // 격자 범위를 벗어나지 않는지 확인
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 연결된 석유이고 아직 방문하지 않았다면
                    if (land[nx][ny] == 1 && oilIdMap[nx][ny] == 0) {
                        oilIdMap[nx][ny] = chunkId; // 덩어리 ID 부여 (방문 처리)
                        queue.add(new int[]{nx, ny}); // 큐에 추가하여 탐색 이어나감
                        size++; // 크기 1 증가
                    }
                }
            }
        }
        
        return size; // 최종적으로 연결된 덩어리의 크기를 반환
    }
}