package mylearnings.com.example;

import java.util.HashMap;

class SnapshotArray {

    HashMap<Integer, Integer> map[];
    int snapId = 0;

    public SnapshotArray(int length) {
        map = new HashMap[length];
        for (int i = 0; i < length; i++) {
            map[i] = new HashMap<>();
        }
    }

    public void set(int index, int val) {
        map[index].put(snapId, val);
    }

    public int snap() {
        snapId++;
        return snapId - 1;
    }

    public int get(int index, int snap_id) {
        while (snap_id >= 0 && !map[index].containsKey(snap_id)) {
            snap_id--;
            if (snap_id == -1) {
                return 0;
            }
        }

        return map[index].get(snap_id);

    }
}
