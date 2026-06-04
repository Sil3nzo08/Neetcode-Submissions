class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLen = nums1.length + nums2.length;
        int leftPartitionLen = totalLen / 2;

        // A be the smaller of the two
        int[] arrayA = (nums1.length > nums2.length) ? nums2 : nums1;
        int[] arrayB = (nums1.length > nums2.length) ? nums1 : nums2;

        //System.out.println(Arrays.toString(arrayA) + " | " + Arrays.toString(arrayB));

        // Binary search on smaller array: arrayA.
        int lp = 0;
        int rp = arrayA.length - 1;
        while (true) {
            int mp;  // The index of the very left value in the left partition of arrayA
            if (rp < 0) {
                // No elements in A for left partition
                mp = -1;
            } else if (lp > arrayA.length - 1) {
                // All elements in A for right partition
                mp = arrayA.length - 1;
            } else {
                mp = lp + ((rp - lp) / 2); 
            }
    
            int partitionLeftA = (mp < 0) ? Integer.MIN_VALUE : arrayA[mp];
            int partitionRightA = (mp + 1 >= arrayA.length) ? Integer.MAX_VALUE : arrayA[mp + 1];
            int partitionLeftB = (leftPartitionLen - 1 - (mp + 1) < 0) ? Integer.MIN_VALUE : arrayB[leftPartitionLen - 1 - (mp + 1)];
            int partitionRightB = (leftPartitionLen - (mp + 1) >= arrayB.length) ? Integer.MAX_VALUE : arrayB[leftPartitionLen - (mp + 1)];  

            // Check if mp was a good and valid partition
            if (partitionLeftA <= partitionRightB && partitionLeftB <= partitionRightA) {
                // Valid partition, so compute median
                if (totalLen % 2 == 0) {
                    // Even, so need to get avg of the two inner-middle elements
                    return ((double) (Math.max(partitionLeftA, partitionLeftB) + Math.min(partitionRightA, partitionRightB))) / 2;
                } else {
                    // Odd, so get middle element
                    return Math.min(partitionRightA, partitionRightB);
                }
            } else if (partitionLeftA > partitionRightB) {
                // Invalid since something in A's left partition is bigger than in B's right partition, so 
                // decrease A's partition
                rp = mp - 1;
            } else {
                // Invalid since something in B's left partition is bigger than in A's right partition, so 
                // increase A's partition
                lp = mp + 1;
            }
        }

        // return 0.0;
    }
}
/*
================================
- Time: O(log(min(m, n))) 
    > We perform binary search on the smaller of the two arrays given
- Space: O(1)
    > Just some extra space used for pointers, partitioning, and arrays. To elaborate, the arrayA and arrayB are REFERENCES to
      the original arrays "nums1" and "nums2", so no extra space needed, which is why it remains O(1).
================================

Key takeaways:
    - Honestly, could not get this one after thinking for roughly 30 minutes, despite all the hints given. I think the best way
      to come up with the solution on your own is to imagine a merged array, and think of how you could independently get the
      left and right halved partitions without merging in the first place. I think just going through a couple examples (with
      the merged array as reference) helps a ton! Very hard, but i derived my own coded up solution myself, based on my 
      understanding of the solution. I probably should've realised early on, that to find the median, you need to partition
      the entire merged array into HALVES, so a left partition, and right partition that are of equal size if the total merged
      array would be even, and differ in size by 1 if the total merged array would be odd.
    - The syntax: 'variable = (condition) ? a : b;' makes the variable = a if the condition is true, and variable = b if that
      condition was false instead.
*/
