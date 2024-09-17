
/***************************************************************************************
 * @file               EmployeeApp.java
 * @author             Brenden O'Brien
 * @version            1.0
 * @date               2024-01-16
 * 
 * @brief              Creates a program for employee management; user may add, find,
 *                     remove, or display all employees the list.<p>
 * 
 * Course:             CPS 101<p>
 * 
 * Problem Statement:  Creates a program for employee managementthat 
 *                     allows the user to add, search, remove an employee, 
 *                     as well as to display all employees.<p>
 * 
 * INPUT:              Textfile storing an Employee's ID, first name, and last name
 *                     per line, as well as user input of employee information.<p>
 * 
 * OUTPUT:             -Upon start, the program performs the following:
 *                         1.   Open file 'empdata.txt' and creates Employee OrderedDLinkedList
 *                         2.   Convert provided employee data into Employee objects and
 *                              add to the OrderedDLinkedList
 *                         3.   Display menu to user, including options to add, find,
 *                              remove, display all, or exit
 *                         4.   If user adds new employee, store in list among other
 *                              employees with all data
 *                         5.   If user rmeoves existing employee, remove from list
 *                         6.   Display complete list of employees after user configuration
 *                     

 **************************************************************************************/

import java.io.IOException;
import java.util.Scanner;

/******************************************************************************
 * Class:   EmployeeApp
 * 
 *          <p>Contains main method; calls for a new EmployeeList object named eList
 *          then provides user with interface where they can make changes
 *          to the OrderedDLinkedList through the EmployeeList object.
 ******************************************************************************/
public class EmployeeApp {


    /*******************************************************************************************
     * Method:  Main
     * 
     *          <p>Main method which creates a new EmployeeList object
     *          and provides user with an interface from where configurations
     *          to the OrderedDLinkedList<Employee> field can be made.<p>
     *          -Steps:
     *              1. Create an initialize a new EmployeeList object
     *              2. Prompt user with menu and perform actions requested until user enters 'E'
     * 
     * @param args[] - String array holding commands to execute.
     *******************************************************************************************/
    public static void main(String[] args) {
        char userChoice = '\0';
        /** < Variable to store character user selected */
        try {
            // creates and initializes eList
            EmployeeList eList = new EmployeeList(); 

            //creates Scanner for user input in terminal
            Scanner scnr = new Scanner(System.in);  
            /**
             * do-while loop runs and prompts the user with a menu with
             * choices until they choose to exit the program
             */
            do {
                //display program name and options
                displayMenu();  

                //prompt user for input; store character selected in userChoice; convert to uppercase
                userChoice = Character.toUpperCase(scnr.nextLine().charAt(0));  
                //perform command corresponding to user input
                switch (userChoice) {
                    case 'A' -> eList.addEmp();
                    case 'B' -> eList.findEmp();
                    case 'C' -> eList.removeEmp();
                    case 'D' -> eList.displayEmps();
                    case 'E' -> System.out.println("Closing Manager...");
                    //if character entered is not one of the above, print error, then prompt user again
                    default -> System.out.println("ERROR: INVALID CHARACTER DETECTED; ENTER A VALID CHARACTER");
                }
                // if userChoice is 'E', end the loop thus ending the program
            } while (userChoice != 'E');
            //close scanner as loop ends
            scnr.close();
            /**
             * if EmployeeList() throws IOException error then during the constructor's
             * execution, then the file could not be found either due to an incorrect name, or
             * that it may be in the wrong location
             * */
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("SYSTEM CLOSING...");
            /**
             * if the program encounters an IllegalArgumentException, then the IDs of employees
             * withing the text file 'empdata.txt' does not match the typical 'company' formatting specifications
             * expected format is a 3 digit positive whole number ie. 070.
             */
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            System.out.println("SYSTEM CLOSING...");
        }
    }
    /***************************************************
     * Method: displayMenu()
     * 
     *        <p>Displays a list of options in the terminal 
     *        window that the user may choose from
     ***************************************************/
    private static void displayMenu() {;
        System.out.println("Employee Managing System");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("(A)|\tAdd A New Employee");
        System.out.println("(B)|\tFind An Employee");
        System.out.println("(C)|\tRemove An Employee");
        System.out.println("(D)|\tDisplay All Employees");
        System.out.println("(E)|\tClose Managing System");
        System.out.print("->");
    }
}