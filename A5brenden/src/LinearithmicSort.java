/**
 * @file                LinearithmicSort.java
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
 * LinearithmicSort
 * 
 * contains mergeSort, quickSort, and enhancedQuickSort algorithms as all have close average time complexities
 */
public class LinearithmicSort<T extends Comparable<T>> {
   /**
    * Method:     merge

    * @param arr - array
    * @param i - position of left partition
    * @param j - position just before start of right partition
    * @param k
    */
    private void merge(T[] arr, int i, int j, int k) {
        int mergedSize = k - i + 1;                // Size of merged partition

        @SuppressWarnings("unchecked")
         T[] mergedVals = (T[]) new Comparable[mergedSize]; // Dynamically allocates temporary
                                                   // array for merged numbers
        int mergePos = 0;                          // Position to insert merged number
        int leftPos = i;                           // Initialize left partition position
        int rightPos = j + 1;                      // Initialize right partition position
        
        // Add smallest element from left or right partition to merged numbers
        while (leftPos <= j && rightPos <= k) {
           if (arr[leftPos].compareTo(arr[rightPos]) <= 0) {
              mergedVals[mergePos] = arr[leftPos];
              leftPos++;
           }
           else {
              mergedVals[mergePos] = arr[rightPos];
              rightPos++;
           }
           mergePos++;
        }
        
        // If left partition is not empty, add remaining elements to merged numbers
        while (leftPos <= j) {
           mergedVals[mergePos] = arr[leftPos];
           leftPos++;
           mergePos++;
        }
     
        // If right partition is not empty, add remaining elements to merged numbers
        while (rightPos <= k) {
           mergedVals[mergePos] = arr[rightPos];
           rightPos++;
           mergePos++;
        }
     
        // Copy merged numbers back to numbers
        for (mergePos = 0; mergePos < mergedSize; mergePos++) {
           arr[i + mergePos] = mergedVals[mergePos];
        }
     }
     /**
      * Method:      mergeSort
      * @param arr - array to be sorted
      * @param i - start index of partition
      * @param k - end index of partition
      */
     public void mergeSort(T[] arr, int i, int k) {
        int j = 0;
        
        if (i < k) {
           j = (i + k) / 2;  // Find the midpoint in the partition
  
           // Recursively sort left and right partitions
           mergeSort(arr, i, j);
           mergeSort(arr, j + 1, k);
              
           // Merge left and right partition in sorted order
           merge(arr, i, j, k);
        }
     }
     /**
      * Method:         partition
      * @param arr - array to be partitioned
      * @param startIndex - start index of partition
      * @param endIndex - end of partition
      * @return high - last index of left partition
      */
     private int partition(T[] arr, int startIndex, int endIndex) {
        // Select the middle value as the pivot.
        int midpoint = startIndex + (endIndex - startIndex) / 2;
        T pivot = arr[midpoint];
     
        // "low" and "high" start at the ends of the array segment
        // and move towards each other.
        int low = startIndex;
        int high = endIndex;
     
        boolean done = false;
        while (!done) {
           // Increment low while numbers[low] < pivot
           while (arr[low].compareTo(pivot) < 0) {
              low = low + 1;
           }
        
           // Decrement high while pivot < numbers[high]
           while (pivot.compareTo(arr[high]) < 0) {
              high = high - 1;
           }
        
           // If low and high have crossed each other, the loop
           // is done. If not, the elements are swapped, low is
           // incremented and high is decremented.
           if (low >= high) {
              done = true;
           }
           else {
              T temp = arr[low];
              arr[low] = arr[high];
              arr[high] = temp;
              low++;
              high--;
           }
        }
  
        // "high" is the last index in the left segment
        return high;
     }
     /**
      * Method:      ePartition
      * Differs from partition() method by selecting the pivot by finding the 
      * value of the median of three indicies: the start, the end, and the midpoint.
      * Allows for smoother partitioning
      * @param arr - array to be partitioned
      * @param start - start index of partition
      * @param end - end index of partition
      * @return high - highest index of left partition
      */
     private int ePartition(T[] arr, int start, int end) {
      T medianVal = arr[(end + ((end - start) / 2) - start) / 3];
      T pivot = medianVal;

      int low = start;
      int high = end;

      boolean done = false;
      while (!done) {
         while (arr[low].compareTo(pivot) < 0) {
            low = low + 1;
         }
         while (pivot.compareTo(arr[high]) < 0) {
            high = high - 1;
         }

         if (low >= high) {
            done = true;
         } else {
            T temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
         }
      }
      return high;
     }
     /**
      * Method:      quicksort
      *
      * Partitions the array by fining the midpoint of the array and using it as a pivot to 
      * move values either less or greater than the pivot to the correct partition. The algorithm repeadetly partitions
      * the the arrays until they are sorted by the numerous swaps around or with the pivot value.

      * @param vals - array/partition to be sorted
      * @param startIndex - start of array or partition
      * @param endIndex - end of array/partition
      */
     public void quicksort(T[] vals, int startIndex, int endIndex) {
        // Only attempt to sort the array segment if there are
        // at least 2 elements
        if (endIndex <= startIndex) {
           return;
        }
        // Partition the array segment
        int high = partition(vals, startIndex, endIndex);
  
        // Recursively sort the left segment
        quicksort(vals, startIndex, high);
  
        // Recursively sort the right segment
        quicksort(vals, high + 1, endIndex);
     }
     /**
      * Method:      enhancedQuickSort
      * 
      * Selects pivots by taking the value located by the median of the start, end and midpoint of the array.
      * When partition size is <= 7; the algorithm performs an insertion sort on the partition instead of partitioning
      * it further into individual elements
      * @param arr - array/partition to be sorted
      * @param start - beginning index of array/partition
      * @param end - end index of array/partition
      */
     public void enchancedQuickSort(T[] arr, int start, int end) {


      if (end <= start) {
         return;
      }
      if (end - start + 1 <= 7) {
         QuadraticSort<T> iSort = new QuadraticSort<>();
         iSort.insertionSort(arr, start, end);
      } else {

         int high = ePartition(arr, start, end);

         quicksort(arr, start, high);
   
         quicksort(arr, high + 1, end);
      }
     }
}