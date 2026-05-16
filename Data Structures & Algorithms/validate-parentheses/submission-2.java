class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) { return false; }
                char closestOpenBracket = stack.pop();

                if ((closestOpenBracket == '(' && c != ')') ||
                    (closestOpenBracket == '[' && c != ']') ||
                    (closestOpenBracket == '{' && c != '}')) {
                        return false;
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
