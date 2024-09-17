/********************************************************************************************************
 * @file                OrderedDLinkedList.java
 * @author              Brenden O'Brien
 * @date                2024-01-27
 * @brief               Contains custom DLinkedList class
 *                      allowing for ordering of elements upon their insertion<p>
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
 *******************************************************************************************************/



/*************************************************
 * Class:       OrderedDLinkedList
 * 
 * <p>Contains constructor method and add() method
 *************************************************/
public class OrderedDLinkedList <T extends Comparable<T>> extends LinkedList <T> {
    /**************************************
     * Constructor:     OrderedDLinkedList
     * 
     * Creates a new OrderedDLinkedList object
     * by calling the LinkedList constructor
     **************************************/
    OrderedDLinkedList() {
        super();
    }
    /*********************************************************************************************************************************
     * Method:      add
     * @param       data<T>
     *
     * Worst Case:     O(N)
     * 
     * -During the worst case scenario, the data being added is greater than all other data in the list, and will be appended at the tail.
     * The program beigns by creating the Node curNode and setting it as the head node. the first if statement conditions are checked and not met
     * so the program continues to the while loop. Program will then keep doing iterations while moving curNode and checking conditions until
     * the node curNode has reached the tail; the program checks if the curNode is null, which indicates whether or not curNode is at tail.
     * As new data is largest element being added so far to list, the Node being added will always be at the end of the list and the program will
     * need to iterate through each Node and compare their data to the new data being added. So once program confirms second if statement condition,
     * new Node is appended to list.
     * 
     * Total Commands/Computations: (create curNode (+1); check first if statement conditions (+3);
     *                              iterate through entire list performing 4 commands each time (+4N); 
     *                              check while loop conditions again upon reaching end of list (+3);
     *                              check second if statement condition (+1);
     *                              perform append() method with node constructor as param (+2))
     *                              14N >= 4N + 9 for all N >= 1
     * 
     * Average Case:   O(N)
     * 
     * -During the average case scenario, the data being added is being inserted somewhere inbetween two 
     * other nodes. curNode is created and set as head. Program then checks both conditions within
     * first if statement. Program begins while loop and for each loop iteration
     * one command is executed and both conditions are checked. Upon exit, conditions are checked one final time and for this case
     * the newNode will not be inserted at the end, therefore the condition (curNode != null) will not be broken during this case.
     *  After loop exit, program checks if statement condition and if met performs insertAfter() method
     * with curNode's previous pointer and using Node constructor as parameters.
     * 
     * Total Commands/Computations: (create curNode (+1); check first if statement conditions (+3);
     *                              perform 4 commands per loop iteration (+4N); 
     *                              1 final condition check upon loop exit (+3); check second if statement
     *                              conditions(+1); perform insertAfter() method(+1) with curNode.previous
     *                              and using node constructor (+1))
     *                              4N + 10
     * Best Case:      O(1)
     * 
     * -During the best case scenario, the list is either empty or the first element is greater than
     * the element being inserted. A new node is created and set as the head node.
     * Program then checks first if statement, and if the current Node is null (the list is empty)
     * or if the data being added is less than the data in the head node, a new node with the provided
     * data is created and added to the beginning of the list.
     * Total Commands/Computations: (create curNode(+1); check if statement(+3); create new node with data(+1); prepend(+1))
     *                              6
     ********************************************************************************************************************************/
    public void add(T data) {

        //create new node and set as head of list
        Node<T> curNode = getHeadNode();

        //if list is empty or first node data is greater than data being inserted:
        if (curNode == null || data.compareTo(curNode.data) < 0) {

            //add new node with data to beginning of list
            prepend(new Node<T>(data));

        } else {

            //else while selected node is not null and selected node data is less than new data:
            while (curNode != null && data.compareTo(curNode.data) >= 0) {

                //set current node to following node
                curNode = curNode.next;
            }

            //if program encounters node which is greater than new data; insert new node after current previous node
            if (curNode != null) {
                insertAfter(curNode.previous, new Node<T>(data));

            //else if while loop ended because curNode is null; then end of list has been reached and no elements were greater than added element
            } else {
                
                //add greatest valued element to end of list to maintain organization
                append(new Node<T>(data));
            }

        }
        
    }
}