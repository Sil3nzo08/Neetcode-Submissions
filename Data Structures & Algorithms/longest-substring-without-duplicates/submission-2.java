class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) { return 0; }
        if (s.length() == 1) { return 1; }
        
        int lp = 0;
        int rp = 1;

        Set<Character> seen = new HashSet<>();
        seen.add(s.charAt(lp));

        int longestSubstring = 0;
        while (rp < s.length()) {
            char rpChar = s.charAt(rp);

            if (seen.contains(rpChar)) {
                // Move lp until previous same character is out of window
                while (s.charAt(lp) != rpChar) {
                    seen.remove(s.charAt(lp));
                    lp++;
                }
                lp++; // To move past the same char, no need to remove it.
                
            }
            
            longestSubstring = Math.max(longestSubstring, rp - lp + 1);
            seen.add(s.charAt(rp));
            rp++;
        }

        return longestSubstring;
    }
}
