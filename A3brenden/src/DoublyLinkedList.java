/***********************************************************************************************************
 * @file                DoublyLinkedList.java
 * @author              Brenden O'Brien
 * @date                2024-01-27
 * 
 * Course:              CPS 101<p>
 * 
 * Problem Statement:   Implementing an ordered list and demonstrate its 
 *                      use with an application.
 * 
 * INPUT:               5 randomly generated integers and select names.
 * 
 * OUTPUT:              -Upon start, the program does the following:
 *                          1. Create a OrderedDLinkedList for both Strings and Integers
 *                          2. add 5 randomly generated integers and provided unorganized names to list
 *                          3. During the addition of elements, the add() method ensures that the list 
 *                             remains in acsending order
 *                          4. Once no more elements are being added, print the organized list
 **************************************************************************************************************/
/**********************************************
 * Class:       Node
 * 
 * Outlines node fields such as data held
 * and pointers and contains constructor method
 ***********************************************/
class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> previous;
   /***************************
    * Constructor:      Node

    * Creates a new node with the provided data.
    * It's pointers are set to null to be prepared for insertion
    * @param initialData
    ****************************/
    public Node(T initialData) {
       data = initialData;
       next = null;
       previous = null;
    }
 }
 /****************************************
  * Class:      LinkedList
  *
  * Contains head and tail nodes,
  * outlines constructor method
  * and other methods for configuring list
  ***************************************/
 class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    /******************************
     * Constructor:     LinkedList
     * 
     * Creates new LinkedList and sets
     * head and tail nodes to null
     ******************************/
    public LinkedList() {
       head = null;
       tail = null;
    }
     /***************************************************
      * Method:     append
      *
      * @param      newNode - node to add to end of list
      ****************************************************/
    public void append(Node<T> newNode) {
       if (head == null) {
          head = newNode;
          tail = newNode;
       }
       else {
          tail.next = newNode;
          newNode.previous = tail;
          tail = newNode;
       }
    }
    /******************************************
     * Method:      prepend
     * 
     * @param       newNode - node to add to beginning of list
     *****************************************/
    public void prepend(Node<T> newNode) {
       if (head == null) {
          head = newNode;
          tail = newNode;
       }
       else {
          newNode.next = head;
          head.previous = newNode;
          head = newNode;
       }
    }
    /************************************
     * Method:      printList
     * 
     * Iterates through entire LinkedList
     * and prints each node's data
     ***********************************/
    public void printList() {
       Node<T> node = head;
       while (node != null) {
          System.out.print(node.data + " ");
          node = node.next;
       }
       System.out.println();
    }
    /**********************************
     * Method:      insertAfter
     * 
     * @param       currentNode
     * @param       newNode
     **********************************/
    public void insertAfter(Node<T> currentNode, Node<T> newNode) {
       if (head == null) {
          head = newNode;
          tail = newNode;
       }
       else if (currentNode == tail) {
          tail.next = newNode;
          newNode.previous = tail;
          tail = newNode;
       }
       else {
          Node<T> successor = currentNode.next;
          newNode.next = successor;
          newNode.previous = currentNode;
          currentNode.next = newNode;
          successor.previous = newNode;
       }
    }
    /********************************
     * Method:      remove
     * 
     * @param       currentNode - Node to remove
     *******************************/
    public void remove(Node<T> currentNode) {
       Node<T> successor = currentNode.next;
       Node<T> predecessor = currentNode.previous;
       
       if (successor != null)
          successor.previous = predecessor;
          
       if (predecessor != null)
          predecessor.next = successor;
          
       if (currentNode == head)
          head = successor;
          
       if (currentNode == tail)
          tail = predecessor;
    }
    /**************************************
     * Method getHeadNode()
     * 
     * @return List's head node
     **************************************/
    public Node<T> getHeadNode() {
        return this.head;
    }
    /***************************************
     * Method: getTailNode() 
     * 
     * @return List's tail node
     **************************************/
    public Node<T> getTailNode() {
        return this.tail;
    }
    public boolean isEmpty() {
      return head == null;
    }
    public int size() {
      int length = 0;
      Node<T> curNode = head;
      while(curNode != null) {
         length++;
         curNode = curNode.next;
      }
      return length;
    }
 }
