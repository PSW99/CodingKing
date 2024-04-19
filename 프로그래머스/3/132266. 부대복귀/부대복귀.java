import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    Queue<Integer> queue;
    int[] dist;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        dist = new int[n + 1];
        for (int i = 0; i < n + 1; i++) list.add(new ArrayList<>());
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            list.get(u).add(v);
            list.get(v).add(u);
        }
        int index = 0;
        for (int start : sources) {
            dijkstra(start,destination);
            if (dist[destination] == Integer.MAX_VALUE) answer[index] = -1;
            else answer[index] = dist[destination];
            index++;
        }

        return answer;
    }

    void dijkstra(int start,int destination) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[dist.length];
        queue = new ArrayDeque<>();
        queue.add(start);
        dist[start] = 0;
        visited[start] = true;
                
        while (!queue.isEmpty()) {
            int n = queue.poll();

            if (n == destination) break;
            for (int end : list.get(n)) {
                if (visited[end]) continue;
                
                if (dist[n] + 1 < dist[end]) {
                    dist[end] = dist[n] + 1;
                    queue.add(end);
                    visited[end] = true;
                }
            }
        }
    
    }
}