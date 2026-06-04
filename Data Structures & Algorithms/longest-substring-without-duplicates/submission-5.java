class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) { return 0; }
        if (s.length() == 1) { return 1; }
        
        int lp = 0;
        int rp = 1;

        // Character -> its index in the string s
        Map<Character, Integer> seen = new HashMap<>();
        seen.put(s.charAt(lp), lp);

        int longestSubstring = 0;
        // THE OPTIMAL SOLUTION
        while (rp < s.length()) {
            char rpChar = s.charAt(rp);

            if (seen.containsKey(rpChar)) {
                // Math.max() here to prevent lp from going backwards, since we don't 
                // update positioning/indexes of characters we jumped over when duplicates
                // happen.
                lp = Math.max(seen.get(rpChar) + 1, lp);
            }

            seen.put(rpChar, rp);
            longestSubstring = Math.max(longestSubstring, rp - lp + 1);
            
            rp++;
        }

        return longestSubstring;
    }
}
/*
================================
- Time: O(n) 
    > We loop through the entire string, checking/visiting each character once.
- Space: O(n)
    > We use a hashset to store all unique characters, so in the worst case, a unique 
      string will force us to store all of its characters in the set, so O(n).
================================

Key takeaways:
    - Could've more elegantly done it with a for loop than a while loop to traverse the 
      string with rp. Also, the nested if-while block could've been simplified to just a
      while block, that keeps shifting lp when rp's character is still in the seen set
      (we shift lp and remove its character from the set)
    - There's a more optimal solution where you use a HashMap instead of a HashSet, where
      you store the indices for each character occurrence, and when a duplicate occurs,
      you jump the lp all the way to past that previous characters occurrence. Interestingly,
      you don't actually have to update/remove the entires of all the characters lp jumped
      past, since the substring is determined by the lp and rp positioning, not the 
      entries in the map, but you will need to update the index to the most recent one for
      each character as you move along.
*/
