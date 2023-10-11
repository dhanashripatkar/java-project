package mylearnings.com.example;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class Node {
        int key;
        int value;
        Node pre;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            pre = null;
            next = null;
        }
    }

    int cap;
    Map<Integer, Node> map;
    Node left;
    Node right;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.cap = capacity;
        this.left = new Node(0, 0);
        this.right = new Node(0, 0);
        this.left.next = right;
        this.right.pre = left;
    }

    // insert at right;
    public void insert(Node node) {
        Node pre = right.pre;

        pre.next = node;
        right.pre = node;
        node.pre = pre;
        node.next = right;
    }

    // remove from list
    public void remove(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;

    }

    public int get(int key) {
        if (map.containsKey(key)) {
            remove(map.get(key));
            insert(map.get(key));
            return map.get(key).value;
        } else {
            return -1;
        }

    }

    // insert at right always then left will be always LRU
    // [(0,0) (0,0)] => Left and right
    // put[1,1] =>insert [1,1] => [(0,0), (1,1), (0,0)]
    // put[2,2] => insert[2,2] => [(0,0), (1,1), (2,2), (0,0)]
    // get[1,1] => remove(1,1) => insert(1,1) =>[(0,0), (2,2), (1,1),(0,0)]
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        Node node = new Node(key, value);
        insert(node);
        map.put(key, node);
        if (map.size() > cap) {
            Node lru = left.next;
            remove(lru);
            map.remove(lru.key);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
