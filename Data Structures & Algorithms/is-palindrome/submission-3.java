class Solution {
    public boolean isPalindrome(String s) {
        // Check palindrome validity
        int leftPointer = 0;
        int rightPointer = s.length() - 1;
        while (leftPointer < rightPointer) {
            char left = Character.toLowerCase(s.charAt(leftPointer));
            char right = Character.toLowerCase(s.charAt(rightPointer));

            // Keep shifting up, if we don't have an alphanumeric character
            if (!Character.isLetter(left) && !Character.isDigit(left)) {
                leftPointer++;
                continue;
            } 
            // Keep shifting down, if we don't have an alphanumeric character
            if (!Character.isLetter(right) && !Character.isDigit(right)) {
                rightPointer--;
                continue;
            } 

            // Character check to see if same for palindrome
            if (left != right) {
                //System.out.println(left + "|" + right);
                return false;
            }

            leftPointer++;
            rightPointer--;
        }

        return true;
    }
}

/*
================================
- Time: O(n)
    > Looping through all elements in string
- Space: O(1)
    > Have a couple of integer and character variables.
    > Number of space isn't growing as input grows
================================

Key Takeaways:
- There's an inbuilt function to check if character is alphanumeric (a letter or a digit/number):
    Character.isLetterOrDigit(c), where "c" is your char.
- You may be asked to not use the in-built method, in which case use the ASCII table.
- Maybe while loops (instead of if statements) to keep incrementing and decrementing the left and right 
  pointers respectively instead of continuing the loop. Helps avoid unnecessarily reassigning "left" and 
  "right" variables
*/
