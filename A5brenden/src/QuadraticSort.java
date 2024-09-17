/**
 * @file                QuadraticSort.java
 * @author              Brenden O'Brien
 * @version             1.0
 * @date                2024-03-11
 * Course:              CPS 101
 * 
 * Problem Statement:   Empirically verify Big-O efficiency assessments
 *                      of selected sorting algorithms, and enhance the 
 *                      existing quicksort algorithm.
 */
/**
 * Class:      QuadraticSort
 * 
 * Contains insertion and bubble sorting algorithms as average 
 * time complexities of both methods are O(N^2)
 */
public class QuadraticSort<T extends Comparable<T>> {
   /**
    * Method:     insertionSort
    * @param arr
    */
   public void insertionSort(T[] arr) {
      for (int i = 1; i < arr.length; i++) {
         int j = i;
         while (j > 0 && arr[j].compareTo(arr[j - 1]) < 0) {
            // Swap numbers[j] and numbers [j - 1]
            T temp = arr[j];
            arr[j] = arr[j - 1];
            arr[j - 1] = temp;
            j--;
         }
      }
   }
   /**
    * Method:     insertionsSort
    * @param arr
    * @param startIndex
    * @param endIndex
    */
   public void insertionSort(T[] arr, int startIndex, int endIndex) {
      for (int i = startIndex; i < endIndex; i++) {
         int j = startIndex + 1;
         while (j > 0 && arr[j].compareTo(arr[j - 1]) < 0) {
            T temp = arr[j];
            arr[j] = arr[j - 1];
            arr[j - 1] = temp;
            j--;
         }
      }
   }
   /**
    * Method:     bubbleSort
    * @param arr
    */
   public void bubbleSort(T[] arr) {
     for (int i = 0; i < arr.length - 1; i++) {
        for (int j = 0; j < arr.length - i - 1; j++) {

           if (arr[j].compareTo(arr[j+1]) > 0) {
              T temp = arr[j];
              arr[j] = arr[j + 1];
              arr[j + 1] = temp;
           }
        }
     }
   }
}
