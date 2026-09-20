class Solution {
    public int reverseDegree(String s) {
     int total = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int revIndex = 26 - (c - 'a');
            int position = i + 1;         
            total += revIndex * position;
        }
        return total;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.reverseDegree("abc"));   
        System.out.println(sol.reverseDegree("zaza"));   
    }
}
