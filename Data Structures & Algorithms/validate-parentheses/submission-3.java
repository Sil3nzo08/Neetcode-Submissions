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
/*
================================
- Time: O(n)
    > We visit each character in the string once.
- Space: O(n)
    > Our stack can grow to the size of the input string if all brackets are opening brackets. e.g: "({{["
================================

Key takeways:
    - Valid parentheses must follow a last-opened, first-closed order, just like the stack framework (LIFO).
      You should use a stack if you need quick access to the most recent unresolved item. Naturally fits 
      problems like nesting, matching, backtracking, monotonic behaviour (values move in only one direction,
      e.g: 1, 3, 4, 6, 8 is monotonically increasing), etc. 
    - Could've used a hashmap to map each closing bracket to its opening bracket counterpart instead of doing
      this insanely big condition for the if statment.
*/
