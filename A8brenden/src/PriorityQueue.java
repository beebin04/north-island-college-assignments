/**
 * @file PriorityQueue.java
 * @author Brenden O'Brien
 * @version 1.0
 * @date 2024-03-31
 * @brief Course CPS 101
 * 
 *        <p>
 *        Problem Statement: Implement a priority queue using a heap
 *        apply it in a hospital scenario.
 * 
 *        <p>
 *        INPUT: Objects of the Patient class are created and
 *        assigned a name and priorty representating
 *        the urgency of when they need to be treated.
 * 
 *        <p>
 *        OUTPUT: Hosputal brings patients into care according to their
 *        priority level.
 */
/**
 * Class: PriorityQueue
 * 
 * <p>
 * Uses MaxHeap object queue as an underlying container for the PriorityQueue.
 * Contains enqueue, dequeue methods as well as peek, isEmpty and getLength
 */
public class PriorityQueue<T> {
    private MaxHeap<T> queue;

    /**
     * Constructor: PriorityQueue
     * 
     * <p>
     * creates a new Priority queue using the MaxHeap class as an underlying
     * container
     */
    PriorityQueue() {
        queue = new MaxHeap<>();
    }

    /**
     * Method: enqueue
     * 
     * <p>
     * inserts a new item into priorty queue using MaxHeap insert method. item is
     * inserted and placed in heapArray according to its value
     * 
     * @param item - item being inserted
     */
    public void enqueue(T item) {
        queue.insert(item);
    }

    /**
     * Method: dequeue
     * 
     * <p>
     * returns item from the front of the queue, which would be the item of the
     * highest priority
     * 
     * @return - item with highest priority
     */
    public T dequeue() {
        return queue.remove();
    }

    /**
     * Method: peek
     * 
     * <p>
     * returns the item from the fron of the highest priority without removing it
     * 
     * @return - item from front of queue
     */
    public T peek() {
        return queue.peek();
    }

    /**
     * Method: isEmpty
     * 
     * <p>
     * checks for object nullility on front of queue
     * 
     * @return false if object is not null, true if null
     */
    public boolean isEmpty() {
        if (!queue.peek().equals(null))
            return false;
        else
            return true;
    }

    /**
     * Method: getLength
     * 
     * @return length of heapArray
     */
    public int getLength() {
        return queue.getHeapSize();
    }

}
