/**
 * @file                BSTNode.java
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
/**
 * Class:       BSTNode
 * 
 * contains key field and left/right pointer fields
 */
public class BSTNode<T> {
   public T key;
   public BSTNode<T> left;
   public BSTNode<T> right;
   /**
    * Constructor:        Node
     * Constructor initializes pointers to null, and key to provided data
    * @param data - data to link with new node
    */
   public BSTNode(T data) {

       key = data;
       left = null;
       right = null;
    }
}