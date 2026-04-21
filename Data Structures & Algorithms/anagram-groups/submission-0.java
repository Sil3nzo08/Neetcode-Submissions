class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();

        boolean added = false;
        for (String s : strs) {
            added = false;
            for (String key : anagrams.keySet()) {
                if (isAnagram(key, s)) {
                    anagrams.get(key).add(s);
                    added = true;
                    break;
                }
            }

            if (!added) {
                List<String> arr = new ArrayList<>();
                arr.add(s);
                anagrams.put(s, arr);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> anagram : anagrams.values()) {
            ans.add(anagram);
        }

        return ans;
    }


    private boolean isAnagram(String s, String t) {
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
