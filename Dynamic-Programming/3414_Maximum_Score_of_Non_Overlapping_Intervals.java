class Solution {
    long[][] dp;
    List<Integer>[][] ans;

    int find(int[][] a, int target, int end) {
        int l = 0, r = end;
        while (l<r){
            int mid = l+(r-l)/2;
            if (a[mid][1]<target)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    boolean smaller(List<Integer> a, List<Integer> b) {
        for (int i = 0;i<Math.min(a.size(),b.size());i++) {
            if (!a.get(i).equals(b.get(i)))
                return a.get(i)<b.get(i);
        }
        return a.size()<b.size();
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a = new int[n][4];
        for (int i=0;i<n;i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }
        Arrays.sort(a, (x, y) -> x[1] - y[1]);

        dp = new long[n + 1][5];
        ans = new ArrayList[n + 1][5];
        for (int i=0;i<=n;i++) {
            for (int j=0;j<=4;j++) {
                ans[i][j] = new ArrayList<>();
            }
        }

        for (int i=1;i<=n;i++) {
            int start = a[i-1][0];
            int weight = a[i-1][2];
            int index = a[i-1][3];
            int p = find(a, start, i-1);
            for (int j=1;j<=4;j++) {
                long notTake = dp[i-1][j];
                long take = dp[p][j-1] + weight;
                if (take>notTake){
                    dp[i][j] = take;
                    ans[i][j] = new ArrayList<>(ans[p][j-1]);
                    ans[i][j].add(index);
                    Collections.sort(ans[i][j]);
                } else if (take<notTake) {
                    dp[i][j] = notTake;
                    ans[i][j] = new ArrayList<>(ans[i-1][j]);
                } else {
                    dp[i][j] = take;
                    List<Integer> takeList = new ArrayList<>(ans[p][j-1]);
                    takeList.add(index);
                    Collections.sort(takeList);
                    List<Integer> notTakeList = new ArrayList<>(ans[i-1][j]);
                    if (smaller(takeList, notTakeList))
                        ans[i][j] = takeList;
                    else
                        ans[i][j] = notTakeList;
                }
            }
        }

        return ans[n][4].stream().mapToInt(Integer::intValue).toArray();
    }
}
