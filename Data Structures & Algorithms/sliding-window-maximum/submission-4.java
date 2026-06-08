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
/*
================================
- Time: O(nlog(n)) 
    > Adding to and polling from the heap is O(log(n)) time, and since we are doing this for every window
      as we traverse the array, it is O(nlog(n)) asymptotically.
- Space: O(n)
    > We are using a maxHeap to store the elements of the array
================================

Key takeaways:
    - The solution given, wasn't even the most optimal solution. I also had to use hints to figure out that a
      heap could be used to solve the problem. 
    - I guess deriving the optimal solution from the brute force obvious one was clever and intuitive. This
      is because, let's say you have a window [1, 1, 1, 1, 6]. Then the max is obviously 6, but we NEVER need
      to check the preceding 4 1's because they'll never be the max for the following windows since the window
      will be shifting to the right always. However, if we have a window like [6, 5, 4], even though the max is
      6, we still need 5 and 4 because when the window gets shifted to the right, 6 will be out, but 5 and 4 
      remain, so there's potential for them to be the new max. Consequently, we need a data structure that 
      can stay monotonically decreasing: [1, 1, 1, 1, 6, 5, 4] => we only need to check [6, 5, 4] for the 
      following windows. Something that can remove previous elements that are smaller than the new one we're 
      adding, and can easily add new elements to the front. This is where a queue comes into action! Reason
      being we're adding and removing elements from the right, and also removing elements from the left (due
      to the window shifting to the right; elements out of the window need to be removed). 
    - There's also a dynamic solution approach, which also gives O(n) time complexity (but less optimal I think
      because you have to go through the entire array 3 times). Essentially, you split the array into blocks of
      size k, and define leftMax and rightMax for these blocks. That way, you can calculate the max in O(1) 
      time on the third loop. By defining blocks of size k, you could get one of the following scenarios:
        [block 1 | block 2]         <-- In this case, take the maximum of rightMax(block1) && leftMax(block2)
        [block 1]                   <-- In this case, take maximum of rightMax(block1) && leftMax(block1)
      So the formula works regardless of the two possibilities. The reason there can't be more blocks in
      the window is precisely why size k was chosen. This should be practically impossible to figure out 
      on your own I swear...
*/