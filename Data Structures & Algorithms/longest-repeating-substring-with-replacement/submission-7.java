class Solution {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();     
        int longestRepeat = 0;   

        // Sliding window
        int greatestFrequency = 0;
        int numAtGreatestFrequency = 0;
        
        int lp = 0;
        for (int rp = 0; rp < s.length(); rp++) {
            // Record new frequency
            int currFreq = freq.getOrDefault(s.charAt(rp), 0) + 1;
            freq.put(s.charAt(rp), currFreq);
            greatestFrequency = Math.max(greatestFrequency, currFreq);

            //System.out.println("freq: " + greatestFrequency);

            int k_left = k - ((rp - lp + 1) - greatestFrequency);
            while (k_left < 0) {
                //System.out.println("CHANGING K: " + k_left);
                //System.out.println(freq);

                // shift lp up
                int lFreq = freq.get(s.charAt(lp));
                freq.put(s.charAt(lp), lFreq - 1);
                lp++;

                /*
                if (lFreq == greatestFrequency) {
                    greatestFrequency--;
                    for (int frequency : freq.values()) {
                        greatestFrequency = Math.max(greatestFrequency, frequency);
                    }
                } */

                // recompute k needed
                k_left = k - ((rp - lp + 1) - greatestFrequency);
                //System.out.println("new K: " + k_left);
            }
            //System.out.println("not in while loop: " + freq);

            longestRepeat = Math.max(longestRepeat, rp - lp + 1);
        } 

        //System.out.println(freq);

        return longestRepeat;
    }
}
/*
================================
- Time: O(n) 
    > We visit each character once in the input string s.
- Space: O(m), where m is the number of unique characters in the string
    > We have a hashmap that stores the frequency of every letter/char, so in the 
      worst case, we'll have 26 entries in there, with keys ranging from A-Z.
================================

Key takeaways:
    - Honestly, could not get this until I used up all my hints ;-;. The brute force
      was to have lp at every char, and for every lp position, we extend rp as far
      as we can until the window (which we're growing by shifting rp as far as possible)
      becomes invalid, which occurs when there are too many characters that need replacing
      (not enough k-value). That's O(n^2), but we can do it in O(n) with sliding window
      technique. To do so, we have lp at index = 0, then extend rp as far as possible
      until the window becomes invalid, and if invalid, we retrace/push lp forward
      until the window becomes valid again. The most optimal replacement technique for
      a given window is to stick to the character that is occurring the most in that
      window, and use our replacements for all other characters. So, to calculate
      the max occurring character, you just loop through the values of the hashmap for
      every valid window, and then update the res/'longestRepeat' if it's greater 
    - There's a weird optimisation where you don't actually have to loop through the 
      values of the hashmap, and just use a variable to store the greatestFrequency.
      This brings down the time complexity from O(26n) to O(n) (not looping through the
      26 entries in the hashmap), which is still the same thing asympotically, but more
      optimal... The reason this works with storing the 'greatestFrequency' (why this
      is an optimisation) is because we can actually remove the block comment in the
      code, since we're looking for the LONGEST answer. So when we decrease a tally
      in the hashmap, we don't need to update greatestFrequency to be 100% true, instead
      we keep it at the MAXIMUM FREQUENCY we've seen so far in the string, because the
      only way we get a longer answer is if the maximum frequency decreases. No matter
      how big the window size is, bigger windows become valid only if the max frequency
      seen is bigger. So what we end up here is that the algorithm may keep invalid
      windows, but it will never record an answer that is larger than what some
      previously observed frequency could support. 
      From CHATGPT:
      A greatestFrequency of 3 means the algorithm has genuinely observed 3 equal 
      characters somewhere, and therefore any window size larger than 3 + k cannot 
      be accepted until a real frequency of 4 is observed. That's what prevents 
      overestimating the answer.
    - But let's say the max window was just 3 of the same character. Well, greatest
      frequency would be 3 and with k = 3, then max window size is 6, but our answer
      is only 3 because we record the longestRepeat with rp and lp positions. The
      greatestFrequency is just used to check for validity (and it will keep invalid
      windows; but not ones that can override the answer). 
*/
