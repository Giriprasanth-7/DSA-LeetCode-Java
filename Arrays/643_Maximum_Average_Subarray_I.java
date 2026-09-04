class Solution {
    public double findMaxAverage(int[] nums, int k) {
       int n=nums.length;
       int windowSum=0;
       for(int i=0;i<k;i++){
        windowSum+=nums[i];
       } 
       int maxAns=windowSum;
       for(int j=k;j<n;j++){
        windowSum+=nums[j];
        windowSum-=nums[j-k];
        maxAns=Math.max(maxAns,windowSum);
       }
       return (double) maxAns/k;
    }
}
