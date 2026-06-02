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
