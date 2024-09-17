/**
 * @file                DemoApp.java
 * @author              Brenden O'Brien
 * @version             1.0
 * @date                2024-03-10
 * Course               CPS 101
 * 
 * Problem Statement:   Empirically verify Big-O efficiency assessments
 *                      of selected sorting algorithms, and enhance the 
 *                      existing quicksort algorithm.
 */
import java.util.Arrays;
import java.util.Random;
/**
 * Class:       DemoApp
 * 
 * Main application for testing and analyzing time complexities
 * of quadratic and linearithmic sorting algorithms.
 * 3 Different trials,
 * (1) first a small data set is sorted by each 
 * algorithm and their performance is recorded,
 * (2) A similar trial is done with the same size 
 * data set but with larger integers. 
 * (3)Finally, here is an incrementing test where
 * the data size increases until the difference in computational 
 * effeciency between large data sets stablizes between 10% 3 times for each algorithm
 */
public class DemoApp {
    final static int START_ARRAY_SIZE = 20000;
    private static double lastCValue;
    private static double cVal;
    private static double percentDiff;
    private static int count;
    /**
     * Method:      Main
     * 
     * runs trials for testing computational effeciency
     * @param args
     */
    public static void main(String[] args) {
        begin2DigitTrials();
        begin5DigitTrials();
        beginIncrementTrials();
    }
    /**
     * Method:      intializeArray2Digits
     * 
     * Initializes an array of arraySize with each value being an integer between 10-99 inclusive
     * @param arraySize
     * @return unsorted Integer array filled with elements ranging from 10-99
     */
    private static Integer[] initializeArray2Digits(int arraySize) {
        Random rnd = new Random(23632);
        Integer[] nums = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            nums[i] = ((rnd.nextInt(9) + 1) * 10) + (rnd.nextInt(10));
        }
        return nums;
    }
    /**
     * Method:      intializeArray5Digits
     * 
     * Intializes an array of arraySize with each value being an integer between 10000 - 99999 inclusive
     * @param arraySize
     * @return unsorted Integer array of arraySize filled with integers between 10000-99999
     */
    private static Integer[] initializeArray5Digits(int arraySize) {
        Random rnd = new Random(23632);
        Integer[] nums = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            nums[i] = ((rnd.nextInt(9) + 1) * 10000) + (rnd.nextInt(10) * 1000) + (rnd.nextInt(10) * 100) + (rnd.nextInt(10) * 10) + rnd.nextInt(10);
        }
        return nums;
    }
    /**
     * Mehthod:     bubbleSort
     * 
     * calls and records the time used to sort an Integer array of arraySize using
     * the bubbleSort sorting algorithm.
     * 
     * Testing with a generic bubbleSort algorithm the time complexity is determined to be O(N^2) with a 
     * C-Ratio of approximately 4.7
     * @param arr - array to be sorted
     * @param arraySize - size of array
     */
    private static void bubbleSort(Integer[] arr, int arraySize) {
        QuadraticSort<Integer> bubSort = new QuadraticSort<>();
        long startTime = System.nanoTime();
        bubSort.bubbleSort(arr);
        long totTime = System.nanoTime() - startTime;
        cVal = (totTime / Math.pow(arraySize, 2));
        System.out.print("Array Size: " + arraySize + " Elements | ");
        System.out.println("Bubble Sort Runtime: " + totTime + " ns | C-Ratio: " + cVal);
        if (lastCValue != 0) {
            percentDiff = Math.abs((cVal - lastCValue) / ((cVal + lastCValue) / 2)) * 100;
            System.out.println("Percent difference between last C-Ratio: %" + percentDiff);
        }
        if (percentDiff <= 10 && percentDiff != 0) {
            count++;
        } else {
            count = 0;
        }
        lastCValue = cVal;

    }
    /**
     * Method:      insertionSort
     * 
     * calls and records the time taken to sort an Integer array of arraySize with
     * the insertionSort algorithm.
     * 
     * Testing with a generic insertionSort algorithm the time complexity has been verified
     * and calculated to be approximately O(N^2) with a C-Ratio of approximately 2.0
     * @param array
     */
    private static void insertionSort(Integer[] nums, int arraySize) {
        QuadraticSort<Integer> insertSort = new QuadraticSort<>();
        long startTime = System.nanoTime();
        insertSort.insertionSort(nums);
        long totTime = System.nanoTime() - startTime;
        cVal = (totTime / Math.pow(arraySize, 2));
        System.out.print("Array Size: " + arraySize + " Elements | ");
        System.out.println("Insertion Sort Runtime: " + totTime + " ns | C-Ratio: " + cVal);
        if (lastCValue != 0) {
            percentDiff = Math.abs((cVal - lastCValue) / ((cVal + lastCValue) / 2)) * 100;
            System.out.println("Percent difference between last C-Ratio: %" + percentDiff);
        }
        if (percentDiff <= 10 && percentDiff != 0) {
            count++;
        } else {
            count = 0;
        }
        lastCValue = cVal;
    }
    /**
     * Method:      mergeSort
     * 
     * calls and records time taken to sort an Integer array of arraySize using the mergeSort
     * algorithm
     * 
     * Testing with a generic mergeSort algorithm a time complexity of O(N*log(N)) was verified with a 
     * C-Ratio of 23.6
     * @param nums - array to be sorted
     * @param arraySize - size of array
     */
    private static void mergeSort(Integer[] nums, int arraySize) {
        LinearithmicSort<Integer> mergeSort = new LinearithmicSort<>();
        long startTime = System.nanoTime();
        mergeSort.mergeSort(nums, 0, arraySize - 1);
        long totTime = System.nanoTime() - startTime;
        cVal = (totTime / (arraySize * (Math.log(arraySize) / Math.log(2))));
        System.out.print("Array Size: " + arraySize + " Elements | ");
        System.out.println("Merge Sort Runtime: " + totTime + " ns | C-Ratio: " + cVal);
        if (lastCValue != 0) {
            percentDiff = Math.abs((cVal - lastCValue) / ((cVal + lastCValue) / 2)) * 100;
            System.out.println("Percent difference between last C-Ratio: %" + percentDiff);
        }
        if (percentDiff <= 10 && percentDiff != 0) {
            count++;
        } else {
            count = 0;
        }
        lastCValue = cVal;
    }
    /**
     * Method:      quickSort
     * 
     * calls and records time taken to sort an Integer array of arraySize using quickSort algorithm
     * 
     * Testing with a generic quickSort method a time complexity of O(N*log(N)) was found and verified with a 
     * C-Ratio of  12.1
     * @param nums - array to be sorted
     * @param arraySize - size of array
     */
    private static void quickSort(Integer[] nums, int arraySize) {
        LinearithmicSort<Integer> quickSort = new LinearithmicSort<>();
        long startTime = System.nanoTime();
        quickSort.quicksort(nums, 0, arraySize - 1);
        long totTime = System.nanoTime() - startTime;
        cVal = (totTime / (arraySize * (Math.log(arraySize) / Math.log(2))));
        System.out.print("Array Size: " + arraySize + " Elements | ");
        System.out.println("Quick Sort Runtime: " + totTime + " ns | C-Ratio: "+ cVal);
        if (lastCValue != 0) {
            percentDiff = Math.abs((cVal - lastCValue) / ((cVal + lastCValue) / 2)) * 100;
            System.out.println("Percent difference between last C-Ratio: %" + percentDiff);
        }
        if (percentDiff <= 10 && percentDiff != 0) {
            count++;
        } else {
            count = 0;
        }
        lastCValue = cVal;
    }
    /**
     * Method:      enhancedQuickSort
     * 
     * calls and records time used to sort an Integer array of arraySize using
     * an improved quickSort algorithm
     * 
     * Testing with a generic enhancedQuickSort method the average case was found to be
     * O(N*log(N)) with a C-Ratio of 12.3
     * @param arr - integer to be sorted
     * @param arraySize - size of array
     */
    private static void enchancedQuickSort(Integer[] arr, int arraySize) {
        LinearithmicSort<Integer> eQuickSort = new LinearithmicSort<>();
        long startTime = System.nanoTime();
        eQuickSort.enchancedQuickSort(arr, 0, arraySize - 1);
        long totTime = System.nanoTime() - startTime;
        cVal = (totTime / (arraySize * (Math.log(arraySize) / Math.log(2))));
        System.out.print("Array Size: " + arraySize + " Elements | ");
        System.out.println("Enchaned Quick Sort Runtime: " + totTime + " ns | C-Ratio: "+ cVal);
        if (lastCValue != 0) {
            percentDiff = Math.abs((cVal - lastCValue) / ((cVal + lastCValue) / 2)) * 100;
            System.out.println("Percent difference between last C-Ratio: %" + percentDiff);
        }
        if (percentDiff <= 10 && percentDiff != 0) {
            count++;
        } else {
            count = 0;
        }
        lastCValue = cVal;
    }
    /**
     * Method:      beginIncrementTrials
     * 
     * performs trials with increasingly large data sets with all sorting algorithms;
     * stops when percent difference between current trial's C-Ratio and previous trials is <=10% 3 times consecutively
     */
    private static void beginIncrementTrials() {
        int currentSize = START_ARRAY_SIZE;
        //bubble sort trial
        lastCValue = 0;
        count = 0;
        System.out.println("BUBBLE SORT TRIAL\n===================\n");
        while (count < 3) {
            Integer[] nums = initializeArray5Digits(currentSize);
            bubbleSort(nums, currentSize);
            currentSize += 20000;
            percentDiff = Math.abs((cVal - lastCValue) / ((cVal + lastCValue) / 2)) * 100;
            
        }
        System.out.println("\nSTABLIZED: END OF TRIAL");
        lastCValue = 0;
        currentSize = START_ARRAY_SIZE;
        //insertion sort trial
        count = 0;
        System.out.println("INSERTION SORT TRIAL\n====================\n");
        while (count < 3) {
            Integer[] nums = initializeArray5Digits(currentSize);
            insertionSort(nums, currentSize);
            currentSize += 20000;
        }
        System.out.println("\nSTABLIZED: END OF TRIAL");
        lastCValue = 0;
        currentSize = START_ARRAY_SIZE;
        //merge sort trial
        count = 0;
        System.out.println("MERGE SORT TRIAL\n======================\n");
        while (count < 3) {
            Integer[] nums = initializeArray5Digits(currentSize);
            mergeSort(nums, currentSize);
            currentSize += 1500000;
        }
        System.out.println("\nSTABLIZED: END OF TRIAL");
        lastCValue = 0;
        currentSize = START_ARRAY_SIZE;
        //quick sort trial
        count = 0;
        System.out.println("QUICK SORT TRIAL\n==========================\n");
        while (count < 3) {
            Integer[] nums = initializeArray5Digits(currentSize);
            quickSort(nums, currentSize);
            currentSize += 1500000;
        }
        System.out.println("\nSTABLIZED: END OF TRIAL");
        lastCValue = 0;
        currentSize = START_ARRAY_SIZE;
        count = 0;

        System.out.println("ENHANCED QUICK SORT TRIAL\n=====================\n");
        while (count < 3) {
            Integer[] nums = initializeArray5Digits(currentSize);
            enchancedQuickSort(nums, currentSize);
            currentSize += 1500000;
        }
        System.out.println("\nSTABLIZED: END OF TRIAL");
    }
    /**
     * Method:      begin2DigitTrials
     * 
     * initializes 5 arrays of size 10 and fills with integers ranging from 10-99;
     * executes soring algorithms on arrays to analyze performance with small datasets and small values
     */
    private static void begin2DigitTrials() {
        System.out.println("\n====2 Digit Trial====\n");
        Integer[] array1 = initializeArray2Digits(10);
        Integer[] array2 = initializeArray2Digits(10);
        Integer[] array3 = initializeArray2Digits(10);
        Integer[] array4 = initializeArray2Digits(10);
        Integer[] array5 = initializeArray2Digits(10);

        System.out.println("\nBubble Sort\n===========\n");
        System.out.println("Before: " + Arrays.toString(array1));
        bubbleSort(array1, array1.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array1));
        System.out.println("\nInsertion Sort\n=============\n");
        System.out.println("Before: " + Arrays.toString(array2));
        insertionSort(array2, array2.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array2));
        System.out.println("\nMerge Sort\n===============\n");
        System.out.println("Before: " + Arrays.toString(array3));
        mergeSort(array3, array3.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array3));
        System.out.println("\nQuick Sort\n==================\n");
        System.out.println("Before: " + Arrays.toString(array4));
        quickSort(array4, array4.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array4));
        System.out.println("\nEnchaned Quick Sort\n==================");
        System.out.println("Before: " + Arrays.toString(array5));
        enchancedQuickSort(array5, array5.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array5));
    }
    /**
     * Method:      begin5DigitTrials
     * 
     * intializes 5 arrays of size 10 and fills with digits ranging from 10000-99999;
     * executes sorting algorithms for each array to analyze performance effeciency on small data sets with larger values
     */
    private static void begin5DigitTrials() {
        System.out.println("\n====5 Digit Trial====\n");
        Integer[] array1 = initializeArray5Digits(10);
        Integer[] array2 = initializeArray5Digits(10);
        Integer[] array3 = initializeArray5Digits(10);
        Integer[] array4 = initializeArray5Digits(10);
        Integer[] array5 = initializeArray5Digits(10);

        System.out.println("\nBubble Sort\n===========\n");
        System.out.println("Before: " + Arrays.toString(array1));
        bubbleSort(array1, array1.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array1));
        System.out.println("\nInsertion Sort\n=============\n");
        System.out.println("Before: " + Arrays.toString(array2));
        insertionSort(array2, array2.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array2));
        System.out.println("\nMerge Sort\n===============\n");
        System.out.println("Before: " + Arrays.toString(array3));
        mergeSort(array3, array3.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array3));
        System.out.println("\nQuick Sort\n=================\n");
        System.out.println("Before: " + Arrays.toString(array4));
        quickSort(array4, array4.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array4));
        System.out.println("\nEnchaned Quick Sort\n====================");
        System.out.println("Before: " + Arrays.toString(array5));
        enchancedQuickSort(array5, array5.length);
        lastCValue = 0;
        System.out.println("After: " + Arrays.toString(array5));
    }
}
