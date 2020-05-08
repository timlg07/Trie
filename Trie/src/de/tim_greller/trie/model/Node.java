package de.tim_greller.trie.model;

/**
 * This class provides the Node elements used to build the trie data structure.
 * It provides methods necessary to navigate through the trie, output it and 
 * change it.
 */
public class Node {

    private static final char FIRST_CHAR = 'a';
    private static final char LAST_CHAR = 'z';
    
    private char ch;
    private Node parent;
    private Node[] children;
    private Integer points;

    /** Creates a root Node with the character '+' and no parent. */
    public Node() {
        this('+', null);
    }

    /**
     * Creates a new Node with the given character and parent.
     * 
     * @param ch      The character the new Node gets created for.
     * @param parent  The parent of the new Node.
     */
    public Node(char ch, Node parent) {
        this.ch = ch;
        this.parent = parent;
        this.children = new Node[LAST_CHAR - FIRST_CHAR + 1];
        this.points = null;
        
        if (parent != null) {
            parent.setChild(ch, this);
        }
    }

    /**
     * Searches for the Node with the given {@code key} in the current sub-trie
     * recursively and returns it if it exists and {@code null} otherwise.
     * 
     * @param key The key to search after in the children of this Node.
     * @return The Node at the given {@code key} or {@code null} if it does not 
     *         exist.
     */
    public Node find(String key) {
        // Base case: reached end of key.
        if (key.isEmpty()) {
            return this;
        }

        Node next = getChild(key.charAt(0));
        String remainingKey = key.substring(1);
        
        // Return null if the searched Node does not exist.
        if (next == null) {
            return null;
        }
        
        // Recursively continue at the next level.
        return next.find(remainingKey);
    }

    /**
     * Removes itself and all now unnecessary parent Nodes from the trie.
     */
    public void remove() {
        setPoints(null);
        parent.cleanup(calculateIndex(ch));
    }

    /**
     * Removes all unnecessary parent Nodes.
     * 
     * @param index The array-index from the child which called the cleanup.
     */
    private void cleanup(int index) {
        if (children[index].isUnnecessary()) {
            children[index] = null;
            
            // Stop recursion when root is reached.
            if (parent != null) {
                parent.cleanup(calculateIndex(ch));
            }
        }
    }

    /**
     * This Node is considered unnecessary if it contains no value or children.
     * 
     * @return whether this Node is unnecessary or not.
     */
    private boolean isUnnecessary() {
        return getPoints() == null && !hasChildren();
    }

    /**
     * Returns a string that textually represents this object and its children.
     * 
     * @return A string representation of the current sub-trie containing
     *         structure and stored data.
     */
    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder();
        stringRepresentation.append(ch);

        if (points != null) {
            stringRepresentation.append('[').append(points).append(']');
        }

        if (hasChildren()) {
            stringRepresentation.append('(');
            for (Node child : children) {
                if (child != null) {
                    stringRepresentation.append(child);
                }
            }
            stringRepresentation.append(')');
        }

        return stringRepresentation.toString();
    }

    /**
     * Returns {@code true} if this Node has one or more children.
     * @return Whether this Node has children or not.
     */
    private boolean hasChildren() {
        boolean result = false;
        for (Node child : children) {
            if (child != null) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Get the child representing the given character.
     * 
     * @param ch The character of the requested child.
     * @return The child or {@code null} if it does not exist.
     */
    public Node getChild(char ch) {
        return children[calculateIndex(ch)];
    }

    /**
     * Sets the child representing the given character.
     * 
     * @param ch The character of the new child.
     * @param child The new Node which should be added as child of this Node.
     */
    private void setChild(char ch, Node child) {
        children[calculateIndex(ch)] = child;
    }

    /**
     * Sets the value of this Node.
     * 
     * @param points The new point value.
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * Returns the value of this Node.
     * 
     * @return The point value stored in this Node.
     */
    public Integer getPoints() {
        return points;
    }
    
    /**
     * Calculates the index of the given char in an array ranging from 
     * {@code FIRST_CHAR} to {@code LAST_CHAR}.
     * 
     * @param ch The character which index should be calculated.
     * @return The array index of the character {@code ch}.
     */
    private int calculateIndex(char ch) {
        return ch - FIRST_CHAR;
    }

}
