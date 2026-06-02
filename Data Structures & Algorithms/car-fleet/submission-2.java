class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        // [[pos, speed], [pos, speed], ...]
        int[][] cars = new int[position.length][2]; 

        for (int i = 0; i < position.length; i++) {
            cars[i] = new int[] {position[i], speed[i]};
        }

        Arrays.sort(cars, (car1, car2) -> car2[0] - car1[0]);

        Stack<Double> stack = new Stack<>();
        for (int[] car : cars) {
            if (stack.isEmpty()) { 
                stack.push((double) (target - car[0]) / car[1]);
            } else {
                double topTime = stack.peek();
                double carTime = (double) (target - car[0]) / car[1];

                if (carTime > topTime) {
                    stack.push(carTime);
                } 
            }
        }

        return stack.size();
    }
}
/*
================================
- Time: O(nlog(n)) 
    > The cars array has n tuples/subarrays in it (length of the position input array), and we loop through
      this giving us O(n). The if statement inside will at most push every car onto the stack, so O(n) is
      preserved there.
    > nlog(n) comes from sorting the cars array prior to working with it. Since this time dominates O(n), 
      time complexity overall is O(nlog(n))
- Space: O(n)
    > We have an array "cars" that is size n, and a stack that grows at most O(n), where every car in "cars"
      is pushed onto it.
================================

Key takeways:
    - Instead of having a separate variable for counting the fleets, we can just return the size of the stack
      (I didn't even know there was a size() function to be honest), since by our implementation, we push 
      only cars that create a new fleet. 
    - Probably should store the speeds as doubles and not floats, since those have double the capacity.
    - With comparators, e.g: Arrays.sort(nums, (a, b) -> ...). Whatever this lambda function returns, if it:
        > Negative, means a comes before b (1st parameter comes before 2nd parameter)
        > Positive, means b comes before a (2nd parameter comes before 1st parameter)
        > == 0, means equal ordering (with ordering, it just puts one ahead of the other; we don't care which
                                     is first in this case)
    - You probably don't need a stack to solve this problem. Just keep track of the previous fleet's time to
      see if the upcoming car will reach the fleet or not. See 2nd sol here.
*/
