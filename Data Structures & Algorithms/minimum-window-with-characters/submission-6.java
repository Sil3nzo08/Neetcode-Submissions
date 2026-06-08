class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) { return ""; }

        Map<Character, Integer> mapT = new HashMap<>();
        Map<Character, Integer> mapS = new HashMap<>();

        // Populate map T
        int matchesWithT = 0; // matches should equal num of entries in mapT
        for (int i = 0; i < t.length(); i++) {
            mapT.put(t.charAt(i), mapT.getOrDefault(t.charAt(i), 0) + 1); 
        }

        // Sliding window on string s
        int ansLP = 0;
        int ansRP = 0;
        int minLength = Integer.MAX_VALUE;

        int lp = 0;
        for (int rp = 0; rp < s.length(); rp++) {
            // Add char at rp
            char rChar = s.charAt(rp);
            if (!mapT.containsKey(rChar)) { continue; }

            mapS.put(rChar, mapS.getOrDefault(rChar, 0) + 1);
            int currTallyR = mapS.get(rChar);
            if (currTallyR == mapT.get(rChar)) {
                // Tally in S now matches tally in T
                matchesWithT++;
            }
            
            // Shrink lp if valid
            while (matchesWithT == mapT.size()) {
                    char lChar = s.charAt(lp);
                    
                    if (!mapT.containsKey(lChar)) { lp++; continue; }

                    mapS.put(lChar, mapS.get(lChar) - 1);
                    int currTallyL = mapS.get(lChar);
                    if (currTallyL == mapT.get(lChar) - 1) {
                        // Tally in S no longer matches tally in T
                        matchesWithT--;

                        // Record minLength if needed
                        if (minLength > rp - lp + 1) {
                            minLength = rp - lp + 1;
                            ansRP = rp;
                            ansLP = lp;
                        }
                    }
                    
                    lp++;
            }
        }
         
        if (minLength == Integer.MAX_VALUE) { return ""; }
        return s.substring(ansLP, ansRP + 1);
    }
}
/* 
================================
- Time: O(n), where n is the size of string s 
    > We loop through the string s, visiting each character twice only through the technique of sliding window.
    > Note that we return prematurely if s is smaller than t. So, despite looping through t as well, the bigger
      'roadblock'/overhead is the string s, but doesn't really matter asymptotically as both give O(n) time
      complexity regardless.
- Space: O(m), where m is the number of unique characters in t
    > We use two hashmaps to store the frequency count of each character in t. The second hashmap is for these
      occurrences in string s (so characters in s but not in t are ignored and discarded). 
    > There's also some extra variables used for pointers and storing current results.
================================

Key takeaways:
    - THIS WAS MY FIRST HARD leetcode problem that I completed on my own!!!! No hints used, and no solution
      looked at. 
    - The hardest part about this problem was just understanding it. The general algorithm here is to grow 
      the window until the substring is valid (that is, all frequencies of the characters needed in the
      current window are >= the frequencies needed for their corresponding characters in t). Once valid,
      record result if smaller, and keep shrinking the window (move lp up) until the window is no longer valid
      since you're trying to find the minimum window! Additionally, you'll be checking the result everytime you
      shrink the window, since it could potentially be a new minimum.
    - To check if a window's valid, you could just loop through the entires of both hashmaps for the window
      and the string t, and ensure the window's frequency >= that of the corresponding character in t's
      hashmap. But that means you have to loop through the dictionary every single time, which is O(len(t)) 
      worst case if the string t was all unique characters, such as "abcdefghijkl". Since we are checking 
      and updating the hashmap one character at a time, we can instead have a new variable to store the 
      current number of entries that meet t's character requirements, and gets updated everytime we add/remove
      a character from our window. Now, we only need to check this variable with the size of t's hashmap to
      check for validity instead of looping through the entire hashmap!
*/
