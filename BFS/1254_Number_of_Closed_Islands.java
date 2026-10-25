import java.util.*;

class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) continue;

                boolean closed = true;
                grid[i][j] = 1;
                stack.push(i * n + j);

                while (!stack.isEmpty()) {
                    int pos = stack.pop();
                    int r = pos / n, c = pos % n;

                    if (r == 0 || c == 0 || r == m - 1 || c == n - 1) {
                        closed = false;
                    }

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr >= 0 && nr < m && nc >= 0 && nc < n
                                && grid[nr][nc] == 0) {
                            grid[nr][nc] = 1;
                            stack.push(nr * n + nc);
                        }
                    }
                }

                if (closed) count++;
            }
        }

        return count;
    }
}
