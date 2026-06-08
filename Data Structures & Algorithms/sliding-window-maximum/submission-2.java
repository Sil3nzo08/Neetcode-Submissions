class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] maxAns = new int[nums.length - k + 1];

        // Stores int[2] = {num, index}
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]
        );      
 
        // Create window
        for (int i = 0; i < k; i++) {
            maxHeap.add(new int[] {nums[i], i});
        }
        maxAns[0] = maxHeap.peek()[0];
        //System.out.println(Arrays.toString(maxAns));

        // Sliding the window
        int lp = 0; 
        for (int rp = k; rp < nums.length; rp++) {
            // Shift lp up by 1, and remove leftmost element 
            int[] top = maxHeap.peek();
            // Keep polling if top element is the one being removed, or element is out of window already
            while (top[1] == lp || top[1] <= rp - k) {
                maxHeap.poll();

                if (maxHeap.isEmpty()) {
                    break;
                } else {
                    top = maxHeap.peek();
                }
            }
            lp++;

            // Now add rp's element
            maxHeap.add(new int[] {nums[rp], rp});
            
            // Get max now with window updated
            maxAns[lp] = maxHeap.peek()[0];

            //System.out.println(Arrays.toString(maxAns));
        }

        return maxAns;
    }
}
