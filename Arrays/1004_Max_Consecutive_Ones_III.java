class Solution {
    public int longestOnes(int[] nums, int k) {
        int n=nums.length;
        int left=0,zero_count=0;
        for(int right=0;right<n;right++){
            if(nums[right]==0){
                zero_count++;
            }
            if(zero_count>k){
                if(nums[left]==0){
                    zero_count--;
                }
                left++;
            }
        }
        return n-left;
    }
}
