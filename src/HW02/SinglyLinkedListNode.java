package HW02;

/**
 * Node class used for implementing the SinglyLinkedList.
 *
 * DO NOT MODIFY THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class SinglyLinkedListNode<T> {

    private T data;
    private SinglyLinkedListNode<T> next;

    /**
     * Constructs a new SinglyLinkedListNode with the given data and next node
     * reference.
     *
     * @param data the data stored in the new node
     * @param next the next node in the list
     */
    SinglyLinkedListNode(T data, SinglyLinkedListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Creates a new SinglyLinkedListNode with only the given data.
     *
     * @param data the data stored in the new node
     */
    SinglyLinkedListNode(T data) {
        this(data, null);
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    T getData() {
        return data;
    }

    /**
     * Gets the next node.
     *
     * @return the next node
     */
    SinglyLinkedListNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node.
     *
     * @param next the new next node
     */
    void setNext(SinglyLinkedListNode<T> next) {
        this.next = next;
    }
}