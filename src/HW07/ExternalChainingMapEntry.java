package HW07;

/**
 * Map entry class used for implementing the ExternalChainingHashMap.
 *
 * DO NOT MODIFY THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class ExternalChainingMapEntry<K, V> {

    private K key;
    private V value;
    private ExternalChainingMapEntry<K, V> next;

    /**
     * Constructs a new ExternalChainingMapEntry with only the given key and value.
     *
     * @param key   The key in the new entry.
     * @param value The value in the new entry.
     */
    public ExternalChainingMapEntry(K key, V value) {
        this(key, value, null);
    }

    /**
     * Constructs a new ExternalChainingMapEntry with the given key, value, and next reference.
     *
     * @param key   The key in the new entry.
     * @param value The value in the new entry.
     * @param next  The next entry in the external chain.
     */
    public ExternalChainingMapEntry(K key, V value, ExternalChainingMapEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /**
     * Gets the key.
     *
     * @return The key.
     */
    public K getKey() {
        return key;
    }

    /**
     * Gets the value.
     *
     * @return The value.
     */
    public V getValue() {
        return value;
    }

    /**
     * Gets the next entry.
     *
     * @return The next entry.
     */
    public ExternalChainingMapEntry<K, V> getNext() {
        return next;
    }

    /**
     * Sets the key.
     *
     * @param key The new key.
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Sets the value.
     *
     * @param value The new value.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Sets the next entry.
     *
     * @param next The new next entry.
     */
    public void setNext(ExternalChainingMapEntry<K, V> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String key = this.key == null ? "null" : this.key.toString();
        String value = this.value == null ? "null" : this.value.toString();
        return String.format("(%s, %s)", key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        // DO NOT USE THIS METHOD IN YOUR CODE! This is for testing ONLY!
        if (!(o instanceof ExternalChainingMapEntry)) {
            return false;
        } else {
            ExternalChainingMapEntry<K, V> that = (ExternalChainingMapEntry<K, V>) o;
            return that.getKey().equals(key) && that.getValue().equals(value);
        }
    }
}