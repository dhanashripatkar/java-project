package mylearnings.com.example;

class Linklist {

    public Node head;
    public Node tail;

    public Node head2;
    public Node tail2;

    class Node {
        int data;
        Node next;

        public Node() {

        }

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

    public void push2(int data) {
        final Node new_node = new Node(data);
        // last append
        if (this.head2 == null) {
            this.head2 = new_node;
            tail2 = new_node;
            return;
        }
        tail2.next = new_node;
        tail2 = new_node;

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

    public static void print(Node head) {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public void print2() {
        Node current = this.head2;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public Node mergeTwoLists() {
        Node list1 = head;
        Node list2 = head;
        final Node root = new Node();
        Node prev = root;
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 != null ? list1 : list2;// append the remaining portion
        return root.next;
    }

    public boolean hasCycle(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        System.gc();
        return false;
    }

    public void reorderList(Node head) {
        // time -> O(n);
        // find the middle of linked list
        // middle will be leftmost element
        Node fast = head.next; // since middle should be leftmost
        Node slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second half of list
        Node second = slow.next;
        Node pre = slow.next = null;
        while (second != null) {
            Node temp = second.next;
            second.next = pre;
            pre = second;
            second = temp;
        }
        // first = 1 2 3
        // seoncd = 5 4
        // merge the list

        second = pre;
        Node first = head;
        while (second != null) {
            Node temp1 = first.next; // 2
            Node temp2 = second.next; // 4
            first.next = second; // 1->5
            second.next = temp1; // 1 -> 5->2
            first = temp1; // first = 2
            second = temp2; // second = 4
        }
    }

    public static void main(String args[]) {
        final Linklist list = new Linklist();
        for (int i = 0; i < 3; i++) {
            list.push(i);
        }
        final Linklist list2 = new Linklist();
        for (int i = 2; i < 6; i++) {
            list2.push2(i);
        }
        // list2.print2();
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

        // list.getNthNodeFromLast(6);
        Node ans = list.mergeTwoLists();
        print(ans);

    }

}
