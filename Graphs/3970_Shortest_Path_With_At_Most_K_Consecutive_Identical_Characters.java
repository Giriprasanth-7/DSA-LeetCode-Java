import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[node][consecutive count]
        int[][] dist = new int[n][k + 1];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<long[]> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        int startCount = 1;
        dist[0][startCount] = 0;
        pq.offer(new long[]{0, 0, startCount});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long cost = cur[0];
            int u = (int) cur[1];
            int count = (int) cur[2];

            if (cost != dist[u][count]) continue;

            if (u == n - 1) return (int) cost;

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int weight = edge[1];

                int nextCount = labels.charAt(u) == labels.charAt(v)
                    ? count + 1 : 1;

                if (nextCount > k) continue;

                long newCost = cost + weight;

                if (newCost < dist[v][nextCount]) {
                    dist[v][nextCount] = (int) newCost;
                    pq.offer(new long[]{newCost, v, nextCount});
                }
            }
        }

        return -1;
    }
}
