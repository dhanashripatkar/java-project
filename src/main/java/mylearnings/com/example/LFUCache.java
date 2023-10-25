package mylearnings.com.example;

import java.util.HashMap;

class LFUCache {

    class Node {
        int val;
        int key;
        int count; // additional
        Node pre;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.count = 1; // imp
        }
    }

    class DLList {
        Node left;
        Node right;
        int size;

        DLList() {
            left = new Node(0, 0);
            right = new Node(0, 0);
            left.next = right;
            right.pre = left;
        }

        // insert at last or right
        void add(Node node) {
            Node pre = right.pre;
            pre.next = node;
            right.pre = node;
            node.pre = pre;
            node.next = right;
            size++;
        }

        // normal remove function
        void remove(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
            size--;
        }

        // when there is tie, remove the first one-> O(1)
        Node removeFirst() {
            if (size > 0) {
                Node lru = left.next;
                remove(lru);
                return lru;
            } else {
                return null;
            }
        }
    }

    int size;
    int min;
    int capacity;
    HashMap<Integer, Node> nodeMap;
    HashMap<Integer, DLList> countMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        this.countMap = new HashMap<>();
    }

    public int get(int key) {
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            if (node == null) {
                return -1;
            }
            update(node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.val = value;
            update(node);
        } else {
            Node node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) {
                DLList minList = countMap.get(min); // if size is full, get the list of min key from countmap
                nodeMap.remove(minList.removeFirst().key); // reove the node from that list
                size--;
            }
            min = 1;
            size++;
            DLList newList = countMap.getOrDefault(node.count, new DLList());
            newList.add(node);
            countMap.put(node.count, newList);
        }
    }

    public void update(Node node) {
        DLList oldList = countMap.get(node.count);
        oldList.remove(node); 
        // imp
        if (node.count == min && oldList.size == 0) {
            min++;
        }
        node.count++;
        DLList newList = countMap.getOrDefault(node.count, new DLList());
        newList.add(node);
        countMap.put(node.count, newList);
    }
}
