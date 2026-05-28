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
