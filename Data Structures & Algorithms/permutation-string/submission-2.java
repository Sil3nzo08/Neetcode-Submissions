class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) { return false; }

        // Construct character frequency in s1
        int[] s1CharFreq = new int[26];
        int[] s2CharFreq = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1CharFreq[s1.charAt(i) - 'a'] += 1;
            s2CharFreq[s2.charAt(i) - 'a'] += 1; // Populate s2 as well to same length
        }

        // Check for premutation equality
        if (Arrays.equals(s1CharFreq, s2CharFreq)) {
            return true;
        }

        // Sliding window (fixed size)
        int lp = 0;
        for (int rp = s1.length(); rp < s2.length(); rp++) {
            //System.out.println("RP: " + rp);
            //System.out.println(Arrays.toString(s1CharFreq));
            //System.out.println(Arrays.toString(s2CharFreq));

            // Re-validate window by shifting lp up by 1
            s2CharFreq[s2.charAt(rp) - 'a'] += 1;
            s2CharFreq[s2.charAt(lp) - 'a'] -= 1;
            lp++;
            
            // Check for premutation equality
            if (Arrays.equals(s1CharFreq, s2CharFreq)) {
                return true;
            }
        }

        return false;
    }
}
/*
================================
- Time: O(n) 
    > We loop through all the elements in s1 and s2, and update the array (which is a 
      D.I.Y. hashmap) by adding and removing their count from the frequency as needed, 
      which is O(1) operation.
    > Note that checking if the arrays are equal everytime is O(26n) time, since the 
      arrays have length 26, so its O(26), which gets asymptotically reduced to O(n)>
      Interestingly, there is a more optimal solution...
- Space: O(1)
    > Just some extra variables for pointers for the sliding window.
    > The arrays have a fixed size of 26, so they don't grow when the input grows! Thus, 
      it stays at O(1) space.
================================

Key takeaways:
    - Very similar to the Anagram problem, where you keep a tally of the s1 string and
      any valid window in s2 to efficiently check for permutations, since the character
      counts are what matters, not the ordering of them. 
    - What I've done here ISN'T the optimal solution. This is because we are checking 
      if the arrays are equal EVERY SINGLE TIME for a valid window, which isn't needed.
      That means we have to loop through the 26 corresponding rows to check for equality
      each time, which makes our solution O(26n), and not O(n) by default. Instead, the
      optimal method is to store the matches between s1 and s2 hashmaps and update these
      matches whenever you add/remove a tally for the frequency as we slide the window.
      If at any point the matches == 26, that is all rows of the array match, we've found
      a permutation, and can thus return true. 
*/
