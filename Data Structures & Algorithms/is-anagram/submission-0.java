class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) { return false; }
        
        int[] occurrences = new int[26];
        for (char c : s.toCharArray()) {
            occurrences[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            if (occurrences[c - 'a'] == 0) {
                return false;
            }
            
            occurrences[c - 'a']--;
        }

        return true;
    }
}
