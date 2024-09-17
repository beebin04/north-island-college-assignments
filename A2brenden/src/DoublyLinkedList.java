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
      * adds a new node after the last element
      * in the list. If list is empty, sets head and tail
      * to node being inserted
      *
      * @param      newNode
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
     * Adds a new node to the beginning of
     * the list. If list is empty, set head
     * and tail nodes to new node.
     * 
     * @param       newNode
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
     * inserts a new node into the list following
     * currentNode. If list is empty, head and tail
     * are set as newNode. If param currentNode is at the tail,
     * set the tail's next pointer to the newNode, newNode's prev pointer to
     * tail, then set newNode as the new tail of list.
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
     * Removes node currentNode from the LinkedList
     * by linking currentNode's successor and predecessor node
     * pointers to eachother.
     * 
     * @param       currentNode
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
     * Accessor method for returning
     * list's current head node
     * 
     * @return List's head node
     **************************************/
    public Node<T> getHeadNode() {
        return this.head;
    }
    /***************************************
     * Method: getTailNode() 
     * 
     * Accessor method for returning list's
     * current tail node
     * 
     * @return List's tail node
     **************************************/
    public Node<T> getTailNode() {
        return this.tail;
    }
 }
