class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        // [[pos, speed], [pos, speed], ...]
        int[][] cars = new int[position.length][2]; 

        for (int i = 0; i < position.length; i++) {
            cars[i] = new int[] {position[i], speed[i]};
        }

        Arrays.sort(cars, (car1, car2) -> car2[0] - car1[0]);

        Stack<Float> stack = new Stack<>();
        int fleets = 0;
        for (int[] car : cars) {
            if (stack.isEmpty()) { 
                stack.push((float) (target - car[0]) / car[1]);
                fleets++;
            } else {
                float topTime = stack.peek();
                float carTime = (float) (target - car[0]) / car[1];

                if (carTime > topTime) {
                    stack.push(carTime);
                    fleets++;
                } 
            }
        }

        return fleets;
    }
}
