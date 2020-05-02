package de.tim_greller.trie.model;

/**
 * This class models the trie, a special tree data structure. It can store an 
 * Integer value for each key consisting of standard lowercase letters and 
 * provides operations to use and manage this data.
 */
public class Trie {

    /** The root element of the tree which does not contain any data. */
    private Node root;

    /** Creates an empty trie. */
    public Trie() {
        root = new Node();
    }

    /**
     * Adds the Integer value {@code points} for the given {@code key} to the 
     * trie. If the {@code key} already holds a value, the method returns 
     * {@code false} without changing the data.
     * 
     * @param key     The key which should be associated with a value.
     * @param points  The Integer value which should be stored in the trie.
     * @return Whether {@code points} was added successful to the new key.
     */
    public boolean add(String key, Integer points) {
        // Navigate to the Node with the given key and append missing Nodes on
        // the way.
        Node iterator = root;
        for (char c : key.toCharArray()) {
            Node child = iterator.getChild(c);
            if (child == null) {
                child = new Node(c, iterator);
                iterator.setChild(c, child);
            }
            iterator = child;
        }

        // Do not do anything if the key already has a value assigned.
        if (iterator.getPoints() != null) {
            return false;
        }

        iterator.setPoints(points);
        return true;
    }

    /**
     * Overrides an existing value at the position {@code key} with the new 
     * value {@code points}. If no entry exists for the given {@code key},
     * the method returns {@code false} without changing the data.
     * 
     * @param key     The key whose value should be altered.
     * @param points  The new value which should be stored in the trie.
     * @return Whether the {@code key} hold a value which could be changed.
     */
    public boolean change(String key, Integer points) {
        Node target = root.find(key);

        // Unable to change value if no entry exists for the given key.
        if (target == null || target.getPoints() == null) {
            return false;
        }

        target.setPoints(points);
        return true;
    }

    /**
     * Removes an existing value at the position {@code key}. If no entry exists
     * for the given {@code key}, the method returns {@code false} without 
     * deleting anything.
     * 
     * @param key The key whose value should get removed from the trie.
     * @return Whether the {@code key} hold a value which could be removed.
     */
    public boolean remove(String key) {
        Node target = root.find(key);

        // Unable to remove value if no entry exists for the given key.
        if (target == null || target.getPoints() == null) {
            return false;
        }

        target.remove();
        return true;
    }

    /**
     * Returns the Integer value stored at the given {@code key} or {@code null}
     * if no value is associated with the {@code key}.
     * 
     * @param key The key of the requested value.
     * @return The Integer value associated with the {@code key} or {@code null}
     * for keys with no value.
     */
    public Integer points(String key) {
        Node target = root.find(key);
        return (target == null) ? null : target.getPoints();
    }

    /**
     * Returns a string that textually represents this object.
     * 
     * @return A string representation of the current trie structure and the
     *         stored data.
     */
    @Override
    public String toString() {
        return root.toString();
    }

}
