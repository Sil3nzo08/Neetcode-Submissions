class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);

        if (minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        int poppedVal = stack.pop();

        if (!minStack.isEmpty() && poppedVal == minStack.peek()) {
            minStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
/*
================================
- Time: O(1) for each function in class
    > All operations implemented here: push(), pop(), top(), and getMin() have time complexity O(1).
    > Note that the push(), pop(), peek() functions inside of a stack (implemented by java here) are O(1).
- Space: O(n)
    > We are using two stacks, each needing O(n) space. Thus, O(n) + O(n) = O(2n) = O(n)
================================

Key takeways:
    - If we use java's implementation of a stack, all the functions: push(), pop(), and peek() are all in 
      O(1) time. You can implement such an optimal stack yourself using ArrayList or Linked Lists really!
    - Interestingly, the way to come up with the solution is relatively simple. The most brute force way of
      doing this problem is, whenever getMin() gets called, we just 'loop' through all elements in the stack,
      and get the min, but that is O(n) time. We can do better, and a way to do so, is to store the 
      CORRESPONDING minimum for each val/node at the precise moment they were pushed onto the stack. In 
      other words, each node in the stack will have 'like' a minimum val associated with it. To 
      illustrate:
      Stack: -2             (From the stack, the moment -2 was pushed onto the stack, the stack has a minimum of -2)
      Stack: -2 0           (From the stack, the moment 0 was pushed onto the stack, the stack has a minimum of -2)
      Stack: -2 0 -4        (From the stack, the moment -4 was pushed onto the stack, the stack has a minimum of -4)
      Stack: -2 0 -4 3      (From the stack, the moment 3 was pushed onto the stack, the stack has a minimum of -4)
      So, we essentially need to make another stack, which will store the minimum at each 'corresponding' node. 
      When pushing to the original stack, we also push to this stack the minimum for that moment, and when 
      popping from the original stack, we pop from the minimum too. You can think of it as every block in the
      stack has its own getMin() function, and thus whatever is at the top of the stack has its getMin() 
      function called when getMin() is needed. Example:
      Stack:    -2
      MinStack: -2              <-- The minimum val of the stack with -2 and all elements below in stack

      Stack:    -2 0
      MinStack: -2 -2           <-- The minimum val of the stack with 0 and all elements below in stack

      Stack:    -2 0 -4
      MinStack: -2 -2 -4        <-- The minimum val of the stack with -4 and all elements below in stack

      Stack:    -2 0 -4 3
      MinStack: -2 -2 -4 -4     <-- The minimum val of the stack with 3 and all elements below in stack
      That way, if we pop 3 from stack, we also pop from minStack, thus restoring the minimum val of the 
      stack before 3 was pushed onto the stack, which is the minimum value of (-2 0 -4;l everything below),
      a.k.a the getMin() for -4. The same 'level' between stack and minStack, means that at that height
      in stack, it has a minimum value stored at the same height in minStack:

      minStack: 
      -4 (minValue of bottom 4 elements)        <-- So, when we pop from original stack, we are left with 3 elements, so we also need to pop this element from minStack to get minimum for the 3 elements left! 
      -4 (minValue of bottom 3 elements)
      -2 (minValue of bottom 2 elements) 
      -2 (minValue of bottom element) 
                

    - How ChatGPT phrased it: "Each element in the stack stores the minimum value of 
    the stack at the moment that element was pushed." OR "Every position in the stack 
    has a corresponding running minimum".

*/
