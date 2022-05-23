package HW04;

import java.util.List;
import java.util.ArrayList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        List<T> data = new ArrayList<>();
        preorderHelper(data, root);
        return data;
    }

    private void preorderHelper(List<T> list, TreeNode<T> node) {
        if (node == null) {
            return;
        } else {
            list.add(node.getData());
            preorderHelper(list, node.getLeft());
            preorderHelper(list, node.getRight());
        }
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        List<T> data = new ArrayList<>();
        inorderHelper(data, root);
        return data;
    }

    private void inorderHelper(List<T> list, TreeNode<T> node) {
        if (node == null) {
            return;
        } else {
            inorderHelper(list, node.getLeft());
            list.add(node.getData());
            inorderHelper(list, node.getRight());
        }
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        List<T> data = new ArrayList<>();
        postorderHelper(data, root);
        return data;
    }

    private void postorderHelper(List<T> list, TreeNode<T> node) {
        if (node == null) {
            return;
        } else {
            postorderHelper(list, node.getLeft());
            postorderHelper(list, node.getRight());
            list.add(node.getData());
        }
    }

}