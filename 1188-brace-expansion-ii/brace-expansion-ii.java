import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = dfs(expression, 0, expression.length() - 1);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> dfs(String s, int l, int r) {

        Set<String> result = new HashSet<>();
        result.add("");

        int i = l;

        while (i <= r) {

            Set<String> current = new HashSet<>();

            // Case 1: opening brace
            if (s.charAt(i) == '{') {

                int count = 0;
                int j = i;

                for (; j <= r; j++) {
                    if (s.charAt(j) == '{') count++;
                    else if (s.charAt(j) == '}') count--;

                    if (count == 0) break;
                }

                current = dfs(s, i + 1, j - 1);

                i = j + 1;

            } 
            // Case 2: normal character
            else {

                current.add(String.valueOf(s.charAt(i)));
                i++;
            }

            // Concatenation
            Set<String> temp = new HashSet<>();

            for (String a : result) {
                for (String b : current) {
                    temp.add(a + b);
                }
            }

            result = temp;

            // Skip comma
            if (i <= r && s.charAt(i) == ',') {
                i++;

                // Union of alternatives
                Set<String> next = dfs(s, i, r);

                result.addAll(next);
                break;
            }
        }

        return result;
    }
}