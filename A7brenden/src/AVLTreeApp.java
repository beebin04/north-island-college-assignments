/**
 * @file                AVLTreeApp.java
 * @author              Brenden O'Brien
 * @version             1.0
 * @date                2024-03-24
 * Course               CPS 101
 * 
 * Problem Statement:   Implement an AVLTree and compare
 *                      it to a regular BST
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
/**
 * Class:           AVLTreeApp
 * 
 */
public class AVLTreeApp {
    private static int[] valsToAdd = {5, 10, 20, 22, 12, 15, 47, 19, 3, 18};
    private static String[] wordsToAdd = {"Lily", "Dave", "Nick", "Ted", "Bob", "Alice", "Diva", "Eva", "Fiona", "Mary", "Kerry"};

    private static AVLTree<Integer> avlTree = new AVLTree<>();
    private static AVLTree<String> avlStringTree = new AVLTree<>();
    private static BST<Integer> bst = new BST<>();
    private static Scanner scnr = new Scanner(System.in);
    /**
     * Method:      main
     * 
     * Tests AVLTree implementation with Integers and Strings,
     * then tests BST with same values for comparison. search and remove methods are tested and trees
     * are displayed with BSTPrint class. 
     * A new AVLTree and BST are created and filled with strings read from a text file. The trees heights are 
     * calculated to demonstrate the effeciency of the AVLTree's rebalancing methods when given a large data set.
     * 
     * The BST, which only inserts new data to the left or right of an internal node or child node with no
     * rebalancing ended up with a height of 596 levels of nodes. The AVLTree had a height of 9 thanks to the tree's
     * balance factor and balancing methods.
     * @param args
     */
    public static void main(String[] args) {

        for (int i : valsToAdd) {
            avlTree.insert(new Node<Integer>(i));
            bst.insert(new Node<Integer>(i));
        }
        for (String s : wordsToAdd) 
            avlStringTree.insert(new Node<String>(s.toUpperCase()));
        
           

        System.out.println("====Testing AVLTree with small Integer data set====\n");
        printAVLTreeInfo();

        System.out.println("Enter a value to remove:");
        for (int i = 0; i < 2; i++) {
            boolean result = avlTree.removeKey(getInt());
            if (result == false) {
                System.out.println("Error removing node... AVLTree unchanged");
                printAVLTreeInfo();
            }

            else {
                System.out.println("Node successfully removed from AVLTree");
                printAVLTreeInfo();
            }
        }
        System.out.println("====Testing BST with small Integer data set====\n");
        printBSTInfo();

        System.out.println("Enter a value to remove:");
        for (int i = 0; i < 2; i++) {
            boolean result = bst.remove(getInt());
            if (result == false)
                System.out.println("Error removing node... BST unchanged");
            else
                System.out.println("Node successfully removed from BST");

            printBSTInfo();
        }

        System.out.println("====Testing AVLTree with String values====\n");
        System.out.println(BSTPrint.treeToString(avlStringTree.getRoot()) + "\n");
        System.out.println("Tree Height: " + avlStringTree.getRoot().height);
        System.out.println("Enter a value to remove:");
        for (int i = 0; i < 2; i++) {
            boolean result = avlStringTree.removeKey(getInput().toUpperCase());
            if (result == false)
            System.out.println("Error removing node... AVLTree unchanged");
            else
            System.out.println("Node successfully removed from AVLTree");

            System.out.println(BSTPrint.treeToString(avlStringTree.getRoot()));
            System.out.println("Tree Height: " + avlStringTree.getRoot().height);
        }
        insertDictionaryStrings();
    }
    /**
     * Method:          printAVLTreeInfo
     * 
     * Prints visible tree using BTSPrint class, and prints the AVLTree's root height
     */
    private static void printAVLTreeInfo() {
        System.out.println(BSTPrint.treeToString(avlTree.getRoot()) + "\n");
        System.out.println("Tree Height: " + avlTree.getRoot().height);
    }
    /**
     * Method           printBSTInfo
     * 
     * prints visible tree using BSTPrint class, then prints the tree's root node height
     */
    private static void printBSTInfo() {
        System.out.println(BSTPrint.treeToString(bst.getRoot()) + "\n");
        System.out.println("Tree Height: " + bst.getHeight(bst.getRoot()));
    }
    /**
     * Mehtod:          getInput
     * gets user input as string
     * @return user input string
     */
    private static String getInput() {
        String in = scnr.nextLine();
        return in;
    }
    /**
     * Method:          getInt
     * 
     * parses integers provided by getInput
     * @return integer parsed from user input string
     */
    private static int getInt() {
        int i = -1;
        while (true) {
            try {
                i = Integer.parseInt(getInput());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error parsing input... please enter a valid integer");
            }
        }
        return i;
    }
    /**
     * Method           insertDictionaryStrings
     * 
     * reads dictionary.txt, for each line the string is inserted in an AVLTree and BST. Their heights are printed
     * for comparison of effeciency
     */
    private static void insertDictionaryStrings() {
        AVLTree<String> avlDictionary = new AVLTree<>();
        BST<String> bstDictionary = new BST<>();
        System.out.println("Entering dictionary values into AVLTree and BST...\n");
        try {
            Scanner dictonaryScanner = new Scanner(new FileInputStream("dictionary.txt"));
            while (dictonaryScanner.hasNextLine()) {
                String s = dictonaryScanner.nextLine().toLowerCase();
                avlDictionary.insert(new Node<String>(s));
                bstDictionary.insert(new Node<String>(s));
            }
            dictonaryScanner.close();
        } catch (IOException ioe) {
            System.out.println("Error reading dictionary... please make sure file location is correct");
        }

        System.out.println("BST Dictionary Height == " + bstDictionary.getHeight(bstDictionary.getRoot()));
        System.out.println("AVLTree Dictionary Height == " + avlDictionary.getRoot().height);
    }   
}
