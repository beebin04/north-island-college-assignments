/**
 * @file                BinarySearchTree.java
 * @author              Brenden O'Brien
 * @version             1.0
 * @date                2024-03-18
 * Course:              CPS 101
 * Problem Statement:   Enchance the BST ADT into a generic class
 *                      which also supports multiple traversal methods
 * INPUT:               User provides intergers and strings to input into BST trees
 *                      and Trie
 * OUTPUT:              With provided data, BST methods are tested with both integers and strings
 *                      displaying contents after a modification. The Trie class is tested similarily with strings
 *                 
 */
import java.util.LinkedList;

/**
 * Class: BinarySearchTree
 * 
 * 
 */
class BinarySearchTree<T extends Comparable<T>> {
   private BSTNode<T> root;

   /**
    * Constructor: BinarySearchTree
    * intializes root node to null
    */
   public BinarySearchTree() {
      root = null;
   }

   /**
    * Method: getRoot
    * 
    * @return BST root node
    */
   public BSTNode<T> getRoot() {
      return root;
   }

   /**
    * Method: search
    * compares data with provided key following the BST
    * @param desiredKey
    * @return node containing desired data; null if node is not found in BST
    */
   public BSTNode<T> search(T desiredKey) {
      BSTNode<T> currentNode = root;
      while (currentNode != null) {
         // Return the node if the key matches
         if (currentNode.key.compareTo(desiredKey) == 0)
            return currentNode;

         // Navigate to the left if the search key is
         // less than the node's key.
         else if (desiredKey.compareTo(currentNode.key) < 0)
            currentNode = currentNode.left;

         // Navigate to the right if the search key is
         // greater than the node's key.
         else
            currentNode = currentNode.right;

      }
      // The key was not found in the tree.
      return null;
   }
   /**
    * Method: rSearch
    *
    calls recursiveSearch method with BST's root and key as parameters
    * @param key
    * @return Node with desired data; null if not found
    */
   public BSTNode<T> rSearch(T key) {
      return recursiveSearch(root, key);

   }
   /**
    * Method: recursiveSearch
    * while the current node is not null; if current node data is 
    * greater than the key search down the node's left child; right if less than, 
    * return the node if data is equal to key
    * @param node
    * @param key
    * @return
    */
   private BSTNode<T> recursiveSearch(BSTNode<T> node, T key) {
      if (node != null) {

         if (key.compareTo(node.key) == 0)
            return node;
         else if (key.compareTo(node.key) < 0)
            return recursiveSearch(node.left, key);
         else
            return recursiveSearch(node.right, key);

      }
      return null;
   }
   /**
    * getParent
    * 
    * @param node
    * @return parent node of node provided in parameter
    */
   private BSTNode<T> getParentNode(BSTNode<T> node) {
      return getParentRecursive(root, node);
   }

   /**
    * Method: getParentRecursive
    * finds the parent node of a child by searching for the node
    * provided by comparing the tree node's children to the desired node; 
    * allowing for the reference to the parent to be maintained
    * @param root2
    * @param node
    * @return parent node of node
    */
   private BSTNode<T> getParentRecursive(BSTNode<T> root2, BSTNode<T> node) {
      if (root2 == null) {
         return null;
      }
      try {
         if (root2.right.key.compareTo(node.key) == 0)
         return root2;
         if (root2.left.key.compareTo(node.key) == 0)
         return root2;
      } catch (NullPointerException npe) {
         //compareTo() method fails if comparing a null object
         //fix is to override system shut down upon catch;
         //if local root has no children, and local root is not search node, return null
         if (root2.left == null && root2.right == null && root2 != node)
            return null;
      }  
      if (root2.key.compareTo(node.key) < 0)
         return getParentRecursive(root2.right, node);

      return getParentRecursive(root2.left, node);
   }
   /**
    * Method:        rInsert
    * Calls recursive insert method with BST root and a new node as parameters
    * @param node
    */
   public void rInsert(BSTNode<T> node) {
      if (root == null)
         root = node;
      else
         recursiveInsert(root, node);
   }
   /**
    * Method:        recursiveInsert
    * recursively follows down BST comparing node data to new node data. When a null child is encountered which the new node
    * would typically compared against the node is inserted as the new child
    * @param parentNode
    * @param insertNode
    */
   private void recursiveInsert(BSTNode<T> parentNode, BSTNode<T> insertNode) {
      if (insertNode.key.compareTo(parentNode.key) < 0) {
         if (parentNode.left == null)
            parentNode.left = insertNode;
         else
            recursiveInsert(parentNode.left, insertNode);
      } else {
         if (parentNode.right == null)
            parentNode.right = insertNode;
         else
            recursiveInsert(parentNode.right, insertNode);

      }
   }
   /**
    * Method:        rRemove
    * Calls recursiveRemove method after finding desired node to remove and it's parent
    * @param key
    * @return true if node is removed, false if not removed (never existed)
    */
   public boolean rRemove(T key) {

      BSTNode<T> remNode = rSearch(key);
      BSTNode<T> parentNode = getParentNode(remNode);
      return recursiveRemove(parentNode, remNode);
   }

   /**
    * Method: recursiveRemove
    * 
    * 
    * @param parentNode
    * @param remNode
    * @return
    */
   private boolean recursiveRemove(BSTNode<T> parentNode, BSTNode<T> remNode) {

      // node not found
      if (remNode == null)
         return false;

      // Case 1: Internal with 2 Children
      if (remNode.right != null && remNode.left != null) {

         BSTNode<T> succNode = remNode.right;
         BSTNode<T> succParent = remNode;
         while (succNode.left != null) {
            succParent = succNode;
            succNode = succNode.left;
         }
         remNode.key = succNode.key;

         return recursiveRemove(succParent, succNode);
      }
      // Case 2: Root node (1 or 0 children)
      else if (remNode == root) {
         if (remNode.left != null)
            root = remNode.left;
         else
            root = remNode.right;
      }
      // Case 3: Internal left child only
      else if (remNode.left != null) {

         if (parentNode.left == remNode)
            parentNode.left = remNode.left;
         else
            parentNode.right = remNode.left;
      }

      // Case 4: Right child and leaf(null)
      else if (remNode.right != null) {
         if (parentNode.left == remNode)
            parentNode.left = remNode.right;
         else
            parentNode.right = remNode.right;
      }
      return true;
   }

   /**
    * Method: insert
    * 
    * @param node
    */
   public void insert(BSTNode<T> node) {
      // Check if tree is empty
      if (root == null)
         root = node;
      else {
         BSTNode<T> currentNode = root;
         while (currentNode != null) {
            if (node.key.compareTo(currentNode.key) < 0) {
               // If no left child exists, add the new node
               // here; otherwise repeat from the left child.
               if (currentNode.left == null) {
                  currentNode.left = node;
                  currentNode = null;
               } else
                  currentNode = currentNode.left;
            } else {
               // If no right child exists, add the new node
               // here; otherwise repeat from the right child.
               if (currentNode.right == null) {
                  currentNode.right = node;
                  currentNode = null;
               } else
                  currentNode = currentNode.right;
            }
         }
      }
   }

   /**
    * Method: remove
    * 
    * @param key
    * @return
    */
   public boolean remove(T key) {
      BSTNode<T> parent = null;
      BSTNode<T> currentNode = root;

      // Search for the node.
      while (currentNode != null) {
         // Check if currentNode has a matching key.
         if (currentNode.key.compareTo(key) == 0) {
            if (currentNode.left == null && currentNode.right == null) {
               if (parent == null) // Node is root
                  root = null;
               else if (parent.left == currentNode)
                  parent.left = null;
               else
                  parent.right = null;

               return true; // Node found and removed
            } else if (currentNode.left != null && currentNode.right == null) {
               if (parent == null) // Node is root
                  root = currentNode.left;
               else if (parent.left == currentNode)
                  parent.left = currentNode.left;
               else
                  parent.right = currentNode.left;
               return true; // Node found and removed
            } else if (currentNode.left == null && currentNode.right != null) {

               if (parent == null) // Node is root
                  root = currentNode.right;
               else if (parent.left == currentNode)
                  parent.left = currentNode.right;
               else
                  parent.right = currentNode.right;
               return true; // Node found and removed
            } else {
               // Find successor (leftmost child of right subtree)
               BSTNode<T> successor = currentNode.right;
               while (successor.left != null) {
                  successor = successor.left;
               }
               currentNode.key = successor.key; // Copy successor to current node
               parent = currentNode;
               currentNode = currentNode.right; // Remove successor from right subtree
               key = successor.key; // Loop continues with new key
            }

         } else if (currentNode.key.compareTo(key) < 0) { // Search right
            parent = currentNode;
            currentNode = currentNode.right;
         } else { // Search left
            parent = currentNode;
            currentNode = currentNode.left;
         }
      }
      return false; // Node not found
   }

   /**
    * Method: getTreeHeight
    * 
    * Follows down the entire tree reaching the final level. once level is reached, 
    * a 1 is added for each return, and is then also compared with the value attained by the children
    * @param node
    * @return maximum BST height
    */
   public int getTreeHeight(BSTNode<T> node) {

      if (node == null)
         return -1;
      int leftHeight = getTreeHeight(node.left);
      int rightHeight = getTreeHeight(node.right);
      return 1 + Math.max(leftHeight, rightHeight);
   }
   /**
    * Method:        preOrder
    * 
    */
   public void preOrder() {
      preOrder(root);
      System.out.println();
   }
   /**
    * Method:        preOrder
    * prints BST data in ascending order (least to greatest)
    * @param node
    */
   private void preOrder(BSTNode<T> node) {
      if (node == null) {
         return;
      }
      preOrder(node.left);
      System.out.print(node.key + " ");
      preOrder(node.right);
   }
   /**
    * Method:        postOrder
    * prints BST data in descending order (greatest to least)
    */
   public void postOrder() {
      postOrder(root);
      System.out.println();
   }
   /**
    * Method:        postOrder
    * prints BST data in descending order (greatest to least)
    * @param node
    */
   private void postOrder(BSTNode<T> node) {
      if (node == null) {
         return;
      }

      postOrder(node.right);
      System.out.print(node.key + " ");
      postOrder(node.left);
   }
   /**
    * Method:        levelOrder
    * prints BST data in order of tree level
    */
   public void levelOrder() {
      if (root == null)
         return;

      LinkedList<BSTNode<T>> queue = new LinkedList<>();
      queue.offer(root);
      levelOrder(root, queue);
   }
   /**
    * Method:        levelOrder
    prints BST data in order of tree level
    * @param node
    */
   private void levelOrder(BSTNode<T> node, LinkedList<BSTNode<T>> queue) {
      node = queue.peek();
      try {
         System.out.print(node.key + " ");
         if (node.left != null)
         queue.offer(node.left);
         
         if (node.right != null)
         queue.offer(node.right);
      } catch (NullPointerException e) {}
      if (!queue.isEmpty()) {
         levelOrder(queue.poll(), queue);
      }
   }
}
