class Solution {
    public int maxSubArray(int[] nums) {
        int currsum=0;
        int maxsum=nums[0];
        for(int num:nums){
            currsum+=num;
            maxsum=Math.max(currsum,maxsum);
            if(currsum<0){
                currsum=0;
            }
        }
        return maxsum;
    }
}
