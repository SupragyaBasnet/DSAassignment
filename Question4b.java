// b)
// You are provided with balanced binary tree with the target value k. return x number of values that are closest to the given target k. provide solution in O(n)
// Note: You have only one set of unique values x in binary search tree that are closest to the target.
// Input: 
 
// K=3.8
// x=2
// Output: 3,4



import java.util.LinkedList;
import java.util.List;

// Class representing a binary search tree
class Tree {
    // Inner class representing a node in the binary search tree
    public static class Node {
        int data; // Data stored in the node
        Node left, right; // References to the left and right child nodes

        // Constructor to create a new node with the given data
        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    // Method to create a binary search tree (BST)
    Node createBST(Node root, int data) {
        // If the root is null, create a new node with the given data
        if (root == null)
            return new Node(data);
        // If the data is less than the root's data, recursively create the left subtree
        if (data < root.data) {
            root.left = createBST(root.left, data);
        } else if (data > root.data) { // If the data is greater than the root's data, recursively create the right subtree
            root.right = createBST(root.right, data);
        } else {
            System.out.println("Duplicate entry of " + data); // If the data already exists in the tree, print a message
        }
        return root; // Return the root node
    }

    // Inorder traversal to find the closest values to the target
    private void findClosestValues(Node root, double target, int k, LinkedList<Integer> closest) {
        if (root == null)
            return;

        // Traverse the left subtree
        findClosestValues(root.left, target, k, closest);

        // If we have more than k elements, check if we should remove the farthest
        if (closest.size() == k) {
            if (Math.abs(target - closest.peekFirst()) > Math.abs(target - root.data)) {
                closest.removeFirst();
            } else {
                // If the current element is not closer than the farthest in the list, stop the process
                return;
            }
        }
        closest.add(root.data); // Add the current node's data to the list of closest values

        // Traverse the right subtree
        findClosestValues(root.right, target, k, closest);
    }

    // Public method to initiate the search for the closest values to the target
    public List<Integer> findClosest(Node root, double target, int k) {
        LinkedList<Integer> closest = new LinkedList<>(); // List to store the closest values
        findClosestValues(root, target, k, closest); // Call the recursive method to find the closest values
        return closest; // Return the list of closest values
    }

    public static void main(String[] args) {
        // Example usage:
        Tree tree = new Tree(); // Create an instance of the Tree class
        Node root = null; // Initialize the root node of the tree

        int[] values = { 4, 2, 5, 1, 3 }; // Array of values to insert into the tree

        // Insert each value into the binary search tree
        for (int value : values) {
            root = tree.createBST(root, value);
        }

        double target = 3.8; // Target value for which closest values are to be found
        int k = 2; // Number of closest values to find

        // Find the k closest values to the target in the binary search tree
        List<Integer> closestValues = tree.findClosest(root, target, k);

        System.out.println(closestValues); // Print the closest values found
    }
}
