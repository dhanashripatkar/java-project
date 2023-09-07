package mylearnings.com.example;

class Linklist {

    public Node head;
    public Node tail;

    class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
            this.next = null;
        }
    }

    public void push(int data) {
        final Node new_node = new Node(data);
        // last append
        if (head == null) {
            head = new_node;
            tail = new_node;
            return;
        }
        tail.next = new_node;
        tail = new_node;

        // first append
        /**
         * new_node.next = head;
         * head = new_node;
         */
    }

    public void getMiddle() {
        Node first = head;
        Node second = head;

        while (first != null && first.next != null) {
            first = first.next.next;
            second = second.next;
        }
        System.out.println("middle " + second.data);
    }

    public void isElementPresent(int element) {
        Node current = head;
        while (current != null) {
            if (current.data == element) {
                System.out.println("Found element: " + element);
                return;
            }

            current = current.next;
        }
        System.out.println("Not found element :" + element);
    }

    public void reverse() {
        Node current = head;
        Node pre = null;
        Node next = null;

        while (current != null) {
            next = current.next;// store the next value
            current.next = pre; // update the next of current to pre
            pre = current; // update pre to current
            current = next; // update current to next
        }
        this.head = pre; // this is the updated node of reverse list
    }

    public Node recurssiveReverse(Node head, boolean isFirstCall) {
        if (isFirstCall) {
            head = this.head;
        }
        if (head == null || head.next == null) {
            return head;
        }
        Node rest = recurssiveReverse(head.next, false);
        head.next.next = head;
        head.next = null;

        this.head = rest;
        return rest;
    }

    public void deleteNodeAtPos(int pos) {
        Node current = head;
        Node pre = head;
        for (int i = 0; i < pos; i++) {
            if (i == 0 && pos == 1) {
                // first node delete condition
                head = head.next;
                break;
            } else {
                // we got the pos
                if (i == pos - 1) {
                    pre.next = current.next;
                    break;
                } else {
                    pre = current;
                    if (pre == null) {
                        // when postion is greater than size of list
                        break;
                    }
                    current = current.next;
                }
            }
        }
    }

    public void getNthNode(int index) {
        int count = 0;
        Node current = head;
        Integer result = null;
        while (current != null) {
            if (count == index) {
                result = current.data;
                break;
            }
            count++;
            current = current.next;
        }
        if (result == null) {
            System.out.println("Index is greater than size of list");
        } else {
            System.out.println("Nth node is : " + result);
        }
    }

    public int getNthNodeRecurssive(Node head, int index, boolean isFirst) {
        if (isFirst) {
            head = this.head;
        }
        int count = 0;
        if (head == null) {
            return -1;
        }
        if (count == index) {
            return head.data;
        }
        return getNthNodeRecurssive(head.next, index - 1, false);
    }

    public void getNthNodeFromLast(int pos) {
        // pos is wrt 1
        Node main = head;
        Node ref = head;
        int count = 0;
        if (head != null) {
            while (count < pos) {
                if (ref == null) {
                    System.out.println("Pos is out of list size");
                    return;
                }
                count++;
                ref = ref.next;
            }

            if (ref == null) {
                if (main != null) {
                    System.out.println("Nth node from last is : " + main.data);
                }
            } else {

                while (ref != null) {
                    ref = ref.next;
                    main = main.next;
                }
                System.out.println("Nth node from last is : " + main.data);
            }
        }
    }

    public void print() {
        Node current = this.head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public static void main(String args[]) {
        final Linklist list = new Linklist();
        for (int i = 0; i < 6; i++) {
            list.push(i);
        }
        list.print();
        // list.getMiddle();
        // list.isElementPresent(10);
        // list.reverse();
        // list.recurssiveReverse(null, true);
        // list.deleteNodeAtPos(9);
        // System.out.println("--------------------------");
        // list.print();

        // list.getNthNode(5);
        // int result = list.getNthNodeRecurssive(null, 5, true);
        // System.out.println("Nth node is : " + result);

        list.getNthNodeFromLast(6);
    }

}
