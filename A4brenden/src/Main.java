/**
 * @file                Main.java
 * @author              Brenden O'Brien
 * @version             1.0
 * @date                2024-02-18
 * Course:              CPS 101
 * Problem Statement:   Create a program which enforces users to create passwords
 *                      according to their company's policy:
 *                      1.  is at least 8 characters long
 *                      2.  is not a word in the dictionary
 *                      3.  includes at least a digit and a letter
 *                      4.  is not a word in the dicitonary prefixed with a digit 
 *                          followed by a digit (1password1)
 *                      5.  is not two words in the dictionary seperated by a digit (pass1word)
 * 
 * INPUT:               1.  A text file containing a list of words to use for the 'dictionary'
 *                      2.  User input as strings which may or may not meet criteria
 * 
 * OUTPUT:              1. Will print dictionary data sorted alphabetically
 *                      2. Will then prompt user for passwords to check and will print a failure if fails to meet
 *                                              
 */
import java.util.Scanner;
/**
 * Class:       Main
 * 
 * Creates PasswordValidator object, and contains 
 * validatorApp() method for running main program
 * 
 */
public class Main {
    private static PasswordValidator passwordValidator = new PasswordValidator();
    /**
     * Method:      Main
     * @param args
     * 
     * Prints dicionary contents into console, then starts Validator section of program
     */
    public static void main(String[] args) {
        passwordValidator.printInfo();
        startValidatorApp();
    }
    /**
     * Method:      startValidatorApp
     * 
     * takes user input and checks to see provided password
     * follows company policy. Program ends when user enters "-1"
     */
    private static void startValidatorApp() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
        }
        System.out.print("|Password Validator|");
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
        }
        System.out.println();
        String input = "";
        while (!input.equals("-1")) {
            System.out.println("Enter a password or press -1 to exit");
            input = sc.nextLine();
            if (input.equals("-1")) {
                break;
            }
            System.out.println("\nEnter a password or press -1 to exit");
            if (!passwordValidator.isValid(input)) {
                System.out.println(input + " does not match criteria\n");
            } else {
                System.out.println(input + " meets password criteria!\n");
            }
        }
        sc.close();
    }
}
