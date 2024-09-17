/***************************************************************************************************
 * @file                Queue.java
 * @author              Brenden O'Brien
 * @date                2024-02-03
 * 
 * Course:              CPS 101<p>
 * 
 * Problem Statement:   Implement the Stack ADT and Queue ADT,
 *                      and display their applicability.
 * 
 * INPUT:               Stacks and Queues take either fixed values in the program
 *                      or user given inputs; for the palindrome and postfix evaluator,
 *                      values used are read from user input.
 * 
 * OUTPUT:              Takes advantage of the duality between stacks and queues with
 *                      the palindrome checker, where user strings are checked by an
 *                      algorithm for if they can be read the same backwards. Uses stacks to
 *                      do postfix operations and produce a final answer with the remaining element
 *                      in the stack.
 ****************************************************************************************************/
public class Queue<T> {
    private LinkedList<T> linkedList;
    /**
     * Constructor:     Queue
     * 
     */
    Queue() {
       linkedList = new LinkedList<>();
    }
    /**
     * Method:      enqueue
     * 
     * @param newData
     */
    public void enqueue(T newData) {
       Node<T> newNode = new Node<T>(newData);
        
       linkedList.append(newNode);
    }
    /**
     * Method:      dequeue
     * 
     * @return
     */
    public T dequeue() {

       T dequeuedItem = linkedList.getHeadNode().data;
       linkedList.remove(linkedList.getHeadNode());
       
       return dequeuedItem;
    }
    /**
     * Method:      print()
     */
    public void print() {
       linkedList.printList();
    }
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
    public int size() {
      return linkedList.size();
    }
 }