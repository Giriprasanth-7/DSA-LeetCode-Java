import java.util.*;
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0, count = s1.length();
        for (int right = 0; right < s2.length(); right++) {
            char ch = s2.charAt(right);
            int val = map.getOrDefault(ch, 0);
            if (val > 0)
                count--;
            map.put(ch, val - 1);
            if (count == 0)
                return true;
            if (right - left + 1 == s1.length()) {
                char leftChar = s2.charAt(left);
                int leftVal = map.get(leftChar);
                if (leftVal >= 0)
                    count++;
                map.put(leftChar, leftVal + 1);
                left++;
            }
        }
        return false;
    }
}
