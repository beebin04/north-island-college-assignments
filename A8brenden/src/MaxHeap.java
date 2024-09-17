/**
 * @file MaxHeap.java
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
@SuppressWarnings("unchecked")
/**
 * Class: MaxHeap
 * 
 * <p>
 * contains generic heap array including resizing method, insertion and removal
 * methods, and methods for checking max heap property and moving items as
 * needed
 */
class MaxHeap<T> {
    private T[] heapArray;
    private int heapSize;

    /**
     * Constructor: MaxHeap
     * 
     * <p>
     * initializes new MaxHeap object with underlying heapArray container
     */
    public MaxHeap() {

        heapArray = (T[]) new Comparable[2];
        heapSize = 0;
    }

    /**
     * Method: resizeArray
     * 
     * <p>
     * makes a new array of double the length and copies the data from the previous
     * array to the new one
     */
    private void resizeArray() {
        int newLength = heapArray.length * 2;
        T[] newArray = (T[]) new Comparable[newLength];
        if (newArray != null) {
            // Copy from existing array to new array
            for (int i = 0; i < heapArray.length; i++) {
                newArray[i] = heapArray[i];
            }

            // Set the reference to the new array
            heapArray = newArray;
        }
    }

    /**
     * Method: percolateUp
     * 
     * <p>
     * checks if provieded node follows max heap property. if it fails to meet
     * criteria the node is swapped with its parent. It continues this loop while
     * the nodeIndex being used for comparison reaches past the root node
     * 
     * @param nodeIndex - array index of element being moved
     */
    private void percolateUp(int nodeIndex) {
        while (nodeIndex > 0) {
            // Compute the parent node's index
            int parentIndex = (nodeIndex - 1) / 2;

            // Check for a violation of the max heap property
            if (((Comparable<T>) heapArray[nodeIndex]).compareTo(heapArray[parentIndex]) <= 0) {
                // No violation, so percolate up is done.
                return;
            } else {
                // Swap heapArray[nodeIndex] and heapArray[parentIndex]
                T temp = heapArray[nodeIndex];
                heapArray[nodeIndex] = heapArray[parentIndex];
                heapArray[parentIndex] = temp;

                // Continue the loop from the parent node
                nodeIndex = parentIndex;
            }
        }
    }

    /**
     * Method: percolateDown
     * 
     * <p>
     * checks child nodes of node with provided index and checks the for the max
     * among the parent and its children. if its less than either children it is
     * swapped and the process continues from that child's index.
     * 
     * @param nodeIndex - node being checked for the max heap property
     */
    private void percolateDown(int nodeIndex) {
        int childIndex = 2 * nodeIndex + 1;
        T value = heapArray[nodeIndex];

        while (childIndex < heapSize) {
            // Find the max among the node and all the node's children
            T maxValue = value;
            int maxIndex = -1;
            int i = 0;
            while (i < 2 && i + childIndex < heapSize) {
                if (((Comparable<T>) heapArray[i + childIndex]).compareTo(maxValue) > 0) {
                    maxValue = heapArray[i + childIndex];
                    maxIndex = i + childIndex;
                }
                i++;
            }

            // Check for a violation of the max heap property
            if (maxValue == value) {
                return;
            } else {
                // Swap heapArray[nodeIndex] and heapArray[maxIndex]
                T temp = heapArray[nodeIndex];
                heapArray[nodeIndex] = heapArray[maxIndex];
                heapArray[maxIndex] = temp;

                // Continue loop from the max index node
                nodeIndex = maxIndex;
                childIndex = 2 * nodeIndex + 1;
            }
        }
    }

    /**
     * Method: insert
     * <p>
     * inserts new item into array according to its value. it is placed at the end
     * of the array and then percolated up the array following the max heap property
     * 
     * @param value data being inserted
     */
    public void insert(T value) {
        // Resize if needed
        if (heapSize == heapArray.length) {
            resizeArray();
        }

        // Add the new value to the end of the array
        heapArray[heapSize] = value;
        heapSize++;

        // Percolate up from the last index to restore heap property.
        percolateUp(heapSize - 1);
    }

    /**
     * Method: remove
     * 
     * <p>
     * removes value from front of the array, then places the last item to the fron
     * and percolates it down the array
     * 
     * @return item from front of queue
     */
    public T remove() {
        // Save the max value from the root of the heap.
        T maxValue = heapArray[0];
        T replaceValue;
        // Move the last item in the array into index 0.
        if (heapSize != 0) {
            replaceValue = heapArray[heapSize - 1];
            heapSize--;
        } else {
            return null;
        }
        if (heapSize > 0) {
            heapArray[0] = replaceValue;

            // Percolate down to restore max heap property.
            percolateDown(0);
        }

        // Return the max value
        return maxValue;
    }

    /**
     * Method: getHeapArrayString
     * 
     * <p>
     * formats array data into a string
     * 
     * @return string containing array data
     */
    public String getHeapArrayString() {
        if (heapSize == 0) {
            return "[]";
        }

        String arrayString = String.format("[%d", heapArray[0]);
        for (int i = 1; i < heapSize; i++) {
            arrayString += (", " + heapArray[i]);
        }
        return arrayString + "]";
    }

    /**
     * Method: getHeapSize
     * 
     * @return heapSize field
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Method: peek
     * 
     * @return item from front of array
     */
    public T peek() {
        return heapArray[0];
    }
}
