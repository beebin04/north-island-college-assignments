/**
 * @file                BST.java
 * @author              Brenden O'Brien
 * @version             1.0
 * @date                2024-03-24
 * Course               CPS 101
 * 
 * Problem Statement:   Implement an AVLTree and compare
 *                      it to a regular BST
 */
public class BST<T extends Comparable<T>> {
   private Node<T> root;
   /**
    * Constructor:          BST
    */
   public BST() {
      root = null;
   }
   /**
    * Method:           getRoot
    * @return   tree's root node
    */
   public Node<T> getRoot() {
      return root;
   }
   /**
    * Method:           search
    * @param desiredKey
    * @return
    */
   public Node<T> search(T desiredKey) {
      Node<T> currentBSTNode = root;
      while (currentBSTNode != null) {
         // Return the BSTNode if the key matches
         if (currentBSTNode.key.compareTo(desiredKey) == 0) {
            return currentBSTNode;
         }
         
         // Navigate to the left if the search key is 
         // less than the BSTNode's key.
         else if (desiredKey.compareTo(currentBSTNode.key) == 0) {
            currentBSTNode = currentBSTNode.left;
         }
         
         // Navigate to the right if the search key is 
         // greater than the BSTNode's key.
         else {
            currentBSTNode = currentBSTNode.right;
         }
      }
      
      // The key was not found in the tree.
      return null;
   }
   /**
    * Method:           insert
    * @param BSTNode
    */
   public void insert(Node<T> BSTNode) {
      // Check if tree is empty
      if (root == null) {
         root = BSTNode;
      }
      else {
         Node<T> currentBSTNode = root;
         while (currentBSTNode != null) {
            if (BSTNode.key.compareTo(currentBSTNode.key) < 0) {
               // If no left child exists, add the new BSTNode
               // here; otherwise repeat from the left child.
               if (currentBSTNode.left == null) {
                  currentBSTNode.left = BSTNode;
                  currentBSTNode = null;
               }
               else {
                  currentBSTNode = currentBSTNode.left;
               }
            }
            else {
               // If no right child exists, add the new BSTNode
               // here; otherwise repeat from the right child.
               if (currentBSTNode.right == null) {
                  currentBSTNode.right = BSTNode;
                  currentBSTNode = null;
               }
               else {
                  currentBSTNode = currentBSTNode.right;
               }
            }
         }
      }
   }
   /**
    * Method:           remove
    * @param key
    * @return
    */
   public boolean remove(T key) {
      Node<T> parent = null;
      Node<T> currentBSTNode = root;
      
      // Search for the BSTNode.
      while (currentBSTNode != null) {
         // Check if currentBSTNode has a matching key.
         if (currentBSTNode.key.compareTo(key) == 0) {
            if (currentBSTNode.left == null && currentBSTNode.right == null) {
               if (parent == null) { // BSTNode is root
                  root = null;
               }
               else if (parent.left == currentBSTNode) { 
                  parent.left = null;
               }
               else {
                  parent.right = null;
               }
               return true; // BSTNode found and removed
            }
            else if (currentBSTNode.left != null && currentBSTNode.right == null) {
               if (parent == null) { // BSTNode is root
                  root = currentBSTNode.left;
               }
               else if (parent.left == currentBSTNode) {
                  parent.left = currentBSTNode.left;
               }
               else {
                  parent.right = currentBSTNode.left;
               }
               return true; // BSTNode found and removed
            }
            else if (currentBSTNode.left == null && currentBSTNode.right != null) {
               if (parent == null) { // BSTNode is root
                  root = currentBSTNode.right;
               }
               else if (parent.left == currentBSTNode) {
                  parent.left = currentBSTNode.right;
               }
               else {
                  parent.right = currentBSTNode.right;
               }
               return true; // BSTNode found and removed
            }
            else {
               // Find successor (leftmost child of right subtree)
               Node<T> successor = currentBSTNode.right;
               while (successor.left != null) {
                  successor = successor.left;
               }
               currentBSTNode.key = successor.key; // Copy successor to current BSTNode
               parent = currentBSTNode;
               currentBSTNode = currentBSTNode.right; // Remove successor from right subtree
               key = successor.key;             // Loop continues with new key
            }
         }
         else if (currentBSTNode.key.compareTo(key) < 0) { // Search right
            parent = currentBSTNode;
            currentBSTNode = currentBSTNode.right;
         }
         else { // Search left
            parent = currentBSTNode;
            currentBSTNode = currentBSTNode.left;
         }
      }
      return false; // BSTNode not found
   }
   /**
    * Method:           getHeight
    *
    * @param node
    * @return
    */
   public int getHeight(Node<T> node) {
    if (node == null) 
        return -1;
    
    int leftHeight = getHeight(node.left);
    int rightHeight = getHeight(node.right);
    return Math.max(leftHeight, rightHeight) + 1;
   }
}
/**
 * Class:           Node
 */
class Node<T> {
    public T key;
    public Node<T> left;
    public Node<T> right;
    /**
     * Constructor:         Node
     * @param NodeKey
     */
    public Node(T NodeKey) {
       key = NodeKey;
       left = null;
       right = null;
    }


}