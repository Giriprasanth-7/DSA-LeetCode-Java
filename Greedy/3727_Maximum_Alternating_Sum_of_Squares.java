import java.util.*;
class Solution {
public long maxAlternatingSum(int[] nums) {
Arrays.sort(nums);
long sum = 0;
for (int num : nums) {
sum += (long) num * num;
}
Arrays.sort(nums, 0, 0);
long[] squares = new long[nums.length];
for (int i = 0; i < nums.length; i++) {
squares[i] = (long) nums[i] * nums[i];
}
Arrays.sort(squares);
int n = squares.length;
long result = 0;
for (int i = 0; i < n / 2; i++) {
result += squares[n - 1 - i] - squares[i];
}
if (n % 2 == 1) {
result += squares[n / 2];
}
return result;
}
}
