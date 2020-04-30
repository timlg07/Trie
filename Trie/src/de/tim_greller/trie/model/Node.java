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
    
    public void setChild(char ch, Node child) {
        children[ch - 'a'] = child;
    }
    
    public void setPoints(Integer points) {
        this.points = points;
    }
    
    public Integer getPoints() {
        return points;
    }

}
