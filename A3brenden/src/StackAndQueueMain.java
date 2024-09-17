/***************************************************************************************************
 * @file                StackAndQueueMain.java
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
import java.util.Scanner;
/**
 * Class:       StackAndQueueMain
 * 
 * Contains Integer and String Stacks and Queues for intial testing, also contains 
 * methods for starting PalindromeChecker and ExpressionEvaluator apps
 * 
 */
public class StackAndQueueMain {
    private static Stack<Integer> intStack = new Stack<>();
    private static Queue<Integer> intQueue = new Queue<>();
    private static Stack<String> strStack = new Stack<>();
    private static Queue<String> strQueue = new Queue<>();

    private static String[] nameList = { "Adam", "Bob", "Jack", "Tim" };
    private static Scanner scnr;
    /**
     * Method:      Main
     * 
     * Starts application, beginning with initial tests and then progresses
     * to other applications before finally closing
     * @param       args
     */
    public static void main(String[] args) {
        startApp();
        System.out.println("Program closing... Goodbye!");
    }
    /**
     * Method:      initalizeLists
     * 
     * fills Queues and Stacks with data
     * 
     */
    private static void initializeLists() {
        Integer newData = 10;
        while (newData <= 40) {
            intStack.push(newData);
            intQueue.enqueue(newData);
            newData += 10;
        }
        for (int i = 0; i < nameList.length; i++) {
            strStack.push(nameList[i]);
            strQueue.enqueue(nameList[i]);
        }
    }
    /**
     * Method:      emptyLists
     * 
     * Pops all data from Stacks and Queues
     * 
     */
    private static void emptyLists() {
        System.out.println();
        System.out.print("Data in Integer Stack:\n-------------------------\n\t");

        while (!intStack.isEmpty()) {
            System.out.print(intStack.pop() + " ");
        }

        System.out.print("\n--------------------------\nData in Integer Queue:\n--------------------------\n\t");
        while (!intQueue.isEmpty()) {
            System.out.print(intQueue.dequeue() + " ");
        }
        System.out.print("\n---------------------------\nData in String Stack:\n--------------------------\n\t");
        while (!strStack.isEmpty()) {
            System.out.print(strStack.pop() + " ");
        }
        System.out.print("\n-----------------------------\nData in String Queue:\n-------------------------\n\t");
        while (!strQueue.isEmpty()) {
            System.out.print(strQueue.dequeue() + " ");
        }
        System.out.println("\n");
    }
    /**
     * Method:      startApp
     * 
     * Method which gets called by Main(), leads to other methods for other apps
     */
    private static void startApp() {
        System.out.printf("%30s%n", "Stack & Queue Introduction");
        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        initializeLists();
        emptyLists();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        startPalindromeApp();
    }
    /**
     * Method:      startPalindromeApp
     * runs PalindromeChecker app until user inputs '-1'
     * 
     */
    private static void startPalindromeApp() {
        System.out.println("Palindrome Checking App Has Now Begun");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        String in;
        //while input does not equal "-1", get phrases and check for palindromes
        do {
            scnr = new Scanner(System.in);
            System.out.println("Enter a phrase: (press -1 to exit)");
            in = scnr.nextLine();
            if (!in.equals("-1")) {
                PalindromeChecker.isPalindrome(in);
            }
        } while (!in.equals("-1"));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        startPostFixEvaluatorApp();

    }
    /**
     * Method:      startPostFixEvaluatorApp
     * 
     * Gets an postfix expression from user input until user enters 'exit'
     */
    private static void startPostFixEvaluatorApp() {
        System.out.println("Postfix Expression Evaluator Has Now Begun!\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        String in = "null";
        do {
            System.out.println("Enter a postfix expression or enter exit to end program");
            try {
                //get expression
                in = scnr.nextLine();
                //if user enters string "exit", stop this part of the program
                if (in.toLowerCase().equals("exit")) {
                    break;
                }
                //evaluate expression
                ExpressionEvaluator.evaluatePostfix(in);

            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        } while (!in.toLowerCase().equals("exit"));

        System.out.println("Exiting PostfixEvaluator Program");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
    }
}
