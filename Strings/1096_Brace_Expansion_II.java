class Solution {
    int i = 0;
    public List<String> braceExpansionII(String expression) {
        Set<String> set = parse(expression);
        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }
    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();
        while (i < s.length() && s.charAt(i) != '}') {
            Set<String> term = parseTerm(s);
            result.addAll(term);

            if (i < s.length() && s.charAt(i) == ',') {
                i++;
            }
        }
        return result;
    }
    private Set<String> parseTerm(String s) {
        Set<String> result = new HashSet<>();
        result.add("");
        while (i < s.length() && s.charAt(i) != '}' && s.charAt(i) != ',') {
            Set<String> current;
            if (s.charAt(i) == '{') {
                i++;
                current = parse(s);
                i++;
            } else {
                current = new HashSet<>();
                current.add(String.valueOf(s.charAt(i)));
                i++;
            }
            Set<String> next = new HashSet<>();
            for (String a : result) {
                for (String b : current) {
                    next.add(a + b);
                }
            }
            result = next;
        }
        return result;
    }
}
