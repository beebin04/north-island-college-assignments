/**
 * @file                BSTPrint.java
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
class BSTPrint {
   /**
    * Method:        treeToString

    * @param <T>
    * @param subtreeRoot - root node of BST
    * @return - String visualizing BST layout
    */
    public static <T> String treeToString(BSTNode<T> subtreeRoot) {
       if (subtreeRoot == null) {
          return "(empty tree)";
       }
       
       // First convert the tree to an array of line strings
       String[] lines = treeToLines(subtreeRoot);
       
       // Combine all lines into 1 string
       String treeString = lines[0];
       for (int i = 1; i < lines.length; i++) {
          treeString += ("\n" + lines[i]);
       }
       return treeString;
    }
    private static <T> String[] treeToLines(BSTNode<T> subtreeRoot) {
       if (subtreeRoot == null) {
          return new String[0];
       }
       
       // Make a string with the subtreeRoot's key enclosed in brackets
       String rootString = "[" + subtreeRoot.key + "]";
       int rootStrLen = rootString.length();
       
       // Case 1: subtreeRoot is a leaf
       if (subtreeRoot.left == null && subtreeRoot.right == null) {
          String[] oneLine = new String[1];
          oneLine[0] = rootString;
          return oneLine;
       }
       
       // Recursively make line strings for each child
       String[] leftLines = treeToLines(subtreeRoot.left);
       String[] rightLines = treeToLines(subtreeRoot.right);
       
       int lineCount = Math.max(leftLines.length, rightLines.length);
       String[] allLines = new String[lineCount + 2];
       
       // Case 2: subtreeRoot has no left child
       if (subtreeRoot.left == null) {
          // Create the first 2 lines, not yet indented
          allLines[0] = rootString;
          allLines[1] = getSpaces(rootStrLen) + "\\";
          
          // Find where the right child starts
          int rightChildIndent = rightLines[0].indexOf('[');
          
          // Goal: Indent lines appropriately so that the parent's right branch 
          // character ('\') matches up with the right child's '['.
          
          if (rightChildIndent <= rootStrLen) {
             // Indent all lines below
             indentLines(rightLines, rootStrLen - rightChildIndent);
          }
          else {
             // Indent first 2 lines
             String indent = getSpaces(rightChildIndent - rootStrLen);
             allLines[0] = indent + allLines[0];
             allLines[1] = indent + allLines[1];
          }
          
          // Copy rightLines into allLines starting at index 2
          System.arraycopy(rightLines, 0, allLines, 2, rightLines.length);
          
          return allLines;
       }
       
       // Case 3: subtreeRoot has no right child
       if (subtreeRoot.right == null) {
          // Goal: Indent lines appropriately so that the parent's left branch 
          // character ('/') matches up with the left child's ']'.
          
          // Create the first 2 lines
          String indent = getSpaces(leftLines[0].indexOf(']'));
          allLines[0] = indent + " " + rootString;
          allLines[1] = indent + "/";
          
          // Copy leftLines into allLines starting at index 2
          System.arraycopy(leftLines, 0, allLines, 2, leftLines.length);
          
          return allLines;
       }
       
       // Case 4: subtreeRoot has both a left and right child
       
       // The goal is to have the two child nodes as close to the parent as 
       // possible without overlap on any level.
       
       // Compute absolute indentation, in number of spaces, needed for right lines
       int indentNeeded = 0;
       if (rightLines.length > 0) {
          // Indent should at least get the immediate right child to be to the 
          // right of the root
          indentNeeded = Math.max(0, 
             leftLines[0].length() + rootString.length() - rightLines[0].indexOf('['));
       }
       for (int i = 0; i < leftLines.length && i < rightLines.length; i += 2) {
          // Lines with branches are skipped, so the line of interest has only 
          // nodes. The difference between where the left line ends and the 
          // right line begins should be at least 3 spaces for clarity.
          @SuppressWarnings("unused")
        int leftEnd = leftLines[i].lastIndexOf(']');
          int rightBegin = rightLines[i].indexOf('[');
          
          int forThisLine = leftLines[i].length() + 3 - rightBegin;
          indentNeeded = Math.max(indentNeeded, forThisLine);
       }
       
       // Build final lines in allLines starting at index 2
       String absoluteIndent = getSpaces(indentNeeded);
       for (int i = 0; i < leftLines.length || i < rightLines.length; i++) {
          // If no right line, just take the left
          if (i >= rightLines.length) {
             allLines[2 + i] = leftLines[i];
          }
          else {
             String left = "";
             if (i < leftLines.length) {
                left = leftLines[i];
             }
             String right = absoluteIndent + rightLines[i];
             allLines[2 + i] = left + right.substring(left.length());
          }
       }
       
       // The first 2 lines remain. allLines[2] has the proper string for the 
       // 2 child nodes, and thus can be used to create branches in allLines[1].
       int leftIndex = allLines[2].indexOf(']');
       int rightIndex = allLines[2].lastIndexOf('[');
       allLines[1] = getSpaces(leftIndex) + "/" + 
          getSpaces(rightIndex - leftIndex - 1) + "\\";
       
       // The space between leftIndex and rightIndex is the space that 
       // subtreeRoot's string should occupy. If rootString is too short, put 
       // underscores on the sides.
       rootStrLen = rightIndex - leftIndex - 1;
       if (rootString.length() < rootStrLen) {
          int difference = rootStrLen - rootString.length();
          String underscores = getRepeated('_', difference / 2);
          if (difference % 2 == 0) {
             rootString = underscores + rootString + underscores;
          }
          else {
             rootString = underscores + rootString + underscores + "_";
          }
       }
       allLines[0] = getSpaces(leftIndex + 1) + rootString;
       
       return allLines;
    }
    
    private static String getRepeated(char toRepeat, int numberOfTimes) {
       if (numberOfTimes <= 0) {
          return "";
       }
 
       char[] chars = new char[numberOfTimes];
       for (int i = 0; i < numberOfTimes; i++) {
          chars[i] = toRepeat;
       }
       return new String(chars);
    }
    
    private static String getSpaces(int numberOfSpaces) {
       return getRepeated(' ', numberOfSpaces);
    }
    
    private static void indentLines(String[] lines, int numberOfSpaces) {
       if (numberOfSpaces <= 0) {
          return;
       }
        
       // Prepend indentation to each line
       String indent = getSpaces(numberOfSpaces);
       for (int i = 0; i < lines.length; i++) {
          lines[i] = indent + lines[i];
       }
    }
 }
 