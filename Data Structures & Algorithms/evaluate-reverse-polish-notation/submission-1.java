class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            try {
                int val = Integer.parseInt(token);
                stack.push(val);
                continue;
            } catch (Exception e) {
                // Do nothing
            }

            // Isn't a number
            int val1 = stack.pop();
            int val2 = stack.pop();
            switch (token) {
                case "+" -> {stack.push(val1 + val2); }
                case "-" -> {stack.push(val2 - val1); }
                case "*" -> {stack.push(val1 * val2); }
                case "/" -> {stack.push(val2 / val1); }
            }
        }

        // Should be left with one value in the stack
        return stack.pop();
    }
}
/*
================================
- Time: O(n) amortized
    > We visit each token in the original String[] array once, so that's O(n). 
      Additionally, we'll push each number token on and off the stack, and each 
      operator will pop 2 elements and push 1, asymptotically giving us O(n) too since
      these push and pop commands are O(1). Therefore, total time complexity is 
      O(n) + O(n) = O(2n) = O(n)
- Space: O(n)
    > We're using a stack to store all the tokens (except operators) from the original
      String[] array.
================================

Key takeways:
    - With division, RPN (I think...) specifically wants the value to truncate to 0. 
      That means to round down (floor) when values are >0, and round up (ceiling)
      when values are <0. For example, 5/3 = 1 and -5/3 = -1 (NOT -2). In Java, 
      integer division already does this truncating to 0, but with doubles, you'll
      need to convert/cast it to an int to do the truncating for you! Some languages
      like python will always round down, so -5/3 = -2, and in that language, all you
      need to do is convert it to an int. 
*/