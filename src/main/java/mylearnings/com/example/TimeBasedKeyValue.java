package mylearnings.com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class TimeBasedKeyValue {

    /**
     * HashMap<String, List<Pair<String, Integer>>> map;
     * 
     * public TimeBasedKeyValue() {
     * this.map = new HashMap<>();
     * }
     * 
     * public void set(String key, String value, int timestamp) {
     * if (!map.containsKey(key)) {
     * map.put(key, new ArrayList<>());
     * }
     * map.get(key).add(new Pair<>(value, timestamp));
     * }
     * 
     * public String get(String key, int timestamp) {
     * if (!map.containsKey(key)) {
     * return "";
     * }
     * List<Pair<String, Integer>> list = map.get(key);
     * return search(list, timestamp);
     * }
     * 
     * public String search(List<Pair<String, Integer>> list, int timestamp) {
     * int start = 0;
     * int end = list.size() - 1;
     * while (start < end) {
     * int mid = start + (end - start + 1) / 2; // imp, added +1
     * if (list.get(mid).getValue() <= timestamp) { // here also <= since need to
     * return a value such that set
     * // was
     * // called previously with timestamp_prev <= timestamp
     * start = mid;
     * } else {
     * end = mid - 1;
     * }
     * }
     * return list.get(start).getValue() <= timestamp ? list.get(start).getKey() :
     * "";
     * }
     */

}
