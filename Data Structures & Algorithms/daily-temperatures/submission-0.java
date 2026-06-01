class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                int stackIndex = stack.peek();

                if (temperatures[i] < temperatures[stackIndex]) {
                    result[i] = stackIndex - i;
                    break;
                } else {
                    stack.pop();
                }
            }
            
            stack.push(i);            
        }

        return result; 
    }
}
