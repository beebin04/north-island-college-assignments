/******************************************************************************************
 * @file               EmployeeList.java
 * @author             Brenden O'Brien
 * @version            1.0
 * @date               2024-01-16
 * 
 * @brief              Creates list of employees from provided textfile.
 * 
 *Reads a textfile containing employee data and creates an Employee
 *ArrayList. Users may add, remove, search, or display all employees.<p>
 * 
 * Course:             CPS 101<p>
 * 
 * Problem Statement:  Creates a program for employee management that 
 *                     allows the user to add, search, remove an employee, 
 *                     as well as to display all employees.<p>
 * 
 * INPUT:              Textfile storing an Employee's ID, first name, and last name
 *                     per line, as well as user input of employee information.<p>
 * 
 * OUTPUT:            -Upon start, the program performs the following:
 *                          1.   Open file 'empdata.txt' and create Employee ArrayList
 *                          2.   Convert provided employee data into Employee objects and
 *                               add to the ArrayList
 *                          3.   Display menu to user, including options to add, find,
 *                               remove, display all, or exit
 *                          4.   Add an employee to the system with user-entered data
 *                          5.   Remove an employee by searching for their ID
 *                          6.   Display complete modified list
 *****************************************************************************************/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;
/****************************************************************
 * Class:       EmployeeList
 * 
 *<p>Contains Employee ArrayList which is created by
 *the constructor, and configured by class methods
****************************************************************/
public class EmployeeList {
    /*****************************************************************
     * Employee ArrayList to store and configure employees
     *****************************************************************/
    private ArrayList<Employee> empList;

    //Scanner for reading files
    private Scanner fileScanner;
    //Scanner for reading user input in terminal
    private Scanner terminalScanner = new Scanner(System.in);  
    /***********************************************************
     * Constructor:     EmployeeList
     * 
     *<p>Creates ArrayList<Employee> object and
     *adds new Employee objects to list by
     *reading textfile                 
     *
     * @throws          IOException
     * @throws          IllegalArgumentException
     ************************************************************/
    EmployeeList() throws IOException, IllegalArgumentException {
        //create Employee ArrayList
        empList = new ArrayList<Employee>();    
        try {
            //create scanner object by opening new input stream from empdata.txt file
            fileScanner = new Scanner(new FileInputStream("empdata.txt"));  
            while (fileScanner.hasNextLine()) {      

                //seperates each line into pieces of data
                String[] data = fileScanner.nextLine().split(" ");
                //checks if ids in file match formatting standard specifications
                if (data[0].length() > 3 || Integer.parseInt(data[0]) < 1) {
                    throw new IllegalArgumentException("ERROR: EMPLOYEE IDS IN 'empdata.txt' DO NOT MATCH FORMATTING STANDARDS OF SYSTEM\nIDS MUST BE 3 DIGITS, AND POSITIVE WHOLE NUMBERS. PLEASE CHECK FILE AND TRY AGAIN");
                }
                //uses data to create a new employee and adds to the list
                empList.add(new Employee(data[0], data[1], data[2]));   
            }
            fileScanner.close();
            //if file could not be found due to incorrect name, or placement; throw new IOException so EmployeeApp can catch and close program
        } catch (FileNotFoundException fnfe) {
            throw new IOException("ERROR: FILE 'empdata.txt' DOES NOT EXIST IN EXPECTED LOCATION. ENSURE FILE IS AMONG JAVA FILES BEFORE RETRYING.");
        }
    }
    /************************************************
     * Method:  pause
     * 
     *<p>Private helper method which 
     *pauses program after completing task
     ***********************************************/
    private void pause() {
        System.out.println("Press any key and enter to go back to the menu...");
        terminalScanner.nextLine();
    }
    /*************************************************************************************
     * Method:  addEmp
     * 
     *<p>Creates new employee object with user input as parameters and adds to list
     ************************************************************************************/
    public void addEmp() {
        clearConsole();
        //declare and initialize id variable through getValidID method
        String id = getValidID();
        //get remaining Employee information
        System.out.println("Please enter new employee's first name: ");
        String empFName = terminalScanner.nextLine();
        System.out.println("Please enter new employee's last name: ");
        String empLName = terminalScanner.nextLine();
        //add new employee to list with gathered data as parameters
        empList.add(new Employee(id, empFName, empLName));
        //display success message
        System.out.println("Employee successfully created and added to list!");
        //call pause method
        pause();
    }
    
    /***********************************************************
     * Method:      getValidID
     * <p>returns properly formatted ID after ensuring
     * it obeys company specifications
     * @return      Formatted ID
     **********************************************************/
    private String getValidID() {
        /*********************************************
         * ID user is searching for; initializes as -1
         *********************************************/
        int id = -1;
        // do-while loop continues as id = -1 allowing user to retry
        do {
            try {
                System.out.println("Please enter new employee's ID: (IDs can be up to 3 digits long)");
                 //get user input
                id = (Integer.parseInt(terminalScanner.nextLine()));
                //check bounds
                if (id > 999) { 
                    throw new IllegalArgumentException("ERROR: USER INPUT IS GREATER THAN 3 DIGITS. PLEASE KEEP DIGITS TO 3 OR LESS.");
                }
                if (id < 0) {
                    throw new Exception("ERROR: INPUT CONTAINS NON-NATURAL NUMBERS. ENSURE THAT ONLY NATURAL NUMBERS ARE ENTERED.\n(EX: 1, 2, 3...)");
                }
                //display message if non-naturals are entered, make user retry
            } catch (NumberFormatException nfe) {
                System.out.println("ERROR: NON-NATURAL NUMBERS DETECTED. PLEASE ENSURE ONLY NATURAL NUMBERS ARE ENTERED.\n(EX: 1, 2, 3...)");
                //display error message if id surpasses digit limit
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                id = -1;
                // display message if user input contains negative files
            } catch (Exception e) {
              System.out.println(e.getMessage());
              id = -1;
            }
        }while (id < 1);
        // return formatted id
        return formatID(id);
    }
    /*****************************************************************
     * Method:  formatID
     * 
     *          <p>formats IDs so that all Employee IDs have same length
     * 
     * @param   input
     * @return  String id that if the parameter 
     *          input is < 3 digits, zeros are placed in front
     ****************************************************************/
    private String formatID(int input) {
        return String.format("%03d", input);
    }
    /***********************************************************************************************
     * Method:      searchList
     * 
     *              <p>Private method which searches for and returns a specific employee using their ID
     *              
     *              -Steps
     *              1. get search key from user input
     *              2. Iterate through Employee ArrayList and compare the ID of each employee to
     *              the key value
     *              2. If a match is found, return that employee; otherwise, return null.
     * @return      Employee who's ID matches
     *              that of entered search value
     *              
     ***********************************************************************************************/
    private Employee searchList() {
        /**
         * s
         */
        String searchId = "-1";             
        // do-while loop prompts user to retry entering ID until they enter an ID within the valid range: 0 - 999
        do {
            try {
                System.out.println("Please enter ID of employee");
                 // get user input as search value
                searchId = terminalScanner.nextLine();         
                //check if user input passes boundary check:
                if (Integer.parseInt(searchId) > 999) {
                    throw new IllegalArgumentException("ERROR: USER INPUT IS GREATER THAN 3 DIGITS. PLEASE KEEP DIGITS TO 3 OR LESS.");
                }
                if (Integer.parseInt(searchId) < 0) {
                    throw new Exception("ERROR: INPUT CONTAINS NON-NATURAL NUMBERS. ENSURE THAT ONLY NATURAL NUMBERS ARE ENTERED.\n(EX: 1, 2, 3...)");
                }
                // print error if pass fails; then make user attempt again
            } catch (NumberFormatException nfe) {
                System.out.println("ERROR: NON-NATURAL NUMBERS DETECTED. PLEASE ENSURE ONLY NATURAL NUMBERS ARE ENTERED.\n(EX: 1, 2, 3...)");
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                searchId = "-1";
            } catch (Exception e) {
              System.out.println(e.getMessage());
              searchId = "-1";
            }
        }while (Integer.parseInt(searchId) < 1);
        // iterate through list and compare IDs of employees to search value
        for (Employee e : empList) {
            // if theres a match, print employee details and return employee after user ends pause
            if (searchId.compareTo(e.getID()) == 0) {
                System.out.println("Employee Found: " + e.toString());
                return e;
            }
        }
        // if there was no match, print message and pause the program until user continues. then return a null employee object
        System.out.println("No Employee Found");
        return null;
    }
    /***************************************************************
     * Method:      findEmp
     * 
     *              <p>Public method which returns an Employee provided
     *              by searchList() method
     * @return      Employee object returned by searchList() method
     *************************************************************/
    public Employee findEmp() {
        clearConsole();
        Employee e = searchList();
        pause();
        return e;
    }
    /*************************************************
     * Method:      removeEmp
     * 
     *              <p>Gets the index of an Employee in
     *              the ArrayList by using searchList algorithm.
     *              Uses index to remove Employee object from ArrayList
     *              
     ************************************************/
    public void removeEmp() {
        clearConsole();
        
        empList.remove(searchList());
        // pause program
        pause();
    }
    /*********************************************************************
     * Method:      displayEmps
     * 
     *              <p>iterates through Employee ArrayList printing each
     *              object's output of the toString() method.
     ********************************************************************/
    public void displayEmps() {
        clearConsole();
        //iterate through the empList array, and print each employee's toString() method
        for (Employee e : empList) {
            System.out.println(e.toString());
        }
        pause();
    }
    /******************************************************************
     * Method:      clearConsole
     * 
     *              <p>Prints 20 newlines as to remove any previous text
     *              in the terminal
     *******************************************************************/
    private void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}

