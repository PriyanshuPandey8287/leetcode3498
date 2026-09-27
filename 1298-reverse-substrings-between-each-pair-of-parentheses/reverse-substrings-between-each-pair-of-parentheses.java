class Solution {
    public String reverseParentheses(String s) {
        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == ')') {
                StringBuilder temp = new StringBuilder();

                // '(' tak characters remove karo
                while (stack.charAt(stack.length() - 1) != '(') {
                    temp.append(stack.charAt(stack.length() - 1));
                    stack.deleteCharAt(stack.length() - 1);
                }

                // '(' remove karo
                stack.deleteCharAt(stack.length() - 1);

                // reversed string wapas add karo
                stack.append(temp);

            } else {
                stack.append(ch);
            }
        }

        return stack.toString();
    }
}