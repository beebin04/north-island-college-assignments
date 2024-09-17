/**
 * @file                Trie.java
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
/**
 * Class:           Trie
 * 
 * contains Trie methods and TrieNode class for organizing strings by characters
 */
public class Trie {
    final int ALPHABET_SIZE = 26;

    TrieNode root;
    /**
     * Constructor:     Trie
     * 
     * creates a new root node marking the initial Trie
     */
    Trie() {
        root = new TrieNode();
    }
    /**
     * method:          insert
     * 
     * inserts a new word into the Trie by creating TrieNodes for each character and
     * checking if any characters are a prefix to another existing word; if so, the word is attached after the
     * existing nodes.
     * @param key - word to insert
     */
    public void insert(String key) {
        key = key.toLowerCase();
        int index;
        TrieNode nextNode = root;
        for (Character c : key.toCharArray()) {
            index = c - 'a';
            if (index < 0 || index > 25) {
                continue;
            }
            if (nextNode.childNodes[index] == null)
                nextNode.childNodes[index] = new TrieNode();

            nextNode = nextNode.childNodes[index];
        }
        nextNode.endOfWord = true;
    }
    /**
     * Method:          search
     * 
     * searches for a word by comparing the characters of the key and following any already
     * existing characters in the Trie to find the word. If the word is found, the method returns true; otherwise, false.
     * @param key - word to search
     * @return true if word is found, false if not found
     */
    public boolean search(String key) {
        int index;
        TrieNode node = root;
        for (Character c : key.toCharArray()) {
            index = c - 'a';
            if (node.childNodes[index]  == null) 
                return false;
            node = node.childNodes[index];
        }
        return (node.endOfWord && node != null);

    }
    /**
     * Method:          isEmpty
     * 
     * Checks root node for non-null children; if any are encountered, the trie is not empty
     * @param root - root node of trie
     * @return true if trie is empty; false if not
     */
    private boolean isEmpty(TrieNode root) {
        for (TrieNode n : root.childNodes) {
            if (n != null)
                return false;
        }
        return true;
    }
    /**
     * Method:          remove
     * @param root - subroot node
     * @param key - String to remove from Trie
     * @param depth - current depth
     * @return
     */
    public TrieNode remove(TrieNode root, String key, int depth) {
        if (root == null)
            return null;
        //if current depth is the last char of the key
        if (depth == key.length()) {

            if (root.endOfWord)
                root.endOfWord = false;

            //if node has no children, remove node
            if (isEmpty(root))
                root = null;

            return root;
        }
        //gets index from first char of key
        int index = key.charAt(depth) - 'a';
        root.childNodes[index] = remove(root.childNodes[index], key, depth + 1);
        if (isEmpty(root) && root.endOfWord == false)
            root = null;

        return root;
    }
    /**
     * Method:              displayTrie
     * @param root - root node of Trie
     * @param str - char array for strings in trie
     * @param level - current depth
     */
    public void displayTrie(TrieNode root, char[] str, int level) {
        if (root.endOfWord) {
            for (int i = level; i < str.length; i++) {
                str[i] = '\0';
            }
            System.out.println(str);
        }

        for (int j = 0; j < ALPHABET_SIZE; j++) {
            if (root.childNodes[j] != null) {
                str[level] = (char) (j + 'a');
                displayTrie(root.childNodes[j], str, level + 1);
            }
        }
    }
    /**
     * Method:          getRoot
     * @return root node of Trie
     */
    public TrieNode getRoot() {
        return root;
    }
    /**
     * Class:           TrieNode
     * 
     * contais fields and constructor method for TrieNodes
     */
    private class TrieNode {
        boolean endOfWord;
        TrieNode[] childNodes;

        TrieNode() {
            endOfWord = false;
            childNodes = new TrieNode[ALPHABET_SIZE];
        }
    };

}
