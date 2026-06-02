class Solution {
    public int search(int[] nums, int target) {
        int lp = 0;
        int rp = nums.length - 1;

        while (lp <= rp) {
            int mp = lp + ((rp - lp) / 2);
            //System.out.println(mp);

            if (nums[mp] == target) {
                return mp;
            } else if (nums[mp] > target) {
                // Move right pointer down
                rp = mp - 1;
            } else {
                // Move left pointer up
                lp = mp + 1;
            }
        }

        return -1;
    }
}
/*
================================
- Time: O(log(n)) 
    > Every time we run the while loop, we're cutting the possible input to be checked in the array by half (divide by 2,
      as shown by moving either the lp or rp to the midpoint mp). As a result, this while loop will only run 5 times if
      nums had length of 16, 6 times if nums had length of 32, and so on. You can recognize that log_2(nums.length) gives
      us the amount of times this while loop will run
- Space: O(1)
    > Only needed some extra variables for left, middle, and right pointers
================================

Key takeways:
    - To avoid integer overflow, instead of calculating the midpoint using "(l + r) / 2", we use "l + ((r - l) / 2)".
      This is because adding l + r, when l and r are very close to the max integer limit, can potentially cause an 
      integer overflow into the negatives, which makes your midpoint calculation incorrect. The latter option achieves
      the same thing, but uses subtraction, and since r >= l, there won't be overflow and it will always produce an answer
      >= 0. To read the second one, you literally calculate half the distance from r and l, and then add this distance 
      starting from l's position to give us the midpoint between them!
    - "lp <= rp" instead of "lp < rp" in this implementation, because when lp == rp, we need to check the element that 
      they're both pointing to. Also, "lp = mp + 1" and "rp = mp - 1" rather than "lp = mp" and "rp = mp", because the 
      latter causes an infinite while loop. Imagine we have lp = 3, and rp = 4, thus having mp = 3, and if the element 
      at that index was > target, then lp = 3, but it was already at 3, so we end up in an infinite loop. Same behaviour
      occurs when lp = rp = 3, so mp = 3, and so we actually don't change the lp/rp in the iteration, resulting in an
      infinite loop.
*/
