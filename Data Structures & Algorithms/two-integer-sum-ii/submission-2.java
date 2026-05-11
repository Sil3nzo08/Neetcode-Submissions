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
move down if nums[0] + nums[n-1] > target
if nums[0] + nums[n-1] < target, move up pointer because that vmeans nums[0] can't possibly
be an index for a sum. -100 4 < -90. No we can't
*/
