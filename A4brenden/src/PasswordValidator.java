/**
 * @file                PasswordValidator.java
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class:       PasswordValidator
 * 
 * Contains dictionary, and isValid() method for checking passwords
 */
public class PasswordValidator {
    private ChainingHashTable dictionary;
    private final int MIN_CHARACTER_LENGTH = 8;

    /**
     * Constructor:     PasswordValidator
     * 
     * Creates a ChainingHashTable dicionary and stores words from text file in buckets sorted alphabetically
     */
    PasswordValidator() {

        dictionary = new ChainingHashTable();
        try {
            Scanner fileScan = new Scanner(new FileInputStream("words.txt"));
            while (fileScan.hasNextLine()) {
                String word = fileScan.nextLine();
                dictionary.insert(word.toLowerCase(), word);
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("DICTIONARY FILE COULD NOT BE LOCATED");
        }
    }
    /**
     * Method:      printInfo()
     * 
     * Iterates through dictionary buckets and displays all entries by their first letter
     */
    public void printInfo() {
        for (int i = 0; i < dictionary.length(); i++) {

            String line = "Section ".concat(Character.toString(65 + i) + ": ");
            try {
                ChainingHashTableItem item = (ChainingHashTableItem) dictionary.retrieveFromIndex(i);
                while (!item.equals(null)) {
                    line += String.format("%s, ", item.value.toString());
                    item = item.next;
                }
            } catch (NullPointerException e) {
                line += "\n";
            }
            System.out.println(line);
        } 
    }
    /**
     * Method:      isValid
     *              
     *              1. Check password if password is greater or equal to 8 characters.
     *              2. Go through password characters
     *                  2a) check for at least one digit and letter
     *                  2b) if a character is detected, add to 'letters' String, and check if 'letters' is in dictionary
     *                  2c) if digit detected, and 'letters' length > 0, check dictionary
     *              3. If no words are in dictionary and contains at least 1 letter and digit, password follows policy
     *                  
     * @param pass - Password to be evaluated according to criteria
     * @return true if provided password meets criteria, false if otherwise
     */
    public boolean isValid(String pass) {
        if (pass.length() < MIN_CHARACTER_LENGTH) {
            System.out.println("Invalid Password: Must be greater or equal to 8 characters");
            return false;
        }

        boolean containsDigit = false;
        boolean containsLetter = false;
        String letters = "";
        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);

            if (Character.isDigit(c)) {
                containsDigit = true;

                try {   //if letters have occured before digit, check dicionary for the group of letters
                    if (letters.length() > 0 && !dictionary.search(letters).equals(null)) {
                        System.out.println("Invalid Password: Must not contain words from dictionary");
                        return false;
                    }
                } catch (NullPointerException e) {
                }
            } else if (Character.isLetter(c)) {
                containsLetter = true;
                letters += c;
                if (dictionary.search(letters) != null) {
                    System.out.println("Invalid Password: Must not contain words from dictionary");
                    return false;
                }
            }

        }
        if (!containsDigit || !containsLetter) {
            System.out.println("Invalid Password: Must contain at least both one letter and digit");
            return false;
        }
        return true;
    }
}