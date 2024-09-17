/***************************************************************************************************
 * @file                PostfixEvaluator.java
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
public class ExpressionEvaluator {
    /****************************************************************************************************
     * Method:      evaluatePostfix
     * 
     * O(N)
     * 
     * -Steps:
     * 1. Scans each character in expression
     * 2. If operand, push to stack.
     * 3. If operator, pop last 2 values and use the operator to push the resultant
     * 4. Once all characters have been scanned, and one element remains, return the remaining element
     * 
     * @param str
     * @return  Returns the solution to the entered postfix expression
     * @throws IllegalArgumentException
     *****************************************************************************************************/
    public static void evaluatePostfix(String str) throws IllegalArgumentException {
        Stack<Integer> operands = new Stack<>();
        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            //if digit, push to stack
            if (Character.isDigit(c)) {
                operands.push(Integer.parseInt(Character.toString(c)));
                //else if nothing, print error statement
            } else if (Character.valueOf(c).equals('\0')) {
                System.out.println("No expression was entered! please try again!");
                // else if operator, pop 2 values from stack and get push the
            } else {
                int v1 = 0, v2 = 0;
                try {
                    v1 = operands.pop();
                    v2 = operands.pop();    
                } catch (NullPointerException e) {
                    System.out.println("ERROR: PLEASE CHECK FORMATING OF EXPRESSION AND TRY AGAIN");
                }
                //otherwise, pop 2 elements and push the evaluation of elements and operator
                switch (c) {
                    case '+' -> operands.push(add(v1, v2));
                    case '-' -> operands.push(subtract(v2, v1));
                    case '*' -> operands.push(mult(v1, v2));
                    case '/' -> operands.push(divide(v2, v1));
                    default -> throw new IllegalArgumentException("Operator not recognized!!!");
                }
            }
        }
        //if no elements were manipulated due to operators print error
        if (str.length() == operands.size()) {
            System.out.println("No operands recognized in expression! Please append operands to expression to do calculations");
            //else return the solution and print
        } else if (operands.size() > 1) {
            System.out.print("Values still remaining: ");
            for (int i = 0; i <= operands.size(); i++) {
                System.out.print(operands.pop() + ", ");
            }
            System.out.println();
        } else {
            System.out.println("Expression evaluates to:\t" + operands.pop());
        }
    }
    /**
     * Method:      add
     * 
     * @param v1
     * @param v2
     * @return sum of v1 and v2
     */
    private static int add(int v1, int v2) {
        int sum = v1 + v2;
        System.out.println(v2 + " + " + v1 + " = " + sum);
        return sum;
    }
    /**
     * Method:      subtract
     * 
     * @param v2
     * @param v1
     * @return difference between v2 and v1
     */
    private static int subtract(int v2, int v1) {
        int diff = v2 - v1;
        System.out.println(v2 + " - " + v1 + " = " + diff);
        return diff;
    }
    /**
     * Method:      mult
     * 
     * 
     * @param v1
     * @param v2
     * @return product of v1 and v2
     */
    private static int mult(int v1, int v2) {
        int prod = v1 * v2;
        System.out.println(v2 + " * " + v1 + " = " + prod);
        return prod;
    }
    /**
     * Method:      divide
     * 
     * @param numerator
     * @param denominator
     * @return quotient of the numerator param divieded by denom param
     */
    private static int divide(int numerator, int denominator) {
        int quotient = numerator / denominator;
        System.out.println(numerator + " / " + denominator + " = " + quotient);
        return quotient;
    }
}
