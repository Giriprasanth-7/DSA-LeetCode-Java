class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[] stack = new int[m * n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;

                int top = 0;
                stack[top++] = i * n + j;
                long sum = 0;

                while (top > 0) {
                    int pos = stack[--top];
                    int r = pos / n, c = pos % n;

                    sum += grid[r][c];
                    grid[r][c] = 0;

                    if (r > 0 && grid[r - 1][c] > 0) {
                        stack[top++] = (r - 1) * n + c;
                        sum += grid[r - 1][c];
                        grid[r - 1][c] = 0;
                    }
                    if (r + 1 < m && grid[r + 1][c] > 0) {
                        stack[top++] = (r + 1) * n + c;
                        sum += grid[r + 1][c];
                        grid[r + 1][c] = 0;
                    }
                    if (c > 0 && grid[r][c - 1] > 0) {
                        stack[top++] = r * n + c - 1;
                        sum += grid[r][c - 1];
                        grid[r][c - 1] = 0;
                    }
                    if (c + 1 < n && grid[r][c + 1] > 0) {
                        stack[top++] = r * n + c + 1;
                        sum += grid[r][c + 1];
                        grid[r][c + 1] = 0;
                    }
                }

                if (sum % k == 0) count++;
            }
        }

        return count;
    }
}
