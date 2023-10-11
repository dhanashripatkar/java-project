package mylearnings.com.example;

public class Tree {

    static int height = -1;

    // Structure of a Binary Tree Node
    static class Node {
        int data;
        Node left;
        Node right;
    };

    // Utility function to create
    // a new Binary Tree Node
    static Node newNode(int item) {
        Node temp = new Node();
        temp.data = item;
        temp.left = temp.right = null;
        return temp;
    }

    // Function to find the depth of
    // a given node in a Binary Tree
    static int findDepth(Node root, int x) {

        // Base case
        if (root == null)
            return -1;

        // Initialize distance as -1
        int dist = -1;

        // Check if x is current node=
        if ((root.data == x) ||

        // Otherwise, check if x is
        // present in the left subtree
                (dist = findDepth(root.left, x)) >= 0 ||

                // Otherwise, check if x is
                // present in the right subtree
                (dist = findDepth(root.right, x)) >= 0)

            // Return depth of the node
            return dist + 1;

        return dist;
    }

    // Helper function to find the height
    // of a given node in the binary tree
    static int findHeightUtil(Node root, int x) {

        // Base Case
        if (root == null) {
            return -1;
        }

        // Store the maximum height of
        // the left and right subtree
        int leftHeight = findHeightUtil(root.left, x);

        int rightHeight = findHeightUtil(root.right, x);

        // Update height of the current node
        int ans = Math.max(leftHeight, rightHeight) + 1;

        // If current node is the required node
        if (root.data == x)
            height = ans;

        return ans;
    }

    // Function to find the height of
    // a given node in a Binary Tree
    static int findHeight(Node root, int x) {

        // Stores height of the Tree
        findHeightUtil(root, x);

        // Return the height
        return height;
    }

    public boolean isValidBST(Node root) {
        if (root == null) {
            return true;
        }
        return isBST(root, null, null);
    }

    public boolean isBST(Node root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((null != min && root.data <= min) || (null != max && root.data >= max)) {
            return false;
        }
        return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
    }

    public boolean isSameTree(Node p, Node q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q == null || q != null && p == null) {
            return false;
        } else if (p.data != q.data) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // max depth of tree
    int depth = 0;

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int leftDep = maxDepth(root.left);
        int rightDep = maxDepth(root.right);
        depth = Math.max(leftDep, rightDep) + 1;

        return depth;
    }

    // Driver Code
    public static void main(String[] args) {

        // Binary Tree Formation
        Node root = newNode(5);
        root.left = newNode(10);
        root.right = newNode(15);
        root.left.left = newNode(20);
        root.left.right = newNode(25);
        root.left.right.right = newNode(45);
        root.right.left = newNode(30);
        root.right.right = newNode(35);

        int k = 25;

        // Function call to find the
        // depth of a given node
        System.out.println("Depth: " + findDepth(root, k));

        // Function call to find the
        // height of a given node
        System.out.println("Height: " + findHeight(root, k));
    }
}
