/**
 * @file EmergencyRoom.java
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
 * Class: EmergencyRoom
 * 
 * <p>
 * Driver app for applying priorty queue in a hospital emergency room setting
 */
public class EmergencyRoom {
    private static PriorityQueue<Patient> patientQueue = new PriorityQueue<>();
    private static String[] pNameList = { "John Smith", "Jane Doe", "Gregory House", "Mary Lou", "Lucas Mendoza" };

    /**
     * Method: main()
     * 
     * <p>
     * Creates, displays and inserts new patients into priortiy queue with a random
     * priority value
     * 
     * <p>
     * Patients are then removed from queue with the highest priorty patient always
     * being removed first
     * 
     * @param args
     */
    public static void main(String[] args) {
        for (String s : pNameList) {
            Patient p = new Patient(s, (int) (Math.random() * 5));
            System.out.print("New Patient entering:\n\t");
            System.out.println(p.toString());
            patientQueue.enqueue(p);
        }
        System.out.println("\n");
        while (true) {
            Patient p = patientQueue.dequeue();
            if (p == null) {
                break;
            }
            System.out.println("Patient " + p.getName() + " brought into care");
        }

    }
}
