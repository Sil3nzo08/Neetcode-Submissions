class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int leftPointer = 0;
        int rightPointer = numbers.length - 1;

        while (leftPointer <= rightPointer) {
            int sum = numbers[leftPointer] + numbers[rightPointer];

            if (sum == target) {
                return new int[] {leftPointer + 1, rightPointer + 1};
            } else if (sum > target) {
                rightPointer--;
            } else if (sum < target) {
                leftPointer++;
            }
        }

        // Shouldn't reach here.
        return null;
    }
}
/*
================================
- Time: O(n) 
    > We check each element in the array once (either via the left or right pointer). Moves 
      together to meet in the middle.
- Space: O(1)
    > Only need space for left and right pointers, and sum.
================================

Key takeways:
    - Binary searching every element gives time O(nlog(n)). 
    - If the current indexes are > target, then just move right pointer down to find next
      largest. This works because it means there are NO possible combinations with the
      right pointer number that makes the sum. Similarly, if the current indexes are < 
      target, then just move left pointer up to find next smallest. This works as there
      are NO possible combinations with the left pointer number that makes the sum. This
      approach ensures that right pointer is the MAXIMUM currently and left pointer is
      the MINIMUM currently (from sorted list)
*/
