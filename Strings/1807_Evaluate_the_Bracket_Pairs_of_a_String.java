import java.util.*;
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean inside = false;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                inside = true;
                key.setLength(0);
            }
            else if (ch == ')') {
                inside = false;
                String k = key.toString();
                if (map.containsKey(k)) {
                    result.append(map.get(k));
                } else {
                    result.append('?');
                }
            }
            else if (inside) {
                key.append(ch);
            }
            else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
