/***************************************************************************************************
 * @file                Stack.java
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
public class Stack<T> {
    private LinkedList<T> list;
     
    Stack() {
       list = new LinkedList<>();
    }
    
    public void push(T newData) {
       Node<T> newNode = new Node<T>(newData);
       list.prepend(newNode);
    }
    
    public T pop() {
       Node<T> poppedNode = list.getHeadNode();
       list.remove(poppedNode);
       return poppedNode.data;
    }
    public T peek() {
        return list.getHeadNode().data;
    }
    
    public void print() {
       list.printList();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public int size() {
      return list.size();
    }
 }
