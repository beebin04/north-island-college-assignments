/**
 * @file                BSTApp.java
 * @author              Brenden O'Brien
 * @version             1.0
 * @date                2024-03-18
 * Course:              CPS 101
 * Problem Statement:   Enchance the BST ADT into a generic class
 *                      which also supports multiple traversal methods
 * INPUT:               User provides intergers and strings to input into BST trees
 *                      and Trie
 * OUTPUT:              With provided data, BST methods are tested with both integers and strings
 *                      displaying contents after a modification. The Trie class is tested similarily with strings
 *                 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Class:           BSTApp
 * 
 * contains Integer and String BST Trees for testing methods as well as Trie after testing
 * with BST's are complete
 */
public class BSTApp<T> {
    static BinarySearchTree<Integer> intTree = new BinarySearchTree<>();
    static BinarySearchTree<String> strTree = new BinarySearchTree<>();
    
    static Scanner scnr = new Scanner(System.in);
    /**
     * Method:      Main
     * Calls the Integer, String BST and Trie trial
     * methods for testing implementation
     * @param args
     */
    public static void main(String[] args) {
        runIntTrial();
        runStringTrial();
        runTrieTrial();
    }
    /**
     * Method:      runStringTrial
     * 
     * Creates a String BST from user input and displays initial contents,
     * then calls for user to search for a string in the tree and remove a string
     */
    private static void runStringTrial() {
        String key = "";
        strTree = getStringVals();
        System.out.println("INITIAL TREE");
        System.out.println(BSTPrint.treeToString(strTree.getRoot()));
        System.out.println("TREE HEIGHT:\t" + strTree.getTreeHeight(strTree.getRoot()));

        System.out.println("ENTER SEARCH KEY: ");
        key = scnr.nextLine();
        BSTNode<String> node = strTree.rSearch(key);
        if (node != null) 
            System.out.println("NODE " + key + " FOUND IN TREE");
        else
            System.out.println("NODE " + key + " NOT IN TREE");

        System.out.println("ENTER DATA TO REMOVE FROM TREE:");
        key = scnr.nextLine();
        boolean exists = strTree.rRemove(key);
        if (exists) System.out.println("NODE " + key + " FOUND AND REMOVED");
        else System.out.println("NOT FOUND; NO CHANGE TO TREE");
        System.out.println(BSTPrint.treeToString(strTree.getRoot()));

        System.out.println("PRE-ORDER TRAVERSAL SEQUENCE:");
        strTree.preOrder();
        System.out.println("POST-ORDER TRAVERSAL SEQUENCE:");
        strTree.postOrder();
        System.out.println("LEVEL-ORDER TRAVERSAL SEQUENCE:");
        strTree.levelOrder();
    }
    /**
     * Method:          showTree
     * 
     * Only works with Integer BST; prints the contents of the tree and it's height
     */
    private static void showTree() {
        System.out.println(BSTPrint.treeToString(intTree.getRoot()));
        System.out.println("TREE HEIGHT:\t" + intTree.getTreeHeight(intTree.getRoot()));
    }
    /**
     * Method:          getIntegerVals
     * 
     * gets Integers from user input on a line then parses and inserts each value into the BST
     * @return intTree - BST with provided user data
     */
    private static BinarySearchTree<Integer> getIntegerVals() {
        System.out.println("ENTER DATA ON LINE, WHEN FINISHED PRESS ENTER");
        String[] data = scnr.nextLine().split(" ");
        for (String s : data) {
            try {
                Integer i = Integer.parseInt(s);
                intTree.rInsert(new BSTNode<Integer>(i));
            } catch (Exception e) {
                System.out.println("ERROR PROCESSING INTEGERS, NO DATA ENTERED");
            }
        }
        return intTree;
    }
    /**
     * Method:              getStringVals
     * 
     * Gets strings from user from each line they enter; when user enters -1, end of data
     * @return strTree - BST with provided user strings
     */
    private static BinarySearchTree<String> getStringVals() {
        System.out.println("\nENTER DATA STRINGS; WHEN DONE, ENTER -1");
        while (true) {
            String input = scnr.nextLine();
            if (input.equals("-1"))
                break;
            strTree.rInsert(new BSTNode<String>(input));
        }
        strTree.rRemove(strTree.getRoot().key);
        return strTree;
    }
    /**
     * Method:          getKey
     * 
     * Attempts to read the next user-provided integer; if fails to parse, notify user.
     * @return key - user-provided integer
     */
    private static int getKey() {
        int key = 0;
        try {
            key = scnr.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("ERROR: PLEASE ENTER AN INTEGER");
        }
        return key;
    }
    /**
     * Method:          runIntTrial
     * 
     * creates Integer BST from provided user data, then displays contents and prompts user
     * to search, or remove data. Tree gets displayed after a modification
     */
    private static void runIntTrial() {
        int key = 0;
        intTree = getIntegerVals();
        System.out.println("INTIAL TREE");
        showTree();

        System.out.println("ENTER DATA TO SEARCH FOR IN TREE");
        key = getKey();
        BSTNode<Integer> node = intTree.rSearch(key);

        if (node != null)
            System.out.println("NODE " + key + " IS IN TREE");
        else
            System.out.println("NODE " + key + " NOT IN TREE");
        System.out.println("ENTER DATA TO REMOVE FROM TREE");

        key = getKey();

        boolean exists = intTree.rRemove(key);

        if (exists)
            System.out.println("NODE " + key + " REMOVED");
        else
            System.out.println("NODE NOT FOUND; NO CHANGE TO TREE");
        System.out.println("TREE AFTER REMOVAL\n");
        System.out.println(BSTPrint.treeToString(intTree.getRoot()));
        System.out.println("TREE HEIGHT:\t" + intTree.getTreeHeight(intTree.getRoot()));

        System.out.println("PRE-ORDER TRAVERSAL SEQUENCE:");
        intTree.preOrder();
        System.out.println("POST-ORDER TRAVERSAL SEQUENCE:");
        intTree.postOrder();
        System.out.println("LEVEL-ORDER TRAVERSAL SEQUENCE:");
        intTree.levelOrder();
    }
    /**
     * Method:          runTrieTrial
     * 
     * Creates trie from dictionary file, then prompts user to add more words before displaying contents
     * user may then remove a word from the dictionary and the contents are displayed again
     */
    private static void runTrieTrial() {
        Trie trie = readDict();
        System.out.println("INSERT WORDS INTO TRIE: (ENTER -1 TO STOP)");
        String input;
        while (true) {
            input = scnr.nextLine();
            if (input.equals("-1"))
                break;
            trie.insert(input);
        }
        System.out.println("INTIAL TRIE:\n");
        char[] strings = new char[100];
        trie.displayTrie(trie.getRoot(), strings, 0);
        System.out.println("ENTER A WORD TO REMOVE FROM DICTIONARY:");
        trie.remove(trie.getRoot(), scnr.nextLine(), 0);
        trie.displayTrie(trie.getRoot(), strings, 0);
    }
    /**
     * Method:          readDict
     * 
     * reads dictionary.txt file and adds each line into the trie.
     * @return trie - Newly created trie containing dictionary.txt words
     */
    private static Trie readDict() {
        Trie trie = new Trie();
        try {
            Scanner fileScan = new Scanner(new FileInputStream("dictionary.txt"));
            while (fileScan.hasNextLine()) {
                String word = fileScan.nextLine();
                trie.insert(word);
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("DICTIONARY FILE NOT FOUND; TRIE IS EMPTY");
        }
        return trie;
    }
}