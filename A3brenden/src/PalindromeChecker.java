/***************************************************************************************************
 * @file                PalindromeChecker.java
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
public class PalindromeChecker {

    /**
     * Method:      isPalindrome
     * 
     * @param       inputString - String to check for palindrome
     * @return      returns true if entered string is the same read backwards,
     *              false if not
     */
    public static boolean isPalindrome(String inputString) {

        Queue<Character> normalString = new Queue<>();
        Stack<Character> invertedString = new Stack<>();
        String newString = getLetters(inputString);

        for (int i = 0; i < newString.length(); i++) {

            Character ch = newString.charAt(i);
            normalString.enqueue(ch);
            invertedString.push(ch);

        }

        return compareChars(normalString, invertedString);
            
    }
    /**
     * Methpd:      compareChars
     * 
     * @param fifo
     * @param lifo
     * @return
     */
    private static boolean compareChars(Queue<Character> fifo, Stack<Character> lifo) {
        while (!fifo.isEmpty() && !lifo.isEmpty()) {
            Character c1 = fifo.dequeue();
            Character c2 = lifo.pop();
            System.out.println(c1 + "\t" + c2);
            if (c1.compareTo(c2) != 0) {
                System.out.println("String is not a palindrome....");
                return false;
            }
        } 
        System.out.println("String is a palindrome!!!");
        return true;
    }
    /**
     * Method:      getLetters
     * 
     * @param str
     * @return
     */
    private static String getLetters(String str) {

        String newStr = "";

        for (int i = 0; i < str.length(); i++) {
            Character curChar = str.charAt(i);
            if (Character.isLetter(curChar)) {
                newStr = newStr.concat(curChar.toString().toLowerCase());
            }
        }
        return newStr;
    }
}
