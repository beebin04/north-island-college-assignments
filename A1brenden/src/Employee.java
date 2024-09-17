/******************************************************************************************
 * @file               Employee.java
 * @author             Brenden O'Brien
 * @version            1.0
 * @date               2024-01-16
 * 
 * @brief              Outlines the Employee data type and methods for registering and 
 *                     and managing store employees.<p>
 * 
 * Course:             CPS 101<p>
 * 
 * Problem Statement:  Create a program for employee management that 
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
/***********************************************************
 * Class:       Employee
 * 
 *              <p>Outlines and stores employee records as
 *              fields and also contains class methods
 **********************************************************/
public class Employee {
    private String empID = "-1";
    private String firstName = "null";
    private String lastName = "null";
    /***********************************************
     * Constructor:     Employee
     * 
     * <p>This is the default constructor for the class;
     * Employee object fields are set to either "-1" for
     * ID or "null" for employee name
     ***********************************************/
    Employee() {}
    /****************************************************************
     * Constructor:     Employee
     * 
     *<p> Sets newly created Employee object fields to corresponding
     * parameters by executing Employee mutator methods
     * 
     * @param id
     * @param fName
     * @param lName
     ****************************************************************/
    Employee(String id, String fName, String lName) {
        setID(id);
        setFirstName(fName);
        setLastName(lName);
    }
    /********************************
    * Method:      setID
    * 
    * <p>Sets the Employee object's
    * empID field to to provided String parameter
    * 
    * @param id
    *******************************/
    public void setID(String id) {
        empID = id;
    }
    /**************************************
     * Method:      setFirstName
     * 
     * <p>Sets the firstName field to the
     * provided String parameter
     * 
     * @param name
     *************************************/
    public void setFirstName(String name) {
        firstName = name;
    }
    /************************************
     * Method:      setLastName
     * 
     * <p>Mutator method which
     * sets the lastName field to the
     * provided String parameter
     * @param name
     **************************************/
    public void setLastName(String name) {
        lastName = name;
    }
    /*************************************
     * Method:      getID
     * 
     * <p>Accessor method which
     * returns the Employee object's
     * empID field
     * @return String empID
     *************************************/
    public String getID() {
        return empID;
    }
    /***********************************
     * Method:      getFirstName
     * 
     * <p>Accessor method which
     * returns the current Employee's
     * first name
     * @return String firstName
     *********************************/
    public String getFirstName() {
        return firstName;
    }
    /***********************************
     * Method:      getLastName
     * 
     * <p>Accessor method which 
     * returns the current Employee's
     * last name
     * @return String lastName
     *********************************/
    public String getLastName() {
        return lastName;
    }
    /*********************************************
     * Method:      toString
     * 
     * <p>Returns a formatted String containing the
     * Employee object's fields for smooth printing
     * 
     * @return The current employee's ID, followed
     * by their firstname, then their last name,
     *  with spaces in between
     *********************************************/
    public String toString() {
        return empID.concat(" " + firstName + " " + lastName);
    }
}
