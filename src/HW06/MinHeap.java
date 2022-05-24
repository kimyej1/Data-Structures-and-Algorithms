package HW06;

import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null)
        {
            throw new IllegalArgumentException("ERROR : Cannot add null data.");
        } else if (size == backingArray.length - 1)
        {
            resize();
            backingArray[size + 1] = data;
            size++;
            upheap(size);
        } else
        {
            backingArray[size + 1] = data;
            size++;
            upheap(size);
        }
    }

    private void resize()
    {
        int reCap = (2 * backingArray.length);
        T[] reArray = (T[]) new Comparable[reCap];

        for (int i = 1; i <= size; i++)
        {
            reArray[i] = backingArray[i];
        }
        backingArray = reArray;
    }

    private void upheap(int i) {
        while (i > 1)
        {
            int parent = i / 2;

            if (backingArray[i].compareTo(backingArray[parent]) < 0)
            {
                T tmp = backingArray[parent];
                backingArray[parent] = backingArray[i];
                backingArray[i] = tmp;
            }
            i = parent;
        }
    }
    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (isEmpty())
        {
            throw new NoSuchElementException("ERROR : Heap is empty");
        } else
        {
            T remove = backingArray[1];
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size--;
            heapRec(1);

            return remove;
        }
    }
    private boolean isEmpty()
    {
        return (size == 0);
    }

    private void heapRec(int curr)
    {
        int right = (2 * curr) + 1;
        int left = (2 * curr);
        int min = curr;

        if (left <= size && backingArray[left].compareTo(backingArray[min]) < 0)
        {
            min = left;
        }
        if (right <= size && backingArray[right].compareTo(backingArray[min]) < 0)
        {
            min = right;
        }
        if (min <= size && backingArray[min].compareTo(backingArray[curr]) < 0)
        {
            T tmp = backingArray[curr];
            backingArray[curr] = backingArray[min];
            backingArray[min] = tmp;
            heapRec(min);
            heapRec(curr);
        }
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}