class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n=nums.length;
        int left=0;
        int minlength=Integer.MAX_VALUE;
        int sum=0;
        for(int right=0;right<n;right++){
            sum+=nums[right];
            while(sum>=target){
                minlength=Math.min(minlength,right-left+1);
                sum-=nums[left];
                left++;
            }
        }
        return minlength==Integer.MAX_VALUE?0:minlength;
    }
}
