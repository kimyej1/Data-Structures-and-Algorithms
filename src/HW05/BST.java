package HW05;

import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        if (data == null)
        {
            throw new IllegalArgumentException("ERROR : Data can't be null");
        }
        if (root == null)
        {
            root = new BSTNode<>(data);
            size++;
        } else
        {
            addHelper(data, root);
        }
    }

    private void addHelper(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) > 0)
        {
            if (node.getRight() == null)
            {
                node.setRight(new BSTNode<>(data));
                size++;
            } else
            {
                addHelper(data, node.getRight());
            }
        } else if (data.compareTo(node.getData()) < 0)
        {
            if (node.getLeft() == null)
            {
                node.setLeft(new BSTNode<>(data));
                size++;
            } else {
                addHelper(data, node.getLeft());
            }
        }
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        BSTNode<T> node = new BSTNode<>(data);
        if (data == null)
        {
            throw new IllegalArgumentException("ERROR : Cannot remove null data from tree");
        }
        root = removeHelper(data, root, node);
        size--;
        return node.getData();
    }

    private BSTNode<T> removeHelper(T data, BSTNode<T> curr, BSTNode<T> node) {
        if (curr == null)
        {
            throw new NoSuchElementException("ERROR : Data not found in tree.");
        }

        if (data.compareTo(curr.getData()) > 0)
        {
            curr.setRight(removeHelper(data, curr.getRight(), node));

        } else if (data.compareTo(curr.getData()) < 0)
        {
            curr.setLeft(removeHelper(data, curr.getLeft(), node));

        } else
        {
            node.setData(curr.getData());

            if (curr.getLeft() != null && curr.getRight() != null)
            {
                BSTNode<T> n = new BSTNode<>(null);
                curr.setLeft(predecessor(curr.getLeft(), n));
                curr.setData(n.getData());
            } else
            {
                if (curr.getLeft() != null)
                {
                    return curr.getLeft();
                } else
                {
                    return curr.getRight();
                }
            }
        }
        return curr;
    }

    private BSTNode<T> predecessor(BSTNode<T> curr, BSTNode<T> n) {
        if (curr.getRight() == null)
        {
            n.setData(curr.getData());
            return curr.getLeft();
        }
        curr.setRight(predecessor(curr.getRight(), n));
        return curr;
    }
    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }


    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
