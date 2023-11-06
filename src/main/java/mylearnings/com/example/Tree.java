package mylearnings.com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.TreeNode;

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
    /**
     * The depth of a node is the number of edges present in path from the root node
     * of a tree to that node.
     * The height of a node is the number of edges present in the longest path
     * connecting that node to a leaf node.
     * 
     * @param root
     * @param x
     * @return
     */
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

    public boolean isSubtree(Node root, Node subRoot) {
        // this condition is very imp.
        // check subrrot first. dont miss the sequence
        if (subRoot == null || isSameTree(root, subRoot)) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public Node invertTree(Node root) {

        if (root == null) {
            return null;
        }
        Node node = newNode(root.data);
        node.right = invertTree(root.left);
        node.data = root.data;
        node.left = invertTree(root.right);
        return node;
    }

    // Time - O(n)
    public int kthSmallestByRecurssive(Node root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder1(root, list);
        return list.get(k - 1);
    }

    public void inorder1(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder1(root.left, list);
        list.add(root.data);
        inorder1(root.right, list);
    }

    // time O(n) and space O(h) // height of tree
    public int kthSmallestIterative(Node root, int k) {
        Stack<Node> stack = new Stack<>();
        int n = 0;
        Node current = root;
        while (null != current || !stack.isEmpty()) {
            while (null != current) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            n += 1;
            if (n == k) {
                return current.data;
            }
            current = current.right;
        }
        return -1;
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

    public int goodNodes(Node root) {
        return dfs(root, root.data);
    }

    public int dfs(Node root, int maxValue) {
        if (root == null) {
            return 0;
        }
        int res = root.data >= maxValue ? 1 : 0;
        maxValue = Math.max(maxValue, root.data);
        res += dfs(root.left, maxValue);
        res += dfs(root.right, maxValue);
        return res;

    }

    /**
     * Input: root = [1,2,3,null,5,null,4]
     * Output: [1,3,4]
     * 
     * @param root
     * @return
     */
    public List<Integer> rightSideView(Node root) {

        // time O(n)
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                Node temp = queue.poll();
                if (temp != null) {
                    if (i == 0) {
                        ans.add(temp.data);
                    }
                    if (temp.right != null) {
                        queue.offer(temp.right);
                    }
                    if (temp.left != null) {
                        queue.offer(temp.left);
                    }
                }
            }
        }
        return ans;

    }

    /**
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     * 
     * @param preorder
     * @param inorder
     * @return
     */
    public Node buildTree(int[] preorder, int[] inorder) {

        if (0 == preorder.length || 0 == inorder.length) {
            return null;
        }
        int mid = 0;
        Node root = newNode(preorder[0]);
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                mid = i;
            }
        }
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, mid + 1),
                Arrays.copyOfRange(inorder, 0, mid));
        // mid+1 not included (in preorder)

        root.right = buildTree(Arrays.copyOfRange(preorder, mid + 1, preorder.length),
                Arrays.copyOfRange(inorder, mid + 1, inorder.length));

        return root;

    }

    /**
     * Input: root = [3,9,20,null,null,15,7]
     * Output: true
     * 
     * @param root
     * @return
     */
    /**
     * public boolean isBalanced(TreeNode root) {
     * Pair<Boolean, Integer> ans = dfs(root);
     * return ans.getKey();
     * }
     * 
     * public Pair<Boolean, Integer> dfs(TreeNode node) {
     * if (node == null) {
     * return new Pair<>(true, 0);
     * }
     * 
     * Pair<Boolean, Integer> left = dfs(node.left);
     * Pair<Boolean, Integer> right = dfs(node.right);
     * 
     * boolean balance = left.getKey() && right.getKey() &&
     * (Math.abs(left.getValue() - right.getValue()) <= 1);
     * 
     * return new Pair<>(balance, 1 + Math.max(left.getValue(), right.getValue()));
     * 
     * }
     */

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

        Node r = newNode(4);
        r.left = newNode(2);
        r.right = newNode(7);
        r.left.left = newNode(1);
        r.left.right = newNode(3);
        r.right.left = newNode(6);
        r.right.right = newNode(9);

        Tree tree = new Tree();
        Node ans = tree.invertTree(r);

        System.out.println(ans);

        // Function call to find the
        // depth of a given node
        // System.out.println("Depth: " + findDepth(root, k));

        // Function call to find the
        // height of a given node
        // System.out.println("Height: " + findHeight(root, k));
    }
}
