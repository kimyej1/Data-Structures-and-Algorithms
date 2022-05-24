package HW10;

import java.util.Comparator;
import java.util.LinkedList;



/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

        /**
         * Implement selection sort.
         *
         * It should be:
         * in-place
         * unstable
         * not adaptive
         *
         * Have a worst case running time of: O(n^2)
         * And a best case running time of: O(n^2)
         *
         * You may assume that the passed in array and comparator
         * are both valid and will never be null.
         *
         * @param <T>        Data type to sort.
         * @param arr        The array that must be sorted after the method runs.
         * @param comparator The Comparator used to compare the data in arr.
         */
        public static <T > void selectionSort (T[]arr, Comparator < T > comparator){
            // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
            if (arr == null || comparator == null)
            {
                throw new IllegalArgumentException("ERROR : Null array or comparator");
            }
            for (int i = 0; i < arr.length - 1; i++)
            {
                int min = i;
                for (int j = i + 1; j < arr.length; j++)
                {
                    if (comparator.compare(arr[j], arr[min]) < 0)
                    {
                        min = j;
                    }
                }
                swap(arr, min, i);
            }
        }

        /**
         * Implement insertion sort.
         *
         * It should be:
         * in-place
         * stable
         * adaptive
         *
         * Have a worst case running time of: O(n^2)
         * And a best case running time of: O(n)
         *
         * You may assume that the passed in array and comparator
         * are both valid and will never be null.
         *
         * @param <T>        Data type to sort.
         * @param arr        The array that must be sorted after the method runs.
         * @param comparator The Comparator used to compare the data in arr.
         */
        public static <T > void insertionSort (T[]arr, Comparator < T > comparator){
            // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
            if (arr == null || comparator == null)
            {
                throw new IllegalArgumentException("ERROR : Null array or comparator");
            }
            for (int i = 1; i < arr.length; i++)
            {
                T tmp = arr[i];
                int j = i - 1;
                while (j >= 0 && comparator.compare(arr[j], tmp) > 0)
                {
                    arr[j + 1] = arr[j--];
                }
                arr[j + 1] = tmp;
            }
        }

        private static <T > void swap (T[]arr,int a, int b)
        {
            T tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

        /**
         * Implement merge sort.
         *
         * It should be:
         * out-of-place
         * stable
         * not adaptive
         *
         * Have a worst case running time of: O(n log n)
         * And a best case running time of: O(n log n)
         *
         * You can create more arrays to run merge sort, but at the end, everything
         * should be merged back into the original T[] which was passed in.
         *
         * When splitting the array, if there is an odd number of elements, put the
         * extra data on the right side.
         *
         * Hint: You may need to create a helper method that merges two arrays
         * back into the original T[] array. If two data are equal when merging,
         * think about which subarray you should pull from first.
         *
         * You may assume that the passed in array and comparator are both valid
         * and will not be null.
         *
         * @param <T>        Data type to sort.
         * @param arr        The array to be sorted.
         * @param comparator The Comparator used to compare the data in arr.
         */
        public static <T > void mergeSort (T[]arr, Comparator < T > comparator){
            // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
            if (arr == null)
            {
                throw new IllegalArgumentException("ERROR : Null array");
            } else if (comparator == null)
            {
                throw new IllegalArgumentException("Null comparator");
            }
            int mid = arr.length / 2;
            mergeHelper(arr, comparator);
        }

        private static <T> void mergeHelper(T[]arr, Comparator < T > comparator)
        {
            if (arr.length < 2)
            {
                return;
            }
            int mid = arr.length / 2;

            T[] left = (T[]) new Object[mid];
            T[] right = (T[]) new Object[arr.length - mid];

            for (int i = 0; i < mid; i++)
            {
                left[i] = arr[i];
            }
            for (int i = mid; i < arr.length; i++)
            {
                right[i - mid] = arr[i];
            }
            mergeSort(left, comparator);
            mergeSort(right, comparator);
            merge(arr, left, right, mid, arr.length - mid, comparator);
        }

        private static <T > void merge (T[]a, T[]left, T[]right,int l, int r, Comparator<T > comparator)
        {
            int i = 0;
            int j = 0;
            int k = 0;

            while (i < l && j < r)
            {
                int c = comparator.compare(left[i], right[j]);
                if (c <= 0)
                {
                    a[k++] = left[i++];
                } else
                {
                    a[k++] = right[j++];
                }
            }
            while (i < l)
            {
                a[k++] = left[i++];
            }
            while (j < r)
            {
                a[k++] = right[j++];
            }
        }

        /**
         * Implement LSD (least significant digit) radix sort.
         *
         * It should be:
         * out-of-place
         * stable
         * not adaptive
         *
         * Have a worst case running time of: O(kn)
         * And a best case running time of: O(kn)
         *
         * Feel free to make an initial O(n) passthrough of the array to
         * determine k, the number of iterations you need.
         *
         * At no point should you find yourself needing a way to exponentiate a
         * number; any such method would be non-O(1). Think about how how you can
         * get each power of BASE naturally and efficiently as the algorithm
         * progresses through each digit.
         *
         * You may use an ArrayList or LinkedList if you wish, but it should only
         * be used inside radix sort and any radix sort helpers. Do NOT use these
         * classes with merge sort. However, be sure the List implementation you
         * choose allows for stability while being as efficient as possible.
         *
         * Do NOT use anything from the Math class except Math.abs().
         *
         * You may assume that the passed in array is valid and will not be null.
         *
         * @param arr The array to be sorted.
         */
        public static void lsdRadixSort ( int[] arr){
            // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
            if (arr == null)
            {
                throw new IllegalArgumentException("ERROR : Null array");
            }
            LinkedList<Integer>[] buckets = (LinkedList<Integer>[]) new LinkedList[19];

            int mod = 10;
            int div = 1;
            boolean c = true;

            while (c)
            {
                c = false;
                for (int num : arr)
                {
                    int bucket = num / div;
                    if (bucket / 10 != 0)
                    {
                        c = true;
                    }
                    if (buckets[bucket % mod + 9] == null)
                    {
                        buckets[bucket % mod + 9] = new LinkedList<>();
                    }
                    buckets[bucket % mod + 9].add(num);
                }
                int arrIdx = 0;

                for (int i = 0; i < buckets.length; i++)
                {
                    if (buckets[i] != null)
                    {
                        for (int num : buckets[i])
                        {
                            arr[arrIdx++] = num;
                        }
                        buckets[i].clear();
                    }
                }
                div *= 10;
            }
        }
    }
}