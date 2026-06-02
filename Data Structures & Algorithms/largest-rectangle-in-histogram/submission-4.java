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
/*
================================
- Time: O(n) 
    > At most, we push and pop every height (with its index) onto, and from the
      stack. The while loop doesn't concern us with increasing time complexity
      because of this observation.
- Space: O(n)
    > We use a stack, and at most, it grows with all of the heights in original
      input array
================================

Key takeways:
    - We use a monotonic increasing stack to keep track of which "heights" can
      still be extended (extending its width). This is due to the nature of 
      monotonic increasing behaviour, because if the top element can be extended,
      then so can all the bottom elements too. Then, when we get a smaller one
      that breaks/stops extension of the top element, we compute its area and
      compare with max, and keep popping until we find elements again that can 
      still be extended. The intuition I guess here is that since we are popping
      the most recent bars first, so maybe a stack should be needed.
    - The video does an excellent explanation of this (keep extending) behaviour,
      so 100% recommend checking it out.
*/

