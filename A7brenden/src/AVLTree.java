/**
 * @file                AVLTree.java
 * @author              Brenden O'Brien
 * @version             1.0
 * @date                2024-03-24
 * Course               CPS 101
 * 
 * Problem Statement:   Implement an AVLTree and compare
 *                      it to a regular BST
 */

/**
 * Class:           AVLTree
 */
class AVLTree<T extends Comparable<T>> {
    private Node<T> root;
    /**
     * Constructor:         AVLTree
     * 
     * initializes a new AVLTree by setting the root node to null.
     * any new insertions replace this null node.
     */
    public AVLTree() {
       root = null;
    }
    /**
     * Method:          getRoot
     * 
     * returns the tree's root node
     */
    public Node<T> getRoot() {
       return root;
    }
    
    // Performs a left rotation at the given node. Returns the
    // subtree's new root.
    public Node<T> rotateLeft(Node<T> node) {
       // Define a convenience pointer to the right child of the 
       // left child.
       Node<T> rightLeftChild = node.right.left;
         
       // Step 1 - the right child moves up to the node's position.
       // node is temporarily detached from the tree, but will be reattached
       // later.
       if (node.parent != null) {
          node.parent.replaceChild(node, node.right);
       }
       else { // node is root
          root = node.right;
          root.parent = null;
       }
 
       // Step 2 - the node becomes the left child of what used
       // to be node's right child, but is now node's parent. This will
       // detach rightLeftChild from the tree.
       node.right.setChild(Node.Child.LEFT, node);
         
       // Step 3 - reattach rightLeftChild as the right child of node.
       node.setChild(Node.Child.RIGHT, rightLeftChild);
         
       return node.parent;
    }
    
    // Performs a right rotation at the given node. Returns the
    // subtree's new root.
    public Node<T> rotateRight(Node<T> node) {
       // Define a convenience pointer to the left child of the 
       // right child.
       Node<T> leftRightChild = node.left.right;
         
       // Step 1 - the left child moves up to the node's position.
       // node is temporarily detached from the tree, but will be reattached
       // later.
       if (node.parent != null) {
          node.parent.replaceChild(node, node.left);
       }
       else { // node is root
          root = node.left;
          root.parent = null;
       }
 
       // Step 2 - the node becomes the right child of what used
       // to be node's left child, but is now node's parent. This will
       // detach leftRightChild from the tree.
       node.left.setChild(Node.Child.RIGHT, node);
         
       // Step 3 - reattach leftRightChild as the right child of node.
       node.setChild(Node.Child.LEFT, leftRightChild);
         
       return node.parent;
    }
    
    // Updates the given node's height and rebalances the subtree if
    // the balancing factor is now -2 or +2. Rebalancing is done by
    // performing a rotation. Returns the subtree's new root if
    // a rotation occurred, or the node if no rebalancing was required.
    public Node<T> rebalance(Node<T> node) {
       // First update the height of this node.
       node.updateHeight();      
 
       // Check for an imbalance.
       if (node.getBalance() == -2) {
          // The subtree is too big to the right.
          if (node.right.getBalance() == 1) {
             // Double rotation case. First do a right rotation
             // on the right child.
             rotateRight(node.right);
          }
 
          // A left rotation will now make the subtree balanced.
          return rotateLeft(node);
       }
       else if (node.getBalance() == 2) {
          // The subtree is too big to the left
          if (node.left.getBalance() == -1) {
             // Double rotation case. First do a left rotation
             // on the left child.
             rotateLeft(node.left);
          }
 
          // A right rotation will now make the subtree balanced.
          return rotateRight(node);
       }
             
       // No imbalance, so just return the original node.
       return node;
    }
    
    // Searches for a node with a matching key. Does a regular
    // binary search tree search operation. Returns the node with the
    // matching key, or null if no matching key exists in the tree.
    public Node<T> search(T desiredKey) {
       Node<T> currentNode = root;
       while (currentNode != null) {
          // Return the node if the key matches
          if (currentNode.key.compareTo(desiredKey) == 0 ) {
             return currentNode;
          }
          
          // Navigate to the left if the search key is 
          // less than the node's key.
          else if (desiredKey.compareTo(currentNode.key) < 0) {
             currentNode = currentNode.left;
          }
          
          // Navigate to the right if the search key is 
          // greater than the node's key.
          else {
             currentNode = currentNode.right;
          }
       }
       
       // The key was not found in the tree.
       return null;
    }
    
    public void insert(Node<T> node) {
       // Check if tree is empty
       if (root == null) {
          root = node;
       }
       else {
          // Step 1 - do a regular binary search tree insert.
          Node<T> currentNode = root;
          while (currentNode != null) {
             // Choose to go left or right
             if (node.key.compareTo(currentNode.key) < 0) {
                // Go left. If left child is null, insert the new
                // node here.
                if (currentNode.left == null) {
                   currentNode.left = node;
                   node.parent = currentNode;
                   currentNode = null;
                }
                else {
                   // Go left and do the loop again.
                   currentNode = currentNode.left;
                }
             }
             else {
                // Go right. If the right child is null, insert the
                // new node here.
                if (currentNode.right == null) {
                   currentNode.right = node;
                   node.parent = currentNode;
                   currentNode = null;
                }
                else {
                   // Go right and do the loop again.
                   currentNode = currentNode.right;
                }
             }
          }
 
          // Step 2 - Rebalance along a path from the new node's parent up
          // to the root.
          node = node.parent;
          while (node != null) {
             rebalance(node);
             node = node.parent;
          }
       }
    }
    
    // Attempts to remove a node with a matching key. If no node has a matching key
    // then nothing is done and false is returned; otherwise the node is removed and
    // true is returned.
    public boolean removeKey(T key) {
       Node<T> node = search(key);
       if (node == null) {
          return false;
       }
       else {
          return removeNode(node);
       }
    }
    
    private boolean removeNode(Node<T> nodeToRemove) {
       // Base case: 
       if (nodeToRemove == null) {
          return false;
       }
         
       // Parent needed for rebalancing.
       Node<T> parent = nodeToRemove.parent;
         
       // Case 1: Internal node with 2 children
       if (nodeToRemove.left != null && nodeToRemove.right != null) {
          // Find successor
          Node<T> successorNode = nodeToRemove.right;
          while (successorNode.left != null) {
             successorNode = successorNode.left;
          }
             
          // Copy the value from the node
          nodeToRemove.key = successorNode.key;
             
          // Recursively remove successor
          removeNode(successorNode);
             
          // Nothing left to do since the recursive call will have rebalanced
          return true;
       }
     
       // Case 2: Root node (with 1 or 0 children)
       else if (nodeToRemove == root) {
          if (nodeToRemove.left != null) {
             root = nodeToRemove.left;
          }
          else {
             root = nodeToRemove.right;
          }
 
          if (root != null) {
             root.parent = null;
          }
 
          return true;
       }
       
       // Case 3: Internal with left child only
       else if (nodeToRemove.left != null) {
          parent.replaceChild(nodeToRemove, nodeToRemove.left);
       }
         
       // Case 4: Internal with right child only OR leaf
       else {
          parent.replaceChild(nodeToRemove, nodeToRemove.right);
       }
         
       // nodeToRemove is gone. Anything that was below nodeToRemove that has persisted 
       // is already correctly balanced, but ancestors of nodeToRemove may need rebalancing.
       nodeToRemove = parent;
       while (nodeToRemove != null) {
          rebalance(nodeToRemove);         
          nodeToRemove = nodeToRemove.parent;
       }
     
       return true;
    }
 }
 /**
  * Class:          Node
  *
  * outlines Node fields and methods for implemtation in AVLTrees
  */
 class Node<T> {
    public enum Child {
       LEFT, RIGHT
    }
 
    public T key;
    public Node<T> parent;
    public Node<T> left;
    public Node<T> right;
    public int height;
 
    // Constructor with a key parameter creates the Node object.
    public Node(T nodeKey) {
       key = nodeKey;
       parent = null;
       left = null;
       right = null;
       height = 0;
    }
    
    // Calculate this nodes' balance factor, defined as 
    // height(left subtree) - height(right subtree)
    public int getBalance() {
       // Get current height of left subtree, or -1 if null
       int leftHeight = -1;
       if (left != null) {
          leftHeight = left.height;
       }
             
       // Get current height of right subtree, or -1 if null
       int rightHeight = -1;
       if (right != null) {
          rightHeight = right.height;
       }
             
       // Calculate the balance factor.
       return leftHeight - rightHeight;
    }
    
    // Recalculate the current height of the subtree rooted at
    // the node, usually called after a subtree has been 
    // modified.
    public void updateHeight() {
       // Get current height of left subtree, or -1 if null
       int leftHeight = -1;
       if (left != null) {
          leftHeight = left.height;
       }
             
       // Get current height of right subtree, or -1 if null
       int rightHeight = -1;
       if (right != null) {
          rightHeight = right.height;
       }
 
       // Assign height with calculated node height.
       height = Math.max(leftHeight, rightHeight) + 1;
    }
    
    // Assign either the left or right data member with a new
    // child. Returns true if the new child is successfully 
    // assigned to this node, false otherwise.
    public boolean setChild(Child whichChild, Node<T> child) {
       // Assign the left or right data member.
       if (whichChild == Child.LEFT) {
          left = child;
       }
       else {
          right = child;
       }
 
       // Assign the parent data member of the new child,
       // if the child is not null.
       if (child != null) {
          child.parent = this;
       }
 
       // Update the node's height, since the subtree's structure
       // may have changed.
       updateHeight();
       return true;
    }
    
    // Replace a current child with a new child. Determines if
    // the current child is on the left or right, and calls
    // setChild() with the new node appropriately.
    // Returns true if the new child is assigned, false otherwise.
    public boolean replaceChild(Node<T> currentChild, Node<T> newChild) {
       if (left == currentChild) {
          return setChild(Child.LEFT, newChild);
       }
       else if (right == currentChild) {
          return setChild(Child.RIGHT, newChild);
       }
       
       // If neither of the above cases applied, then the new child
       // could not be attached to this node.
       return false;
    }
}
