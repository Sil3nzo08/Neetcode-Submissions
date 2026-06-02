class Solution {
    public int largestRectangleArea(int[] heights) {
        // each element in stack is [index, height]
        Stack<int[]> stack = new Stack<>();
        int maxArea = 0;

        for (int index = 0; index < heights.length; index++) {
            

            if (stack.isEmpty()) { stack.push(new int[] {index, heights[index]}); continue; }

            // stack not empty
            int[] top = stack.peek();

            if (top[1] == heights[index]) {
                // Don't do anything, keep that rectangles index, needs to keep extending
            } else if (top[1] < heights[index]) {
                // Monotonic increasing stack, so all elements shall keep extending
                stack.push(new int[] {index, heights[index]});
            } else {
                // Keep popping until we find a height smaller than current height
                // Compute all elements in stack that can't be further extended
                while (!stack.isEmpty()) {
                    int[] newTop = stack.peek();

                    if (newTop[1] >= heights[index]) {
                        top = stack.pop();
                        maxArea = Math.max(maxArea, (index - top[0]) * top[1]);
                    } else {
                        break;
                    }
                }

                stack.push(new int[] {top[0], heights[index]});
            }
        }

        // Leftovers in stack imply they could be extended to the end of the histogram
        while (!stack.isEmpty()) {
            int[] top = stack.pop();

            maxArea = Math.max(maxArea, (heights.length - top[0]) * top[1]);
        }

        return maxArea;
    }
}
