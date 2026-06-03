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
