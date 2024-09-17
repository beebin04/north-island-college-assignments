/**
 * @file Patient.java
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
 * Class: Patient
 * 
 * <p>
 * Patient objects contain name and priority field. patient objects may be
 * intilaized with null values or values provided in parameters. patient objects
 * are compared by their priority
 */
public class Patient implements Comparable<Patient> {

    private int priority;
    private String pName;

    /**
     * Constructor: Patient
     * 
     * <p>
     * intializes a null patient with no name and -1 prioty, meaning they will be
     * 'brought into care' last if in a queue
     */
    Patient() {
        priority = -1;
        pName = "(null)";
    }

    /**
     * Constructor: Patient
     * 
     * <p>
     * intializes new patient with provided name and priority
     * 
     * @param name    - patient's name
     * @param priorty - patients medical condition, greater the value the more
     *                urgent their condition
     */
    Patient(String name, int priorty) {
        this.priority = priorty;
        pName = name;
    }

    /**
     * Method: toString
     * 
     * @return String including patient name and priority
     */
    public String toString() {
        return String.format("%s, Priority -- %d", pName, priority);
    }

    /**
     * Method: getName
     * 
     * @return string including patient name only
     */
    public String getName() {
        return pName;
    }

    /**
     * Method: compareTo
     * 
     * @param o - patient being compared
     * @return 1 if this patient priority is greater than one being compared, 0 if
     *         equal, and -1 if less
     */
    @Override
    public int compareTo(Patient o) {
        if (priority > o.priority)
            return 1;
        else if (priority == o.priority)
            return 0;
        else
            return -1;
    }

}
