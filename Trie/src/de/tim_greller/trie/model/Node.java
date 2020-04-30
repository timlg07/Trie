package de.tim_greller.trie.model;

public class Node {

    private char ch;
    private Node parent;
    private Node[] children;
    private Integer points;
    
    public Node() {
        this('+', null);
    }
    
    public Node(char ch, Node parent) {
        this.ch = ch;
        this.parent = parent;
        this.children = new Node[26];
        this.points = null;
    }
    
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
    
    private void remove() {
        setPoints(null);
        parent.cleanup(ch - 'a');
    }
    
    private void cleanup(int index) {
        if (children[index].isUnnecessary()) {
            children[index] = null;
            parent.cleanup(ch - 'a');
        }
    }

    // A node is unnecessary if it contains no value and no children.
    private boolean isUnnecessary() {
        return getPoints() == null && !hasChildren();
    }

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
    
    private boolean hasChildren() {
        boolean result = false;
        for (Node child : children) {
            if (child != null) {
                result = true;
            }
        }
        return result;
    }

    public Node getChild(char ch) {
        return children[ch - 'a'];
    }
    
    // if this is should be private how is the trie
    // supposed to add any elements?
    /*private*/ void setChild(char ch, Node child) {
        children[ch - 'a'] = child;
    }
    
    public void setPoints(Integer points) {
        this.points = points;
    }
    
    public Integer getPoints() {
        return points;
    }

}
