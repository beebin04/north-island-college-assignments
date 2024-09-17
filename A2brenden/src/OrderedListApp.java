/*******************************************************************************************************
 * @file                OrderedListApp.java
 * @author              Brenden O'Brien
 * @date                2024-01-27
 * 
 *Course:               CPS 101<p>
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
 *                          5. Remove elements from both lists printing which elements are being deleted
 *                             and print the remaining elements afterwards
 *******************************************************************************************************/
/*********************************************
 * Class:       OrderedListApp
 * 
 * Contains main method and creates OrderedDLinkedList
 * to add Integer and String values to demonstrate functioning
 * add() and remove() methods.
 *********************************************/
public class OrderedListApp {

    //Create list of names to be generated
    private static String[] nameList = {"Brian", "Zach", "Avery", "Melanie", "Thomas"};

    public static void main(String[] args) {

        //create OrderedDLinkedLists of both Integer and String types
        OrderedDLinkedList<Integer> intList = new OrderedDLinkedList<>();
        OrderedDLinkedList<String> strList = new OrderedDLinkedList<>();

        //print 5 randomly generated integers and add them to list to be sorted
        System.out.println("===================================\nOriginal placement of elements:\n");
        for (int i = 0; i < 5; i++) {
            int newVal = (int) (Math.random() * 200 - 100);
            System.out.print(" " + newVal);
            intList.add(newVal);
        }

        //print names from nameList[] and add them to list to be sorted
        System.out.println();
        for (int i = 0; i < 5; i++) {
            String newStr = nameList[i];
            System.out.print(" " + newStr);
            strList.add(newStr);
        }

        System.out.println("\n====================================\nPlacements after sorting:\n");

        //print sorted Integer and String lists
        intList.printList();
        strList.printList();
        System.out.println("===================================");

        //declare nodes to try to remove
        Node<Integer> intRemNode1 = intList.getTailNode();
        Node<Integer> intRemNode2 = intRemNode1.previous.previous;
        Node<String> strRemNode1 = strList.getHeadNode().next;
        Node<String> strRemNode2 = strRemNode1.next;

        //print data being removed
        System.out.println("Removed these values:\n");
        System.out.println(intRemNode1.data + " " + intRemNode2.data + " " + strRemNode1.data + " " + strRemNode2.data);
        System.out.println("===================================\nLists after element removal:\n");
        
        //execute remove method on data
        intList.remove(intRemNode1);
        intList.remove(intRemNode2);
        strList.remove(strRemNode1);
        strList.remove(strRemNode2);

        //print both lists after data removal
        intList.printList();
        strList.printList();
        System.out.println("===================================");

    }
}