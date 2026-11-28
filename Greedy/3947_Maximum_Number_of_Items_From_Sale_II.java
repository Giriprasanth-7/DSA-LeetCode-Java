
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] multiples = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            for (int j = f; j <= n; j += f) {
                multiples[f] += freq[j];
            }
        }

        int[][] sorted = items.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

        long remaining = budget;
        long selected = 0;

        for (int[] item : sorted) {
            int factor = item[0];
            int price = item[1];
            int capacity = multiples[factor] - 1;

            if (price > 2L * minPrice) break;

            long take = Math.min((long) capacity, remaining / price);

            selected += take;
            remaining -= take * price;
        }

        return (int) (2 * selected + remaining / minPrice);
    }
}
