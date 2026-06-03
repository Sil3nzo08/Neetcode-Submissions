class TimeMap {
    Map<String, ArrayList<TimeStamp>> timeMap;

    public TimeMap() {
        timeMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        ArrayList<TimeStamp> arr = timeMap.getOrDefault(key, new ArrayList<>());
        arr.add(new TimeStamp(timestamp, value));

        timeMap.put(key, arr);
    }
    
    public String get(String key, int timestamp) {
        ArrayList<TimeStamp> arr = timeMap.get(key);

        if (arr == null) { return ""; }

        int lp = 0;
        int rp = arr.size() - 1;
        TimeStamp prevTimeStamp = null;

        while (lp <= rp) {
            int mp = lp + ((rp - lp) / 2);
            TimeStamp mpTimeStamp = arr.get(mp);

            if (timestamp > mpTimeStamp.timestamp) {
                // target timestamp is bigger than mp's one, so move left
                // pointer up, but this a valid timestamp, so record
                prevTimeStamp = mpTimeStamp;
                lp = mp + 1;
            } else if (timestamp < mpTimeStamp.timestamp) {
                // target timestamp is smaller than mp's one, so move right
                // pointer down. Not valid timestamp, so no record
                rp = mp - 1;
            } else {
                // found exact timestamp
                prevTimeStamp = mpTimeStamp;
                break;
            }
        }

        if (prevTimeStamp != null) {
            return prevTimeStamp.val;
        } else {
            return "";
        }
        
    }
}

class TimeStamp {
    public int timestamp;
    public String val;

    public TimeStamp(int timestamp, String val) {
        this.timestamp = timestamp;
        this.val = val;
    }

    @Override
    public String toString() {
        return "[" + timestamp + ", " + val + "]";
    }
}
/*
================================
- Time: set() => O(1),  get() => O(log(n)) 
    > In set(), we use a hashmap to insert the key, value, and timestamp, so getting and putting into the hashmap 
      is O(1). Also, appending/adding to the end of the arraylist is O(1) amortized
    > In get(), we get the right array, which is O(1) thanks to hash map, and then perform binary search on it. Worst
      case, the array has all values inserted, n, which is why time complexity for it increases to O(log(n))
- Space: O(m*n)
    > We have a hashmap to store all the keys, and each key contains an array of n size to store all the values, hence
      space being O(m*n).
================================

Key takeaways:
    - A key constraint given here is that 'All the timestamps of set are strictly increasing', which means that a new
      set() call will use a timestamp that is bigger than all the previous ones given, so by appending that to the end
      of the current arrays, we maintain an ascending order such as [3, 6, 8, 10, 22] for the timestamps, and thus, 
      allows us to perform binary search on it to find the exact timestamp match, or closest one that is less than it
      (boundary search effectively). This constraint, if you think about it in real life, time keeps marching on, so a
      new append will never have time in the past if that makes sense.
    - Probably should've created a private static class inside of the 'TimeStamp' class to hold the timestamps instead 
      of a separate class outside. The class should probably also included getters instead of directly accessing the
      object's fields, but that's more of a design choice...
    - This problem requires floor search, not exact search. We can do that with binary search by always updating our 
      answer whenever we find a valid element (that is <= timestamp), and don't update it when we find an invalid
      element (> timestamp). 
*/
