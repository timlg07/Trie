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
	
	}

}
