package de.tim_greller.trie.model;

public class Node {

	char character;
	Node parent;
	Node[] children;
	Integer value;
	
	public Node(char character, Node parent) {
		this(character, parent, null);
	}
	
	public Node(char character, Node parent, Integer value) {
		this.character = character;
		this.parent = parent;
		this.children = new Node[25];
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder stringRepresentation = new StringBuilder();
		stringRepresentation.append(character);
		
		if (value != null) {
			stringRepresentation.append('[').append(value).append(']');
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

}
