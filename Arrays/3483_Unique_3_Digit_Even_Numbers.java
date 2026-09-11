class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int digit : digits) {
            freq[digit]++;
        }
        int count = 0;
        for (int first = 1; first <= 9; first++) {
            if (freq[first] == 0) continue;
            for (int second = 0; second <= 9; second++) {
                if (freq[second] == 0) continue;
                for (int third = 0; third <= 8; third += 2) {
                    if (freq[third] == 0) continue;
                    int[] used = new int[10];
                    used[first]++;
                    used[second]++;
                    used[third]++;
                    boolean possible = true;
                    for (int d = 0; d <= 9; d++) {
                        if (used[d] > freq[d]) {
                            possible = false;
                            break;
                        }
                    }
                    if (possible) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
